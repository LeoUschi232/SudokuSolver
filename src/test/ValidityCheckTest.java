package test;

import main.SudokuSolver;
import org.junit.jupiter.api.Test;

public class ValidityCheckTest {
    @Test
    void validityCheckTest1() {
        int[][] sudoku = new int[][]{
                new int[]{0, 0, 8, 1, 0, 7, 0, 0, 0},
                new int[]{6, 9, 7, 0, 0, 0, 3, 4, 1},
                new int[]{2, 4, 0, 0, 3, 0, 5, 8, 0},
                new int[]{1, 7, 0, 0, 9, 0, 0, 0, 0},
                new int[]{5, 4, 0, 4, 0, 0, 0, 2, 9},
                new int[]{8, 2, 0, 7, 0, 0, 1, 5, 0},
                new int[]{9, 0, 2, 3, 5, 1, 0, 0, 8},
                new int[]{0, 8, 0, 0, 7, 0, 9, 1, 0},
                new int[]{0, 0, 3, 0, 0, 0, 2, 0, 5}
        };
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);
    }
}
