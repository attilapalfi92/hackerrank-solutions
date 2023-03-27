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
}
















