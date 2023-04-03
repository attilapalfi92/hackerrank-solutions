package com.attilapalfi.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MissingSpacesTest {

    private final MissingSpaces missingSpaces = new MissingSpaces();

    @Test
    void insertSpacesILove() {
        String input = "Ilove";

        String result = missingSpaces.insertSpaces(input);

        assertEquals("I love", result);
    }

    @Test
    void insertSpacesILoveMy() {
        String input = "Ilovemy";

        String result = missingSpaces.insertSpaces(input);

        assertEquals("I love my", result);
    }

    @Test
    void insertSpacesILoveMyMother() {
        String input = "Ilovemymother";

        String result = missingSpaces.insertSpaces(input);

        assertEquals("I love my mother", result);
    }
}