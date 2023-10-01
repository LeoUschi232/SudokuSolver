package test;

import main.BacktrackingSolver;
import main.SudokuSolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static test.EasySudokus.*;

public class ComparisonTest {

    @Test
    void comparisonTest1() {
        testCompare(easySudoku1);
    }

    @Test
    void comparisonTest2() {
        testCompare(easySudoku2);
    }

    @Test
    void comparisonTest3() {
        testCompare(easySudoku3);
    }

    @Test
    void comparisonTest4() {
        testCompare(easySudoku4);
    }

    @Test
    void comparisonTest5() {
        testCompare(easySudoku5);
    }

    @Test
    void comparisonTest6() {
        testCompare(easySudoku6);
    }

    public static void testCompare(int[][] sudoku) {
        int[][] complexSolverSudoku = copySudoku(sudoku);
        int[][] backtrackingSolverSudoku = copySudoku(sudoku);
        complexSolverSudoku = SudokuSolver.solveSudoku(complexSolverSudoku);
        BacktrackingSolver.solve(backtrackingSolverSudoku);

        System.out.println("Complex solved sudoku:\n" + SudokuSolver.sudokuToString(complexSolverSudoku));
        System.out.println("Backtracking solved sudoku:\n" + SudokuSolver.sudokuToString(backtrackingSolverSudoku));
        assertTrue(SudokuSolver.compareSudokus(complexSolverSudoku, backtrackingSolverSudoku));
    }
}
