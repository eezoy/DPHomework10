package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.List;

public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        System.out.println("\n--- Council Engine: Full Run ---");

        int questsTraversed = 0;
        int messagesRouted = 0;
        int membersNotified = 0;

        System.out.println("\n[Iterator 1] Ordered traversal — planning all quests:");
        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            Quest q = ordered.next();
            questsTraversed++;
            System.out.println("  Planning: " + q.getTitle());
            membersNotified += hall.dispatch("orders", null, "Planning: " + q.getTitle());
            messagesRouted++;
        }

        System.out.println("\n[Iterator 2] HIGH+ priority traversal — scouting:");
        QuestIterator priority = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (priority.hasNext()) {
            Quest q = priority.next();
            questsTraversed++;
            System.out.println("  Scouting for: " + q.getTitle());
            membersNotified += hall.dispatch("scouting", null, "Scouting for: " + q.getTitle());
            messagesRouted++;
        }

        System.out.println("\n[Iterator 3] Reverse traversal — supply run:");
        QuestIterator reverse = questLog.reverse();
        while (reverse.hasNext()) {
            Quest q = reverse.next();
            questsTraversed++;
            System.out.println("  Supplies for: " + q.getTitle());
            membersNotified += hall.dispatch("supplies", null, "Supplies for: " + q.getTitle());
            messagesRouted++;
        }

        System.out.println("\n--- Council Engine: Done ---");
        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}

