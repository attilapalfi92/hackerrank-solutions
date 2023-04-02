package com.attilapalfi.hackerrank.datastructures.heap;

import java.util.*;

public class JesseAndCookies {


    public static int cookies(int k, List<Integer> A) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(A);
        int operations = 0;

        while (heap.size() >= 2 && heap.peek() < k) {
            int leastSweet = heap.poll();
            int secondLeastSweet = heap.poll();
            operations++;
            heap.offer(leastSweet + 2 * secondLeastSweet);
        }

        return heap.peek() >= k ? operations : -1;
    }
}
