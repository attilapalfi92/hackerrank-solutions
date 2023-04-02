package com.attilapalfi.hackerrank.datastructures.queues;

import java.util.*;

public class CastleOnTheGrid {

    /**
     * Grid: n * m
     * Possible solutions:
     * 1) Build a graph and use BFS
     * - O(n * m * n) + O(n * m * m) to build the graph
     * - O(n * m) to BFS the graph
     * <p>
     * <p>
     * ...
     * .X.
     * ...
     */
    public static int minimumMoves(List<String> grid, int startY, int startX, int goalY, int goalX) {
        Node[][] board = initializeBoard(grid, startX, startY, goalX, goalY);
        setRowEdges(board);
        setColumnEdges(board);
        printBoard(board);

        Queue<Node> visitQueue = new LinkedList<>();
        Node start = board[startY][startX];
        start.neighbors.forEach(n -> n.stepsFromStart = start.stepsFromStart + 1);
        visitQueue.addAll(start.neighbors);
        while (!visitQueue.isEmpty()) {
            Node next = visitQueue.poll();
            int nextSteps = next.stepsFromStart + 1;
            next.neighbors.forEach(n -> {
                if (nextSteps < n.stepsFromStart) {
                    n.stepsFromStart = nextSteps;
                    visitQueue.add(n);
                }
            });
        }

        printSteps(board);

        return board[goalY][goalX].stepsFromStart;
    }

    private static Node[][] initializeBoard(List<String> grid, int startX, int startY, int goalX, int goalY) {
        Node[][] board = new Node[grid.get(0).length()][grid.size()];

        for (int row = 0; row < grid.size(); row++) {
            char[] chars = grid.get(row).toCharArray();
            for (int column = 0; column < chars.length; column++) {
                if (chars[column] == '.') {
                    board[row][column] = new Node(NodeType.EMPTY, row, column);
                } else {
                    board[row][column] = new Node(NodeType.BLOCKED, row, column);
                }
            }
        }

        board[startY][startX].nodeType = NodeType.START;
        board[startY][startX].stepsFromStart = 0;
        board[goalY][goalX].nodeType = NodeType.GOAL;
        return board;
    }

    private static void setRowEdges(Node[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Node current = board[row][col];
                if (current.nodeType != NodeType.BLOCKED) {
                    for (int colIterator = col + 1; colIterator < board[0].length; colIterator++) {
                        Node next = board[row][colIterator];
                        if (next.nodeType == NodeType.BLOCKED) {
                            break;
                        } else {
                            current.neighbors.add(next);
                            next.neighbors.add(current);
                        }
                    }
                }
            }
        }
    }

    private static void setColumnEdges(Node[][] board) {
        for (int col = 0; col < board[0].length; col++) {
            for (int row = 0; row < board.length; row++) {
                Node current = board[row][col];
                if (current.nodeType != NodeType.BLOCKED) {
                    for (int rowIterator = row + 1; rowIterator < board.length; rowIterator++) {
                        Node next = board[rowIterator][col];
                        if (next.nodeType == NodeType.BLOCKED) {
                            break;
                        } else {
                            current.neighbors.add(next);
                            next.neighbors.add(current);
                        }
                    }
                }
            }
        }
    }

    private static void printBoard(Node[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                switch (board[row][col].nodeType) {
                    case START -> System.out.print("S");
                    case GOAL -> System.out.print("G");
                    case EMPTY -> System.out.print(".");
                    case BLOCKED -> System.out.print("X");
                }
            }
            System.out.println();
        }
    }

    private static void printSteps(Node[][] board) {
        System.out.println("\n");
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                switch (board[row][col].nodeType) {
                    case START -> System.out.print("S ");
                    case GOAL -> System.out.print("G ");
                    case EMPTY -> {
                        if (board[row][col].stepsFromStart == Integer.MAX_VALUE) {
                            System.out.print("- ");
                        } else {
                            System.out.print(board[row][col].stepsFromStart + " ");
                        }
                    }
                    case BLOCKED -> System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    static class Node implements Comparable<Node> {
        public NodeType nodeType;
        public int row;
        public int column;
        public int stepsFromStart = Integer.MAX_VALUE;

        public Set<Node> neighbors = new HashSet<>();

        public Node(NodeType nodeType, int row, int column) {
            this.nodeType = nodeType;
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (row != node.row) return false;
            return column == node.column;
        }

        @Override
        public int hashCode() {
            int result = row;
            return 31 * result + column;
        }

        @Override
        public int compareTo(Node o) {
            int diff = Integer.compare(stepsFromStart, o.stepsFromStart);
            if (diff == 0) {
                diff = Integer.compare(31 * row + column, 31 * o.row + o.column);
            }
            return diff;
        }
    }

    enum NodeType {
        EMPTY, BLOCKED, START, GOAL
    }


    public static String TEST_DATA =
            """
                    ...X......XX.X...........XX....X.XX.....
                    .X..............X...XX..X...X........X.X
                    ......X....X....X.........X...........X.
                    .X.X.X..X........X.....X.X...X.....X..X.
                    ....X.X.X...X..........X..........X.....
                    ..X......X....X....X...X....X.X.X....XX.
                    ...X....X.......X..XXX.......X.X.....X..
                    ...X.X.........X.X....X...X.........X...
                    XXXX..X......X.XX......X.X......XX.X..XX
                    .X........X....X.X......X..X....XX....X.
                    ...X.X..X.X.....X...X....X..X....X......
                    ....XX.X.....X.XX.X...X.X.....X.X.......
                    .X.X.X..............X.....XX..X.........
                    ..X...............X......X........XX...X
                    .X......X...X.XXXX.....XX...........X..X
                    ...X....XX....X...XX.X..X..X..X.....X..X
                    ...X...X.X.....X.....X.....XXXX.........
                    X.....XX..X.............X.XX.X.XXX......
                    .....X.X..X..........X.X..X...X.X......X
                    ...X.....X..X.............X......X..X..X
                    ........X.....................X....X.X..
                    ..........X.....XXX...XX.X..............
                    ........X..X..........X.XXXX..X.........
                    ..X..X...X.......XX...X.....X...XXX..X..
                    .X.......X..............X....X...X....X.
                    .................X.....X......X.....X...
                    .......X.X.XX..X.XXX.X.....X..........X.
                    X..X......X..............X..X.X.......X.
                    X........X.....X.....X....XX.......XX...
                    X.....X.................X.....X..X...XXX
                    XXX..X..X.X.XX..X....X.....XXX..X......X
                    ..........X.....X.....XX................
                    ..X.........X..X.........X...X.....X....
                    .X.X....X...XX....X...............X.....
                    .X....X....XX.XX........X..X............
                    X...X.X................XX......X..X.....
                    ..X.X.......X.X..X.....XX.........X..X..
                    ........................X..X.XX..X......
                    ........X..X.X.....X.....X......X.......
                    .X..X....X.X......XX....................""";
}




















