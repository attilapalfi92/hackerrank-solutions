package com.attilapalfi.hackerrank;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.*;

public class Week1 {

    // 1
    public static void plusMinus(List<Integer> arr) {
        int positives = 0, negatives = 0, zeros = 0;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > 0) {
                positives++;
            } else if (arr.get(i) < 0) {
                negatives++;
            } else {
                zeros++;
            }
        }

        BigDecimal arraySize = BigDecimal.valueOf(arr.size());
        System.out.printf("%.6f", BigDecimal.valueOf(positives).divide(arraySize, MathContext.DECIMAL128));
        System.out.println();
        System.out.printf("%.6f", BigDecimal.valueOf(negatives).divide(arraySize, MathContext.DECIMAL128));
        System.out.println();
        System.out.printf("%.6f", BigDecimal.valueOf(zeros).divide(arraySize, MathContext.DECIMAL128));
        System.out.println();
    }


    // 2
    public static void miniMaxSum(List<Integer> arr) {
        var intArray = new Integer[arr.size()];
        arr.toArray(intArray);
        Arrays.sort(intArray);

        long min = 0, max = 0;

        for (int i = 0; i < intArray.length - 1; i++) {
            min += intArray[i];
        }
        for (int i = 1; i < intArray.length; i++) {
            max += intArray[i];
        }
        System.out.println(min + " " + max);
    }


    // 3
    public static String timeConversion(String s) {
        DateFormat inputFormat = new SimpleDateFormat("hh:mm:ssa");
        DateFormat outputFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            Date date = inputFormat.parse(s);
            return outputFormat.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    // 4
    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        return queries.stream().map(query -> (int) strings.stream()
                .filter(string -> string.equals(query))
                .count()
        ).collect(Collectors.toList());
    }


    // 5
    public static int lonelyInteger(List<Integer> input) {
        Map<Integer, Integer> integers = new HashMap<>();

        for (int i = 0; i < input.size(); i++) {
            Integer integer = input.get(i);
            if (!integers.containsKey(integer)) {
                integers.put(integer, 1);
            } else {
                integers.put(integer, 2);
            }
        }

        return integers.entrySet().stream().filter(i -> i.getValue() == 1).findFirst().get().getKey();
    }


    // 6
    public static long flippingBits(long n) {
        long ones = Long.MAX_VALUE;
        long flipped = n ^ ones;
        String lastBits = Long.toBinaryString(flipped).substring(31);
        return Long.parseLong(lastBits, 2);
    }


    // 7
    public static int diagonalDifference(List<List<Integer>> matrix) {
        long diag1 = 0, diag2 = 0;

        for (int i = 0; i < matrix.size(); i++) {
            diag1 += matrix.get(i).get(i);
            diag2 += matrix.get(i).get(matrix.size() - 1 - i);
        }

        return (int) Math.abs(diag1 - diag2);
    }


    // 8
    public static List<Integer> countingSort(List<Integer> arr) {
        int[] frequencyArray = new int[100];

        for (int i = 0; i < arr.size(); i++) {
            Integer value = arr.get(i);
            frequencyArray[value] = frequencyArray[value] + 1;
        }

        return Arrays.stream(frequencyArray).boxed().collect(Collectors.toList());
    }


    // 9
    public static String pangrams(String input) {
        Set<Character> characters = new HashSet<>();

        for (char c : input.toLowerCase().toCharArray()) {
            if (c != ' ') {
                characters.add(c);
            }
        }

        return characters.size() == 26 ? "pangram" : "not pangram";
    }



    // 10
    public static String twoArrays(int k, List<Integer> A, List<Integer> B) {
        ArrayList<Integer> aSorted = new ArrayList<>(A);
        Collections.sort(aSorted);
        ArrayList<Integer> bSorted = new ArrayList<>(B);
        Collections.sort(bSorted);

        for (int i = 0; i < A.size(); i++) {
            if (aSorted.get(0) + bSorted.get(bSorted.size() - 1) >= k) {
                aSorted.remove(0);
                bSorted.remove(bSorted.size() - 1);
            } else if (aSorted.get(aSorted.size() - 1) + bSorted.get(0) >= k) {
                aSorted.remove(aSorted.size() - 1);
                bSorted.remove(0);
            } else {
                return "NO";
            }
        }

        return "YES";
    }
}
















