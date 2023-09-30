package test;

import main.SudokuSolver;
import org.junit.jupiter.api.Test;

import static test.SampleSudokus.*;

public class EasySolverTest {

    @Test
    void validityCheckTest1() {
        SudokuSolver.checkNumbersValidity(sudoku1);
        SudokuSolver.checkNumbersValidity(sudoku2);
        SudokuSolver.checkNumbersValidity(sudoku3);
        SudokuSolver.checkNumbersValidity(sudoku4);
        SudokuSolver.checkNumbersValidity(sudoku5);
        SudokuSolver.checkNumbersValidity(sudoku6);
    }

}
