package com.narxoz.rpg.guild;

import java.util.Arrays;
import java.util.List;

/**
 * Guild officer responsible for wounds, potions, and recovery plans.
 */
public class Healer extends GuildMember {

    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    @Override
    public List<String> getSubscribedTopics() {
        return Arrays.asList("healing", "urgent");
    }

    public void prepareAid(String topic, String payload) {
        System.out.println("[" + getName() + "] Preparing aid via topic '" + topic + "': " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = from != null ? from.getName() : "System";
        System.out.println("[" + getName() + "] Received on topic '" + topic
                + "' from " + sender + ": " + payload
                + " -> Preparing potions and triage supplies.");
    }
}
