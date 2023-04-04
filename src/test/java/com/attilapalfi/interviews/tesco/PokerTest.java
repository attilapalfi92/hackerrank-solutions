package com.attilapalfi.interviews.tesco;

import org.junit.jupiter.api.Test;

import java.util.*;

import static com.attilapalfi.interviews.tesco.HandType.FULL_HOUSE;
import static com.attilapalfi.interviews.tesco.HandType.NOTHING;
import static com.attilapalfi.interviews.tesco.HandType.ONE_PAIR;
import static com.attilapalfi.interviews.tesco.HandType.THREE;
import static com.attilapalfi.interviews.tesco.HandType.TWO_PAIR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PokerTest {
    private final Poker poker = new Poker();

    @Test
    void testWithOnePair() {
        List<Card> hand = List.of(
                new Card(2),
                new Card(2),
                new Card(4),
                new Card(6),
                new Card(8)
        );

        HandType result = poker.identifyHighestRank(hand);

        assertEquals(ONE_PAIR, result);
    }

    @Test
    void testWithTwoPairs() {
        List<Card> hand = List.of(
                new Card(2),
                new Card(2),
                new Card(4),
                new Card(4),
                new Card(8)
        );

        HandType result = poker.identifyHighestRank(hand);

        assertEquals(TWO_PAIR, result);
    }

    @Test
    void testWithThreeOfAKind() {
        List<Card> hand = List.of(
                new Card(2),
                new Card(2),
                new Card(2),
                new Card(6),
                new Card(8)
        );

        HandType result = poker.identifyHighestRank(hand);

        assertEquals(THREE, result);
    }

    @Test
    void testWithFullHouse() {
        List<Card> hand = List.of(
                new Card(2),
                new Card(2),
                new Card(2),
                new Card(6),
                new Card(6)
        );

        HandType result = poker.identifyHighestRank(hand);

        assertEquals(FULL_HOUSE, result);
    }

    @Test
    void testWithNothing() {
        List<Card> hand = List.of(
                new Card(2),
                new Card(4),
                new Card(6),
                new Card(8),
                new Card(10)
        );

        HandType result = poker.identifyHighestRank(hand);

        assertEquals(NOTHING, result);
    }

    @Test
    void testWith0Cards() {
        assertThrows(IllegalArgumentException.class,
                () -> poker.identifyHighestRank(Collections.emptyList()));

    }

    @Test
    void testWith6Cards() {
        List<Card> hand = List.of(
                new Card(2),
                new Card(4),
                new Card(6),
                new Card(8),
                new Card(10),
                new Card(11)
        );

        assertThrows(IllegalArgumentException.class,
                () -> poker.identifyHighestRank(hand));
    }

    @Test
    void testWithNull() {
        assertThrows(IllegalArgumentException.class,
                () -> poker.identifyHighestRank(null));
    }
}