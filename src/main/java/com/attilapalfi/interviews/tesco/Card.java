package com.attilapalfi.interviews.tesco;

public class Card {
    private final int value;

    public Card(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return value == card.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
