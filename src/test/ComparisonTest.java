package test;

import main.BacktrackingSolver;
import main.SudokuSolver;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static test.SampleSudokus.*;

public class ComparisonTest {

    @Test
    void comparisonTest1() {
        testCompare(sudoku1);
    }

    @Test
    void comparisonTest2() {
        testCompare(sudoku2);
    }

    @Test
    void comparisonTest3() {
        testCompare(sudoku3);
    }

    @Test
    void comparisonTest4() {
        testCompare(sudoku4);
    }

    @Test
    void comparisonTest5() {
        testCompare(sudoku5);
    }

    @Test
    void comparisonTest6() {
        testCompare(sudoku6);
    }

    void testCompare(int[][] sudoku) {
        int[][] complexSolverSudoku = copySudoku(sudoku);
        int[][] backtrackingSolverSudoku = copySudoku(sudoku);
        complexSolverSudoku = SudokuSolver.solveSudoku(complexSolverSudoku);
        BacktrackingSolver.solve(backtrackingSolverSudoku);

        System.out.println("Complex solved sudoku:\n" + SudokuSolver.sudokuToString(complexSolverSudoku));
        System.out.println("Backtracking solved sudoku:\n" + SudokuSolver.sudokuToString(backtrackingSolverSudoku));
        assertTrue(SudokuSolver.compareSudokus(complexSolverSudoku, backtrackingSolverSudoku));
    }
}
