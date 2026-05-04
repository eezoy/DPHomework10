package com.narxoz.rpg.guild;

import java.util.Arrays;
import java.util.List;

/**
 * Guild officer responsible for route reports and reconnaissance.
 */
public class Scout extends GuildMember {

    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    @Override
    public List<String> getSubscribedTopics() {
        return Arrays.asList("scouting", "orders", "urgent");
    }

    public void reportRoute(String topic, String payload) {
        System.out.println("[" + getName() + "] Reporting via topic '" + topic + "': " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = from != null ? from.getName() : "System";
        System.out.println("[" + getName() + "] Received on topic '" + topic
                + "' from " + sender + ": " + payload
                + " -> Updating route map and marking hazards.");
    }
}
