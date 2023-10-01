package test;

import main.SudokuSolver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static test.EasySudokus.*;

public class EasySolverTest {

    @Test
    void sudokuTest1() {
        testSudokuSolving(easySudoku1);
    }

    @Test
    void sudokuTest2() {
        testSudokuSolving(easySudoku2);
    }

    @Test
    void sudokuTest3() {
        testSudokuSolving(easySudoku3);
    }

    @Test
    void sudokuTest4() {
        testSudokuSolving(easySudoku4);
    }

    @Test
    void sudokuTest5() {
        testSudokuSolving(easySudoku5);
    }

    @Test
    void sudokuTest6() {
        testSudokuSolving(easySudoku6);
    }

    public static void testSudokuSolving(int[][] sudoku) {
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
