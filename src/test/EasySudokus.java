package test;

import java.util.Arrays;

public class EasySudokus {
    public final static int[][] easySudoku1 = {
            {0, 0, 8, 1, 0, 7, 0, 0, 0},
            {6, 9, 7, 0, 0, 0, 3, 4, 1},
            {2, 4, 0, 0, 3, 0, 5, 8, 0},
            {1, 7, 0, 0, 9, 0, 0, 0, 0},
            {5, 3, 0, 4, 0, 0, 0, 2, 9},
            {8, 2, 0, 7, 0, 0, 1, 5, 0},
            {9, 0, 2, 3, 5, 1, 0, 0, 8},
            {0, 8, 0, 0, 7, 0, 9, 1, 0},
            {0, 0, 3, 0, 0, 0, 2, 0, 5}
    };
    public final static int[][] easySudoku2 = {
            {9, 4, 7, 0, 6, 8, 1, 0, 0},
            {6, 5, 0, 0, 0, 4, 8, 0, 7},
            {8, 0, 0, 0, 0, 0, 0, 0, 6},
            {0, 0, 0, 0, 8, 0, 6, 2, 0},
            {0, 1, 6, 9, 2, 0, 0, 0, 0},
            {2, 0, 0, 0, 4, 6, 3, 0, 5},
            {0, 0, 4, 0, 0, 5, 0, 0, 8},
            {0, 0, 0, 0, 0, 9, 0, 6, 1},
            {0, 0, 5, 8, 0, 2, 9, 0, 3}
    };
    public final static int[][] easySudoku3 = {
            {0, 1, 8, 7, 0, 3, 0, 9, 0},
            {0, 0, 4, 2, 6, 1, 8, 7, 0},
            {0, 0, 0, 0, 5, 0, 0, 6, 0},
            {0, 4, 5, 0, 9, 2, 0, 3, 0},
            {0, 6, 7, 0, 0, 0, 4, 8, 9},
            {8, 0, 0, 4, 0, 0, 0, 2, 1},
            {5, 9, 6, 0, 0, 7, 3, 0, 8},
            {0, 7, 1, 6, 8, 0, 0, 0, 0},
            {0, 0, 0, 0, 3, 0, 0, 0, 0}
    };
    public final static int[][] easySudoku4 = {
            {0, 4, 0, 0, 1, 8, 9, 7, 3},
            {9, 0, 0, 3, 0, 5, 1, 2, 0},
            {0, 1, 0, 0, 2, 4, 0, 0, 6},
            {1, 7, 0, 4, 0, 2, 0, 0, 0},
            {6, 5, 0, 0, 0, 1, 0, 0, 0},
            {2, 0, 4, 8, 5, 6, 7, 0, 1},
            {0, 0, 3, 5, 4, 0, 2, 1, 0},
            {7, 0, 0, 0, 8, 3, 0, 0, 0},
            {4, 0, 5, 0, 6, 0, 3, 0, 0}
    };
    public final static int[][] easySudoku5 = {
            {4, 0, 0, 5, 0, 0, 7, 0, 0},
            {2, 0, 7, 0, 3, 0, 0, 0, 0},
            {5, 0, 0, 7, 1, 2, 6, 0, 4},
            {0, 4, 3, 2, 0, 7, 9, 5, 1},
            {9, 0, 2, 1, 4, 6, 3, 8, 7},
            {0, 7, 0, 9, 0, 0, 0, 4, 6},
            {1, 2, 0, 0, 0, 0, 0, 6, 0},
            {3, 6, 4, 8, 2, 0, 0, 7, 0},
            {7, 0, 5, 4, 0, 0, 1, 2, 3}
    };
    public final static int[][] easySudoku6 = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    /**
     * Copies a sudoku into a new object
     * May throw a RuntimeException if the sudoku is invalid
     *
     * @param sudoku the sudoku to copy
     * @return the copy of the sudoku
     */
    public static int[][] copySudoku(int[][] sudoku) {
        final int[][] copy = new int[9][];
        for (int i = 0; i < 9; i++) {
            copy[i] = Arrays.copyOf(sudoku[i], sudoku[i].length);
        }
        return copy;
    }

}
