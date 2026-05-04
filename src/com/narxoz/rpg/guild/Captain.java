package com.narxoz.rpg.guild;

import java.util.Arrays;
import java.util.List;

/**
 * Guild officer responsible for orders and mission coordination.
 */
public class Captain extends GuildMember {

    public Captain(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    @Override
    public List<String> getSubscribedTopics() {
        return Arrays.asList("orders", "scouting", "supplies", "healing", "urgent");
    }

    public void issueOrder(String topic, String payload) {
        System.out.println("[" + getName() + "] Issuing order via topic '" + topic + "': " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = from != null ? from.getName() : "System";
        System.out.println("[" + getName() + "] Received on topic '" + topic
                + "' from " + sender + ": " + payload
                + " -> Acknowledged. Adjusting mission plan.");
    }
}
