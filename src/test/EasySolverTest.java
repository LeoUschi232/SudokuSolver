package test;

import main.SudokuSolver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static test.SampleSudokus.*;

public class EasySolverTest {

    @Test
    void sudokuTest1() {
        testSudokuSolving(sudoku1);
    }

    @Test
    void sudokuTest2() {
        testSudokuSolving(sudoku2);
    }

    @Test
    void sudokuTest3() {
        testSudokuSolving(sudoku3);
    }

    @Test
    void sudokuTest4() {
        testSudokuSolving(sudoku4);
    }

    @Test
    void sudokuTest5() {
        testSudokuSolving(sudoku5);
    }

    @Test
    void sudokuTest6() {
        testSudokuSolving(sudoku6);
    }

    void testSudokuSolving(int[][] sudoku) {
        try {
            Thread executor = Thread.currentThread();
            (new Thread(() -> {
                int[][] solvedSudoku = SudokuSolver.solveSudoku(sudoku);
                System.out.println(
                        "Solved Sudoku:\n" + SudokuSolver.sudokuToString(solvedSudoku)
                );
                executor.interrupt();
            })).start();
            Thread.sleep(1000);
            fail("Couldn't solve sudoku in less than 1 second");
        } catch (InterruptedException interruptedException) {
            System.out.println("\n Test executed successfully");
        }
    }
}
