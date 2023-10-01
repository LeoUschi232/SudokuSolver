package test;

import main.BacktrackingSolver;
import main.SudokuSolver;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.fail;
import static test.EasySudokus.*;

public class BacktrackingTest {

    @Test
    void backtrackingTest1() {
        testBacktrackingSolving(easySudoku1);
    }

    @Test
    void backtrackingTest2() {
        testBacktrackingSolving(easySudoku2);
    }

    @Test
    void backtrackingTest3() {
        testBacktrackingSolving(easySudoku3);
    }

    @Test
    void backtrackingTest4() {
        testBacktrackingSolving(easySudoku4);
    }

    @Test
    void backtrackingTest5() {
        testBacktrackingSolving(easySudoku5);
    }

    @Test
    void backtrackingTest6() {
        testBacktrackingSolving(easySudoku6);
    }

    void testBacktrackingSolving(int[][] sudoku) {
        try {
            Thread executor = Thread.currentThread();
            (new Thread(() -> {
                int[][] solvedSudoku = BacktrackingSolver.solve(sudoku);
                System.out.println(
                        "Solved Sudoku:\n" + SudokuSolver.sudokuToString(solvedSudoku)
                );
                executor.interrupt();
            })).start();
            Thread.sleep(2000);
            fail("Sudoku solving timed out.\nIs the backtracking algorithm taking too long?");
        } catch (InterruptedException interruptedException) {
            System.out.println("\n Backtracking algorithm executed successfully");
        }
    }
}
