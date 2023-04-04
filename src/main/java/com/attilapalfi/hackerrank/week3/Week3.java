package com.attilapalfi.hackerrank.week3;

import java.util.*;
import java.util.stream.*;

public class Week3 {

    // 1
    private static Cell[][] cells;
    private static Map<Integer, List<Cell>> plantedBombs = new HashMap<>();
    private static Set<Cell> emptyCells = new HashSet<>();

    public static List<String> bomberMan(int n, List<String> grid) {
        initCellsAndPlantedBombs(grid);
        setAllNeighbors();

        GameState gameState = GameState.PLANT_BOMBS;
        for (int seconds = 2; seconds <= n; seconds++) {
            int currentSecond = seconds;
            if (gameState == GameState.PLANT_BOMBS) {
                emptyCells.stream().collect(Collectors.toList())
                        .forEach(cell -> plantBomb(currentSecond, cell));
            } else {
                int plantedSecond = currentSecond - 3;
                plantedBombs.getOrDefault(plantedSecond, Collections.emptyList())
                        .forEach(bomb -> detonateBomb(currentSecond, bomb));
            }
            gameState = nextGameState(gameState);
        }

        return Arrays.stream(cells)
                .map(row -> Arrays.stream(row).map(c -> c.spaceType == SpaceType.BOMB ? 'O' : '.')
                        .collect(Collector.of(StringBuilder::new,
                                StringBuilder::append,
                                StringBuilder::append,
                                StringBuilder::toString)
                        )
                ).collect(Collectors.toList());
    }

    private static void detonateBomb(int plantedSecond, Cell bomb) {
        bomb.spaceType = SpaceType.EMPTY;
        emptyCells.add(bomb);
        bomb.bombPlantedSecond = -1;
        bomb.neighbors.forEach(n -> {
            if (n.bombPlantedSecond != plantedSecond) {
                n.spaceType = SpaceType.EMPTY;
                n.bombPlantedSecond = -1;
                emptyCells.add(n);
            }
        });
    }

    private static void plantBomb(int currentSecond, Cell cell) {
        emptyCells.remove(cell);
        cell.bombPlantedSecond = currentSecond;
        cell.spaceType = SpaceType.BOMB;
        if (plantedBombs.containsKey(currentSecond)) {
            plantedBombs.get(currentSecond).add(cell);
        } else {
            ArrayList<Cell> bombs = new ArrayList<>();
            bombs.add(cell);
            plantedBombs.put(currentSecond, bombs);
        }
    }

    private static void setAllNeighbors() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                setNeighbor(cells[i][j], i - 1, j);
                setNeighbor(cells[i][j], i + 1, j);
                setNeighbor(cells[i][j], i, j - 1);
                setNeighbor(cells[i][j], i, j + 1);
            }
        }
    }

    private static void setNeighbor(Cell cell, int i, int j) {
        if (i >= 0 && i < cells.length && j >= 0 && j < cells[i].length) {
            cell.addNeighbor(cells[i][j]);
        }
    }

    private static void initCellsAndPlantedBombs(List<String> grid) {
        cells = new Cell[grid.size()][grid.get(0).toCharArray().length];
        for (int i = 0; i < grid.size(); i++) {
            char[] row = grid.get(i).toCharArray();
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 'O') {
                    Cell bomb = initBomb(j, i);
                    cells[i][j] = bomb;
                } else {
                    Cell empty = new Cell(j, i, SpaceType.EMPTY);
                    cells[i][j] = empty;
                    emptyCells.add(empty);
                }
            }
        }
    }

    private static Cell initBomb(int x, int y) {
        Cell bomb = new Cell(x, y, SpaceType.BOMB, 0);
        if (plantedBombs.containsKey(0)) {
            plantedBombs.get(0).add(bomb);
        } else {
            ArrayList<Cell> bombs = new ArrayList<>();
            bombs.add(bomb);
            plantedBombs.put(0, bombs);
        }
        return bomb;
    }

    private static GameState nextGameState(GameState gameState) {
        return gameState == GameState.PLANT_BOMBS ? GameState.OBSERVE : GameState.PLANT_BOMBS;
    }

    static class Cell {
        final int x;
        final int y;
        final List<Cell> neighbors = new ArrayList<>();

        SpaceType spaceType;
        int bombPlantedSecond = -1;

        public Cell(int x, int y, SpaceType spaceType) {
            this.x = x;
            this.y = y;
            this.spaceType = spaceType;
        }

        public Cell(int x, int y, SpaceType spaceType, int bombPlantedSecond) {
            this.x = x;
            this.y = y;
            this.spaceType = spaceType;
            this.bombPlantedSecond = bombPlantedSecond;
        }

        public void addNeighbor(Cell cell) {
            neighbors.add(cell);
        }

        @Override
        public String toString() {
            return spaceType == SpaceType.BOMB ? "O" : ".";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cell cell = (Cell) o;

            if (x != cell.x) return false;
            return y == cell.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    enum SpaceType {
        BOMB, EMPTY
    }

    enum GameState {
        PLANT_BOMBS, OBSERVE
    }

}
