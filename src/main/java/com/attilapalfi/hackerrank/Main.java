package com.attilapalfi.hackerrank;

import com.attilapalfi.hackerrank.datastructures.stacks.BalancedBrackets;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> testData = Arrays.asList(BalancedBrackets.TEST_DATA);
        List<String> results = Arrays.asList(BalancedBrackets.TEST_RESULTS);
        for (int i = 0; i < testData.size(); i++) {
            String balanced = BalancedBrackets.isBalanced(testData.get(i));
            if (!balanced.equals(results.get(i))) {
                System.out.println("Failed at " + i + ". Test: " + testData.get(i));
            }
        }



//        var result = Week1.twoArrays(5, List.of(2, 1, 3), List.of(7, 8, 9));
//        var result = Week1.birthday(List.of(4), 4, 1);
//        var result = Week1.stringsXOR("10101", "00101");

//        List<List<Integer>> matrix = List.of(
//                List.of(112, 42, 83, 119),
//                List.of(56, 125, 56, 49),
//                List.of(15, 78, 101, 43),
//                List.of(62, 98, 114, 109));
//        var result = Test1.flippingMatrix(matrix);
//        System.out.println(result);

//        var result = ArrayManipulation.arrayManipulation(10, List.of(
//                List.of(2, 6, 8),
//                List.of(3, 5, 7),
//                List.of(1, 8, 1),
//                List.of(5, 9, 15)
//        ));
//        System.out.println(result);
    }
}