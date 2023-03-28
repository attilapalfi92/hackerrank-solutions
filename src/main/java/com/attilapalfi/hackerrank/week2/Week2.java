package com.attilapalfi.hackerrank.week2;

import java.util.*;

public class Week2 {

    // 1
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


    // 2 stupid debugging of existing code
    public static void findZigZagSequence(int[] a, int n) {
        Arrays.sort(a);
        int mid = (n + 1) / 2 - 1;
        int temp = a[mid];
        a[mid] = a[n - 1];
        a[n - 1] = temp;

        int st = mid + 1;
        int ed = n - 2;
        while (st <= ed) {
            temp = a[st];
            a[st] = a[ed];
            a[ed] = temp;
            st = st + 1;
            ed = ed - 1;
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(a[i]);
        }
        System.out.println();
    }


    // 3
    public static int pageCount(int n, int p) {
        int turns1 = p / 2;
        final int turns2;
        if (n % 2 == 0) {
            turns2 = (n - p + 1) / 2;
        } else {
            turns2 = (n - p) / 2;
        }

        return Math.min(turns1, turns2);
    }


    // 4
    public static int towerBreakers(int n, int m) {
        return 0;
    }
}





















