package com.attilapalfi.interviews.tesco;

import java.util.*;

public class Poker {

    public HandType identifyHighestRank(List<Card> hand) {
        validateHand(hand);
        Map<Card, Integer> occurrences = fillOccurrencesMap(hand);

        if (hasFullHouse(occurrences)) {
            return HandType.FULL_HOUSE;
        }
        if (hasThreeOfAKind(occurrences)) {
            return HandType.THREE;
        }
        if (hasTwoPairs(occurrences)) {
            return HandType.TWO_PAIR;
        }
        if (hasOnePair(occurrences)) {
            return HandType.ONE_PAIR;
        }

        return HandType.NOTHING;
    }

    private void validateHand(List<Card> hand) {
        if (hand == null) {
            throw new IllegalArgumentException("Hand was null!");
        }
        if (hand.size() != 5) {
            throw new IllegalArgumentException("Invalid number of cards: " + hand.size());
        }
    }

    private boolean hasFullHouse(Map<Card, Integer> occurrences) {
        return hasOnePair(occurrences) && hasThreeOfAKind(occurrences);
    }

    private boolean hasThreeOfAKind(Map<Card, Integer> occurrences) {
        return occurrences.entrySet().stream()
                .anyMatch(entry -> entry.getValue() == 3);
    }

    private boolean hasTwoPairs(Map<Card, Integer> occurrences) {
        return occurrences.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .count() == 2;
    }

    private static boolean hasOnePair(Map<Card, Integer> occurrences) {
        return occurrences.entrySet().stream()
                .anyMatch(entry -> entry.getValue() == 2);
    }

    private static Map<Card, Integer> fillOccurrencesMap(List<Card> hand) {
        Map<Card, Integer> occurrences = new HashMap<>();
        hand.forEach(card -> {
            if (occurrences.containsKey(card)) {
                occurrences.put(card, occurrences.get(card) + 1);
            } else {
                occurrences.put(card, 1);
            }
        });
        return occurrences;
    }
}
