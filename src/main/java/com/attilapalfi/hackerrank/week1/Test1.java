package com.attilapalfi.hackerrank.week1;

import java.util.*;

public class Test1 {

    // 1
    public static int findMedian(List<Integer> arr) {
        List<Integer> sorted = new ArrayList<>(arr);
        Collections.sort(sorted);
        return sorted.get(sorted.size() / 2);
    }


    // 2
    public static int flippingMatrix(List<List<Integer>> matrix) {
        int n = matrix.size() / 2;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += getMaxFor(i, j, matrix);
            }
        }
        return sum;
    }

    public static int getMaxFor(int row, int col, List<List<Integer>> matrix) {
        int max = Integer.MIN_VALUE;
        max = Math.max(max, matrix.get(row).get(col));
        max = Math.max(max, matrix.get(matrix.size() - 1 - row).get(col));
        max = Math.max(max, matrix.get(row).get(matrix.size() - 1 - col));
        max = Math.max(max, matrix.get(matrix.size() - 1 - row).get(matrix.size() -1 - col));
        return max;
    }


    public static int flippingMatrix2(List<List<Integer>> matrix) {
        List<List<Integer>> original = toMutable(matrix);

        List<List<Integer>> mutable = toMutable(original);
        int maxSum = getUpperLeftQuadrantSum(mutable);
        for (int i = 0; i < matrix.size(); i++) {
            mutable = reverseRow(mutable, i);
            maxSum = Math.max(getUpperLeftQuadrantSum(mutable), maxSum);
        }

        mutable = toMutable(original);
        for (int i = 0; i < matrix.size(); i++) {
            mutable = reverseColumn(mutable, i);
            maxSum = Math.max(getUpperLeftQuadrantSum(mutable), maxSum);
        }

        mutable = toMutable(original);
        for (int i = 0; i < matrix.size(); i++) {
            mutable = reverseRow(mutable, i);
            maxSum = Math.max(getUpperLeftQuadrantSum(mutable), maxSum);
            for (int j = 0; j < matrix.size(); j++) {
                mutable = reverseColumn(mutable, j);
                maxSum = Math.max(getUpperLeftQuadrantSum(mutable), maxSum);
            }
        }

        mutable = toMutable(original);
        for (int i = 0; i < matrix.size(); i++) {
            mutable = reverseColumn(mutable, i);
            maxSum = Math.max(getUpperLeftQuadrantSum(mutable), maxSum);
            for (int j = 0; j < matrix.size(); j++) {
                mutable = reverseRow(mutable, j);
                maxSum = Math.max(getUpperLeftQuadrantSum(mutable), maxSum);
            }
        }

        return maxSum;
    }

    static List<List<Integer>> reverseRow(List<List<Integer>> reversed, int row) {
        List<Integer> newRow = new ArrayList<>();
        for (int i = 0; i < reversed.size(); i++) {
            newRow.add(0, reversed.get(row).get(i));
        }
        reversed.remove(row);
        reversed.add(row, newRow);
        return reversed;
    }

    static List<List<Integer>> reverseColumn(List<List<Integer>> reversed, int col) {
        List<Integer> column = new ArrayList<>();
        for (int i = 0; i < reversed.size(); i++) {
            column.add(0, reversed.get(i).get(col));
        }
        for (int i = 0; i < reversed.size(); i++) {
            reversed.get(i).remove(col);
            reversed.get(i).add(col, column.get(i));
        }

        return reversed;
    }

    private static List<List<Integer>> toMutable(List<List<Integer>> matrix) {
        List<List<Integer>> mutable = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            List<Integer> rowCopy = new ArrayList<>(matrix.get(i));
            mutable.add(rowCopy);
        }
        return mutable;
    }

    private static int getUpperLeftQuadrantSum(List<List<Integer>> matrix) {
        int n = matrix.size() / 2;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += matrix.get(i).get(j);
            }
        }
        return sum;
    }
}
