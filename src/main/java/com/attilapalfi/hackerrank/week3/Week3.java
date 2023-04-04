package com.attilapalfi.hackerrank.week3;

import java.util.*;

import static com.attilapalfi.hackerrank.week3.Week3.GameState.OBSERVE;
import static com.attilapalfi.hackerrank.week3.Week3.GameState.PLANT_EMPTY_SPACES;
import static com.attilapalfi.hackerrank.week3.Week3.SpaceType.BOMB;
import static com.attilapalfi.hackerrank.week3.Week3.SpaceType.EMPTY;

public class Week3 {

    // 1
    private static char bomb = 'O';
    private static char empty = '.';

    public static List<String> bomberMan(int n, List<String> grid) {
        Cell[][] cells = new Cell[grid.size()][grid.size()];
        Map<Integer, List<Cell>> plantedBombs = new HashMap<>();
        for (int i = 0; i < grid.size(); i++) {
            char[] row = grid.get(i).toCharArray();
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 'O') {
                    Cell bomb = new Cell(BOMB, 0);
                    plantedBombs.containsKey(0);
                    cells[i][j] = bomb;
                } else {
                    cells[i][j] = new Cell(EMPTY);
                }
            }
        }


        GameState gameState = PLANT_EMPTY_SPACES;
        for (int seconds = 2; seconds <= n; seconds++) {
            if (gameState == PLANT_EMPTY_SPACES) {

            } else {

            }
            gameState = nextGameState(gameState);
        }
    }

    private static GameState nextGameState(GameState gameState) {
        return gameState == PLANT_EMPTY_SPACES ? OBSERVE : PLANT_EMPTY_SPACES;
    }

    static class Cell {
        SpaceType space;
        int bombPlantedSecond = 0;

        public Cell(SpaceType space) {
            this.space = space;
        }

        public Cell(SpaceType space, int bombPlantedSecond) {
            this.space = space;
            this.bombPlantedSecond = bombPlantedSecond;
        }
    }

    enum SpaceType {
        BOMB, EMPTY
    }

    enum GameState {
        PLANT_EMPTY_SPACES, OBSERVE
    }

}
