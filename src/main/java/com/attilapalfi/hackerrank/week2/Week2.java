package com.attilapalfi.hackerrank.week2;

import java.util.*;

public class Week2 {

    public static int sockMerchant(int n, List<Integer> socks) {
        HashMap<Integer, Integer> socksByColor = new HashMap<>();
        for (Integer sock : socks) {
//            socksByColor.merge(sock, 1, Integer::sum);
            int count = socksByColor.getOrDefault(sock, 0);
            count++;
            socksByColor.put(sock, count);
        }

        return socksByColor.values()
                .stream()
                .mapToInt(v -> v / 2)
                .sum();
    }
}
