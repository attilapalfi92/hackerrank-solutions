package com.attilapalfi.tdd;

import java.util.*;

public class MissingSpaces {

    private final Set<String> dictionary = new HashSet<>();

    public MissingSpaces() {
        dictionary.add("I");
        dictionary.add("love");
        dictionary.add("my");
        dictionary.add("mother");
    }

    public String insertSpaces(String input) {
        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (int start = 0; start < chars.length; start++) {
            for (int end = start + 1; end <= chars.length; end++) {
                char[] subArray = Arrays.copyOfRange(chars, start, end);
                String subString = new String(subArray);
                if (dictionary.contains(subString)) {
                    result.append(subString).append(" ");
                    start = end - 1;
                    break;
                }
            }
        }
        return result.toString().trim();
    }
}
