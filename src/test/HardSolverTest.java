package test;

import main.BacktrackingSolver;
import main.SudokuSolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static test.HardSudokus.*;


public class HardSolverTest {

    @Test
    void hardSudokuTest1() {
        testHardSudokuSolving(hardSudoku1);
    }

    @Test
    void hardSudokuTest2() {
        testHardSudokuSolving(hardSudoku2);
    }

    void testHardSudokuSolving(int[][] sudoku) {
        int[][] complexSolverSudoku = EasySudokus.copySudoku(sudoku);
        int[][] backtrackingSolverSudoku = EasySudokus.copySudoku(sudoku);
        try {
            EasySolverTest.testSudokuSolving(complexSolverSudoku);
            ComparisonTest.testCompare(sudoku);
        } catch (Error | Exception error) {
            BacktrackingSolver.solve(backtrackingSolverSudoku);
            System.out.println("Failed solution attempt:\n" + SudokuSolver.sudokuToString(complexSolverSudoku));
            System.out.println("Sudoku solution:\n" + SudokuSolver.sudokuToString(backtrackingSolverSudoku));
            fail();
        }

    }


}
