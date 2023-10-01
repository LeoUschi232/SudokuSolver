package test;

import main.SudokuSolver;
import org.junit.jupiter.api.Test;

import static test.EasySudokus.*;

public class ValidityCheckTest {


    @Test
    void validityCheckTest1() {
        SudokuSolver.checkNumbersValidity(easySudoku1);
        SudokuSolver.checkNumbersValidity(easySudoku2);
        SudokuSolver.checkNumbersValidity(easySudoku3);
        SudokuSolver.checkNumbersValidity(easySudoku4);
        SudokuSolver.checkNumbersValidity(easySudoku5);
        SudokuSolver.checkNumbersValidity(easySudoku6);
    }
}
