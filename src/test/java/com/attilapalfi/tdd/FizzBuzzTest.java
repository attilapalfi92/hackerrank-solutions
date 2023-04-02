package com.attilapalfi.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {

    private final FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    void testFizz() {
        String result = fizzBuzz.convert(3);
        assertEquals("Fizz", result);
    }

    @Test
    void testBuzz() {
        String result = fizzBuzz.convert(5);
        assertEquals("Buzz", result);
    }

    @Test
    void testFizzBuzz() {
        String result = fizzBuzz.convert(15);
        assertEquals("FizzBuzz", result);
    }

    @Test
    void testWith4() {
        String result = fizzBuzz.convert(4);
        assertEquals("", result);
    }

    @Test
    void testWithMinus3() {
        String result = fizzBuzz.convert(-3);
        assertEquals("Fizz", result);
    }

}