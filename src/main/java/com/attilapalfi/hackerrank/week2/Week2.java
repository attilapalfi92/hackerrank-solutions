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


    /**
     * If it is possible for P2 to mirror the moves of P1 then P2 will always win.
     * It doesn't matter if P1 divides first or reduces to 1 (P2 will always win).
     * The only way that P2 can't win is if there is an odd number of towers.
     * With an odd number of towers P2 cannot mirror P1 and thus looses.
     */
    // 4
    public static int towerBreakers(int n, int m) {
        if (m == 1) {
            return 2;
        }
        return n % 2 == 0 ? 2 : 1;
    }


    public static int towerBreakers2(int n, int m) {
        List<Integer> towers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            towers.add(m);
        }
        int player = 1;

        while (!towers.isEmpty() && towers.get(towers.size() - 1) != 1) {
            int y;
            if (towers.size() % 2 == 0) {
                int x = towers.get(towers.size() - 1);
                y = largestDivisor(x);
            } else {
                y = 1;
            }
            towers.remove(towers.size() - 1);

            if (y > 1) {
                int position = Collections.binarySearch(towers, y);
                if (position >= 0) {
                    towers.add(position, y);
                } else {
                    towers.add(-position - 1, y);
                }
            }

            player = nextPlayer(player);
        }

        return nextPlayer(player);
    }

    private static int largestDivisor(int x) {
        for (int i = x / 2; i >= 2; i--) {
            if (x % i == 0) {
                return i;
            }
        }
        return 1;
    }

    private static int nextPlayer(int lastPlayer) {
        return lastPlayer == 1 ? 2 : 1;
    }


    // 5
    public static String caesarCipher(String s, int k) {
        int diff = 'z' - 'a' + 1;
        k = k % diff;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                int added = c + k;
                if (added > 'z') {
                    added = added - ('z' - 'a' + 1);
                }
                chars[i] = (char) added;
            } else if (c >= 'A' && c <= 'Z') {
                int added = c + k;
                if (added > 'Z') {
                    added = added - ('Z' - 'A' + 1);
                }
                chars[i] = (char) added;
            }
        }
        return new String(chars);
    }
}





















