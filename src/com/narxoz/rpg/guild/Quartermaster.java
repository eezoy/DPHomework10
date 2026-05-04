package com.narxoz.rpg.guild;

import java.util.Arrays;
import java.util.List;

public class Quartermaster extends GuildMember {

    public Quartermaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    @Override
    public List<String> getSubscribedTopics() {
        return Arrays.asList("supplies", "rewards", "urgent");
    }

    public void requestSupplies(String topic, String payload) {
        System.out.println("[" + getName() + "] Requesting via topic '" + topic + "': " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = from != null ? from.getName() : "System";
        System.out.println("[" + getName() + "] Received on topic '" + topic + "' from " + sender + ": " + payload + " -> Checking inventory and adjusting supplies.");
    }
}
