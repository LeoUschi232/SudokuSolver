package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SudokuSolver {
    private final Object[][] sudoku;

    public SudokuSolver(int[][] numbers) {
        checkNumbersValidity(numbers);
        sudoku = new Object[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (numbers[i][j] == 0) {
                    sudoku[i][j] = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                } else {
                    sudoku[i][j] = numbers[i][j];
                }
            }
        }
    }

    private void checkNumbersValidity(int[][] numbers) {
        HashSet<Integer> ensemble = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!ensemble.add(numbers[i][j])) {
                    throw new RuntimeException("Invalid sudoku input");
                }
            }
            ensemble.clear();
            for (int j = 0; j < 9; j++) {
                if (!ensemble.add(numbers[j][i])) {
                    throw new RuntimeException("Invalid sudoku input");
                }
            }
            ensemble.clear();
        }
        for (int i = 1; i < 9; i += 3) {
            for (int j = 1; j < 9; j += 3) {
                for (int delta_i = -1; delta_i <= 1; delta_i++) {
                    for (int delta_j = -1; delta_j <= 1; delta_j++) {
                        if (!ensemble.add(numbers[i + delta_i][j + delta_j])) {
                            throw new RuntimeException("Invalid sudoku input");
                        }
                    }
                }
                ensemble.clear();
            }
        }
    }

    public int getNumber(int x, int y) {
        return sudoku[x][y] instanceof Integer number ? number : 0;
    }

    public void removePossibleNumber(int x, int y, int number) {
        if (sudoku[x][y] instanceof ArrayList<?> possibles &&
                possibles.contains(number)) {
            possibles.remove((Integer) number);
            if (possibles.size() == 1) {
                sudoku[x][y] = possibles.get(0);
            }
        }
    }

    public boolean checkSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!(sudoku[i][j] instanceof Integer)) {
                    return false;
                }
            }
        }
        System.out.println(Arrays.toString(sudoku));
        return true;
    }

}
