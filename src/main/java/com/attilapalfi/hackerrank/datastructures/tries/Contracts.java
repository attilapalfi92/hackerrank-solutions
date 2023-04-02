package com.attilapalfi.hackerrank.datastructures.tries;

import java.util.*;

public class Contracts {

    /*
    add ed
    add eddie
    add edward
    find ed
    add edwina
    find edw
    find a
     */
    public static List<Integer> contacts(List<List<String>> queries) {
        Storage storage = new Storage();
        List<Integer> results = new ArrayList<>();

        queries.forEach(query -> {
            if (query.get(0).equalsIgnoreCase("add")) {
                storage.add(query.get(1));
            } else {
                int matches = storage.find(query.get(1));
                results.add(matches);
            }
        });

        return results;
    }

    public static class Storage {
        Map<Character, Node> root = new HashMap<>();

        int find(String name) {
            char[] chars = name.toCharArray();
            int results = 0;
            char c = chars[0];
            Node currentNode = root.getOrDefault(c, null);

            if (chars.length == 1) {
                if (currentNode != null) {
                    return countMatches(currentNode);
                } else {
                    return 0;
                }
            }

            for (int i = 1; i < chars.length && currentNode != null; i++) {
                c = chars[i];
                if (i < chars.length - 1) {
                    currentNode = currentNode.nextNodes.getOrDefault(c, null);
                } else {
                    currentNode = currentNode.nextNodes.getOrDefault(c, null);
                    if (currentNode != null) {
                        results = countMatches(currentNode);
                    }
                }
            }

            return results;
        }

        private static int countMatches(Node currentNode) {
            int results = 0;
            Queue<Node> matching = new LinkedList<>();
            matching.offer(currentNode);
            while (!matching.isEmpty()) {
                Node n = matching.poll();
                if (n.fullName) {
                    results++;
                }
                matching.addAll(n.nextNodes.values());
            }
            return results;
        }


        void add(String name) {
            char[] chars = name.toCharArray();
            Map<Character, Node> currentNodes = root;
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                Node node;
                if (currentNodes.containsKey(c)) {
                    node = currentNodes.get(c);
                } else {
                    node = new Node(c);
                    currentNodes.put(c, node);
                }
                currentNodes = node.nextNodes;
                if (i == chars.length - 1) {
                    node.fullName = true;
                }
            }
        }
    }

    public static class Node {
        char character;
        boolean fullName = false;

        Map<Character, Node> nextNodes = new HashMap<>();

        public Node(char c) {
            this.character = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return character == node.character;
        }

        @Override
        public int hashCode() {
            return character;
        }
    }

}
