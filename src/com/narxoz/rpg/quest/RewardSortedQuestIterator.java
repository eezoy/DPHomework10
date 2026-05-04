package com.narxoz.rpg.quest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Traverses quests sorted by reward amount in descending order (highest reward first).
 *
 * Open/closed proof: adds a new traversal order without modifying any existing
 * iterator class or exposing QuestLog's internal list.
 */
public class RewardSortedQuestIterator implements QuestIterator {

    private final List<Quest> snapshot;
    private int cursor;

    public RewardSortedQuestIterator(QuestLog questLog) {
        List<Quest> sorted = new ArrayList<>(questLog.snapshot());
        sorted.sort(Comparator.comparingInt(Quest::getRewardGold).reversed());
        this.snapshot = sorted;
        this.cursor = 0;
    }

    @Override
    public boolean hasNext() {
        return cursor < snapshot.size();
    }

    @Override
    public Quest next() {
        return snapshot.get(cursor++);
    }
}
