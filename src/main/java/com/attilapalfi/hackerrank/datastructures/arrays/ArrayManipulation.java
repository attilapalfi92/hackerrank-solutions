package com.attilapalfi.hackerrank.datastructures.arrays;

import java.util.*;

public class ArrayManipulation {

    /*
     * Complete the 'arrayManipulation' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */

    // my solution:
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        // Write your code here
        long[] array = new long[n];
        long max = Long.MIN_VALUE;

        for (int i = 0; i < queries.size(); i++) {
            int startIndex = queries.get(i).get(0) - 1;
            int until = queries.get(i).get(1);
            int increment = queries.get(i).get(2);

            for (int j = startIndex; j < until; j++) {
                array[j] += increment;
                if (array[j] > max) {
                    max = array[j];
                }
            }
        }

        return max;
    }

    // ideal solution (clever):
    // https://stackoverflow.com/a/55882427
    static long arrayManipulation2(int n, List<List<Integer>> queries) {

        long[] prefixSumArray = new long[n + 2];
        for (int i = 0; i < queries.size(); i++) {
            int a = queries.get(i).get(0) - 1;
            int b = queries.get(i).get(1);
            int k = queries.get(i).get(2);
            prefixSumArray[a] += k;
            prefixSumArray[b] -= k;
        }
        return getMax(prefixSumArray);
    }

    private static long getMax(long[] prefixSumArray) {
        long max = Long.MIN_VALUE;
        long sum = 0;
        for (int i = 0; i < prefixSumArray.length; i++) {
            sum += prefixSumArray[i];
            max = Math.max(max, sum);
        }
        return max;
    }
}
