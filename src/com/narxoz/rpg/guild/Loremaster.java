package com.narxoz.rpg.guild;

import java.util.Arrays;
import java.util.List;

public class Loremaster extends GuildMember {

    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    @Override
    public List<String> getSubscribedTopics() {
        return Arrays.asList("lore", "curse", "urgent");
    }

    public void shareKnowledge(String topic, String payload) {
        System.out.println("[" + getName() + "] Sharing knowledge via topic '" + topic + "': " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = from != null ? from.getName() : "System";
        System.out.println("[" + getName() + "] Received on topic '" + topic + "' from " + sender + ": " + payload + " -> Consulting ancient tomes and recording findings.");
    }
}
