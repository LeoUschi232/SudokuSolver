package test;

import main.BacktrackingSolver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.fail;
import static test.SampleSudokus.*;

public class BacktrackingTest {

    @Test
    void backtrackingTest1() {
        testBacktrackingSolving(sudoku1);
    }

    @Test
    void backtrackingTest2() {
        testBacktrackingSolving(sudoku2);
    }

    @Test
    void backtrackingTest3() {
        testBacktrackingSolving(sudoku3);
    }

    @Test
    void backtrackingTest4() {
        testBacktrackingSolving(sudoku4);
    }

    @Test
    void backtrackingTest5() {
        testBacktrackingSolving(sudoku5);
    }

    @Test
    void backtrackingTest6() {
        testBacktrackingSolving(sudoku6);
    }

    void testBacktrackingSolving(int[][] sudoku) {
        try {
            Thread executor = Thread.currentThread();
            (new Thread(() -> {
                int[][] solvedSudoku = BacktrackingSolver.solve(sudoku);
                System.out.println(
                        "Valid sudoku solution:\n" + Arrays.stream(solvedSudoku).map(
                                row -> Arrays.toString(row).replaceAll("[\\[\\],]", "") + "\n"
                        ).collect(Collectors.joining())
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
