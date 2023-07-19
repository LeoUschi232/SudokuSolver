package test;

import main.SudokuSolver;
import org.junit.jupiter.api.Test;

public class ValidityCheckTest {
    private final static int[][] sudoku1 = new int[][]{
            new int[]{0, 0, 8, 1, 0, 7, 0, 0, 0},
            new int[]{6, 9, 7, 0, 0, 0, 3, 4, 1},
            new int[]{2, 4, 0, 0, 3, 0, 5, 8, 0},
            new int[]{1, 7, 0, 0, 9, 0, 0, 0, 0},
            new int[]{5, 3, 0, 4, 0, 0, 0, 2, 9},
            new int[]{8, 2, 0, 7, 0, 0, 1, 5, 0},
            new int[]{9, 0, 2, 3, 5, 1, 0, 0, 8},
            new int[]{0, 8, 0, 0, 7, 0, 9, 1, 0},
            new int[]{0, 0, 3, 0, 0, 0, 2, 0, 5}
    };
    private final static int[][] sudoku2 = new int[][]{
            new int[]{9, 4, 7, 0, 6, 8, 1, 0, 0},
            new int[]{6, 5, 0, 0, 0, 4, 8, 0, 7},
            new int[]{8, 0, 0, 0, 0, 0, 0, 0, 6},
            new int[]{0, 0, 0, 0, 8, 0, 6, 2, 0},
            new int[]{0, 1, 6, 9, 2, 0, 0, 0, 0},
            new int[]{2, 0, 0, 0, 4, 6, 3, 0, 5},
            new int[]{0, 0, 4, 0, 0, 5, 0, 0, 8},
            new int[]{0, 0, 0, 0, 0, 9, 0, 6, 1},
            new int[]{0, 0, 5, 8, 0, 2, 9, 0, 3}
    };
    private final static int[][] sudoku3 = new int[][]{
            new int[]{0, 1, 8, 7, 0, 3, 0, 9, 0},
            new int[]{0, 0, 4, 2, 6, 1, 8, 7, 0},
            new int[]{0, 0, 0, 0, 5, 0, 0, 6, 0},
            new int[]{0, 4, 5, 0, 9, 2, 0, 3, 0},
            new int[]{0, 6, 7, 0, 0, 0, 4, 8, 9},
            new int[]{8, 0, 0, 4, 0, 0, 0, 2, 1},
            new int[]{5, 9, 6, 0, 0, 7, 3, 0, 8},
            new int[]{0, 7, 1, 6, 8, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 3, 0, 0, 0, 0}
    };
    private final static int[][] sudoku4 = new int[][]{
            new int[]{0, 4, 0, 0, 1, 8, 9, 7, 3},
            new int[]{9, 0, 0, 3, 0, 5, 1, 2, 0},
            new int[]{0, 1, 0, 0, 2, 4, 0, 0, 6},
            new int[]{1, 7, 0, 4, 0, 2, 0, 0, 0},
            new int[]{6, 5, 0, 0, 0, 1, 0, 0, 0},
            new int[]{2, 0, 4, 8, 5, 6, 7, 0, 1},
            new int[]{0, 0, 3, 5, 4, 0, 2, 1, 0},
            new int[]{7, 0, 0, 0, 8, 3, 0, 0, 0},
            new int[]{4, 0, 5, 0, 6, 0, 3, 0, 0}
    };
    private final static int[][] sudoku5 = new int[][]{
            new int[]{4, 0, 0, 5, 0, 0, 7, 0, 0},
            new int[]{2, 0, 7, 0, 3, 0, 0, 0, 0},
            new int[]{5, 0, 0, 7, 1, 2, 6, 0, 4},
            new int[]{0, 4, 3, 2, 0, 7, 9, 5, 1},
            new int[]{9, 0, 2, 1, 4, 6, 3, 8, 7},
            new int[]{0, 7, 0, 9, 0, 0, 0, 4, 6},
            new int[]{1, 2, 0, 0, 0, 0, 0, 6, 0},
            new int[]{3, 6, 4, 8, 2, 0, 0, 7, 0},
            new int[]{7, 0, 5, 4, 0, 0, 1, 2, 3}
    };

    @Test
    void validityCheckTest1() {
        SudokuSolver.checkNumbersValidity(sudoku1);
        SudokuSolver.checkNumbersValidity(sudoku2);
        SudokuSolver.checkNumbersValidity(sudoku3);
        SudokuSolver.checkNumbersValidity(sudoku4);
        SudokuSolver.checkNumbersValidity(sudoku5);
    }
}
