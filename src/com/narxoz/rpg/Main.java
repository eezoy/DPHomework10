package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.Captain;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.Healer;
import com.narxoz.rpg.guild.Quartermaster;
import com.narxoz.rpg.guild.Scout;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.Arrays;
import java.util.List;

/**
 * Entry point for Homework 10 — The Adventurers' Guild: Iterator + Mediator.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===\n");

        Hero garen = new Hero("Garen", 120, 30, 45, 15, 500);
        Hero sion = new Hero("Sion", 90, 50, 40, 10, 300);
        List<Hero> party = Arrays.asList(garen, sion);

        System.out.println("--- Party ---");
        for (Hero h : party) {
            System.out.println("  " + h.getName() + " (HP:" + h.getHp() + " ATK:" + h.getAttackPower() + ")");
        }

        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Goblin Raid at Westbridge", QuestPriority.NORMAL, 150, false));
        questLog.add(new Quest("Escort the Merchant Convoy", QuestPriority.LOW, 80, false));
        questLog.add(new Quest("Dragon Sighting at Mt. Doom", QuestPriority.URGENT, 1000, true));
        questLog.add(new Quest("Recover the Stolen Relic", QuestPriority.HIGH, 400, false));
        questLog.add(new Quest("Cursed Forest Investigation", QuestPriority.HIGH, 350, false));
        questLog.add(new Quest("Tavern Brawl Mediation", QuestPriority.LOW, 50, false));

        System.out.println("\n--- Guild Hall Registration ---");
        GuildHall hall = new GuildHall();
        Quartermaster quartermaster = new Quartermaster("Gimli the Quartermaster", hall);
        Scout scout = new Scout("Legolas the Scout", hall);
        Healer healer = new Healer("Elrond the Healer", hall);
        Captain captain = new Captain("Gandalf the Captain", hall);
        System.out.println("  Registered: " + quartermaster.getName());
        System.out.println("  Registered: " + scout.getName());
        System.out.println("  Registered: " + healer.getName());
        System.out.println("  Registered: " + captain.getName());

        System.out.println("\n--- Iterator 1: Arrival Order Traversal ---");
        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            Quest q = ordered.next();
            System.out.println("  [ORDERED] " + q.getTitle() + " | " + q.getPriority() + " | " + q.getRewardGold() + "g");
        }

        System.out.println("\n--- Iterator 2: Reverse Order Traversal ---");
        QuestIterator reverse = questLog.reverse();
        while (reverse.hasNext()) {
            Quest q = reverse.next();
            System.out.println("  [REVERSE] " + q.getTitle() + " | " + q.getPriority() + " | " + q.getRewardGold() + "g");
        }

        System.out.println("\n--- Iterator 3: HIGH+ Priority Quests Only ---");
        QuestIterator highPri = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (highPri.hasNext()) {
            Quest q = highPri.next();
            System.out.println("  [PRIORITY] " + q.getTitle() + " | " + q.getPriority());
        }

        System.out.println("\n--- Mediator Demo: Guild Coordination ---");
        captain.issueOrder("orders", "All units prepare for the Dragon Sighting mission.");
        System.out.println();
        scout.reportRoute("scouting", "Northern pass is clear; eastern ridge has troll activity.");
        System.out.println();
        healer.prepareAid("healing", "Stocking 20 healing potions for the high-priority quests.");
        System.out.println();
        quartermaster.requestSupplies("urgent", "Emergency rations needed — Dragon mission is URGENT.");

        System.out.println("\n--- Running Council Engine ---");
        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(party, questLog, hall);

        System.out.println("\n=== Council Run Complete ===");
        System.out.println("  Quests traversed : " + result.getQuestsTraversed());
        System.out.println("  Messages routed  : " + result.getMessagesRouted());
        System.out.println("  Members notified : " + result.getMembersNotified());
        System.out.println("=== End of Demo ===");
    }
}

