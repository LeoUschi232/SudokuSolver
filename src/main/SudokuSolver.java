package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SudokuSolver {
    private final Object[][] sudoku;

    private SudokuSolver(int[][] numbers) {
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


    public static void checkNumbersValidity(int[][] numbers) {
        HashSet<Integer> rowSet = new HashSet<>();
        HashSet<Integer> colSet = new HashSet<>();
        HashSet<Integer> boxSet = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int number = numbers[i][j];
                if (number != 0 && !rowSet.add(number)) {
                    throw new RuntimeException("Invalid sudoku input on field [" + i + "][" + j + "]");
                }
                number = numbers[j][i];
                if (number != 0 && !colSet.add(number)) {
                    throw new RuntimeException("Invalid sudoku input on field [" + j + "][" + i + "]");
                }
            }
            rowSet.clear();
            colSet.clear();
        }

        for (int i = 1; i < 9; i += 3) {
            for (int j = 1; j < 9; j += 3) {
                for (int delta_i = -1; delta_i <= 1; delta_i++) {
                    for (int delta_j = -1; delta_j <= 1; delta_j++) {
                        int number = numbers[i + delta_i][j + delta_j];
                        if (number != 0 && !boxSet.add(number)) {
                            throw new RuntimeException("Invalid sudoku input on field [" + (i + delta_i) + "][" + (j + delta_j) + "]");
                        }
                    }
                }
                boxSet.clear();
            }
        }
    }

    public static int[][] solveSudoku(int[][] sudoku) {
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);
        sudokuSolver.solve();
        if (sudokuSolver.checkSudoku()) {
            return sudokuSolver.getSudoku();
        }
        throw new RuntimeException("Unable to solve Sudoku" + Arrays.toString(sudoku));
    }

    private void solve() {
        while (!checkSudoku()) {
            removeImpossibles();
            assignIsolatedNumbers();
        }
    }

    private void assignIsolatedNumbers() {
        for (int i = 0; i < 9; i++) {
            int[] amountLeftPossibilitiesPerValueRow = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] amountLeftPossibilitiesPerValueColumn = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] instanceof ArrayList<?> possibles) {
                    for (Object possible : possibles) {
                        amountLeftPossibilitiesPerValueRow[(int) possible]++;
                    }
                }
                if (sudoku[j][i] instanceof ArrayList<?> possibles) {
                    for (Object possible : possibles) {
                        amountLeftPossibilitiesPerValueColumn[(int) possible]++;
                    }
                }
            }
            assignIsolatedNumber(amountLeftPossibilitiesPerValueRow, i, DigitCollectionType.ROW);
            assignIsolatedNumber(amountLeftPossibilitiesPerValueColumn, i, DigitCollectionType.COLUMN);
        }

        for (int i = 1; i < 9; i += 3) {
            for (int j = 1; j < 9; j += 3) {
                int[] amountLeftPossibilitiesPerValueBox = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                for (int delta_i = -1; delta_i <= 1; delta_i++) {
                    for (int delta_j = -1; delta_j <= 1; delta_j++) {
                        if (sudoku[i + delta_i][j + delta_j] instanceof ArrayList<?> possibles) {
                            for (Object possible : possibles) {
                                amountLeftPossibilitiesPerValueBox[(int) possible]++;
                            }
                        }
                    }
                }
                for(int leftPossibility = 1; leftPossibility < 10; leftPossibility++) {
                    int amount = amountLeftPossibilitiesPerValueBox[leftPossibility];
                    if (amount == 1) {
                        for (int delta_i = -1; delta_i <= 1; delta_i++) {
                            for (int delta_j = -1; delta_j <= 1; delta_j++) {
                                if (sudoku[i + delta_i][j + delta_j] instanceof ArrayList<?> possibles && possibles.contains(leftPossibility)) {
                                    sudoku[i + delta_i][j + delta_j] = leftPossibility;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void assignIsolatedNumber(int[] amountLeftPossibilitiesPerValue, int i, DigitCollectionType type) {
        if (type == DigitCollectionType.BOX) {
            throw new RuntimeException("This method isn't supposed to be called with DigitCollectionType.BOX");
        }

        for (int leftPossibility = 1; leftPossibility < 10; leftPossibility++) {
            int amount = amountLeftPossibilitiesPerValue[leftPossibility];
            if (amount == 1) {
                for (int j = 0; j < 9; j++) {
                    switch (type) {
                        case ROW -> {
                            if (sudoku[i][j] instanceof ArrayList<?> possibles && possibles.contains(leftPossibility)) {
                                sudoku[i][j] = leftPossibility;
                            }
                        }
                        case COLUMN -> {
                            if (sudoku[j][i] instanceof ArrayList<?> possibles && possibles.contains(leftPossibility)) {
                                sudoku[j][i] = leftPossibility;
                            }
                        }
                    }
                }
            }
        }
    }

    private int[][] getSudoku() {
        return Arrays.stream(sudoku).map(
                rows -> Arrays.stream(rows).
                        mapToInt(object -> object instanceof Integer value ? value : 0).
                        toArray()
        ).toArray(int[][]::new);
    }

    private void removeImpossibles() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] instanceof Integer value) {
                    for (int k = 0; k < 9; k++) {
                        removePossibleNumber(k, j, value);
                        removePossibleNumber(i, k, value);
                    }
                    int[] iBoxIndices = getBoxIndices(i);
                    int[] jBoxIndices = getBoxIndices(j);
                    for (int x : iBoxIndices) {
                        for (int y : jBoxIndices) {
                            removePossibleNumber(x, y, value);
                        }
                    }
                }
            }
        }
    }

    private int getNumber(int x, int y) {
        return sudoku[x][y] instanceof Integer number ? number : 0;
    }

    private void removePossibleNumber(int x, int y, Integer number) {
        if (sudoku[x][y] instanceof ArrayList<?> possibles) {
            possibles.remove(number);
            if (possibles.size() == 1) {
                sudoku[x][y] = possibles.get(0);
            }
        }
    }


    private int[] getBoxIndices(int i) {
        return switch (i) {
            case 0, 1, 2 -> new int[]{0, 1, 2};
            case 3, 4, 5 -> new int[]{3, 4, 5};
            case 6, 7, 8 -> new int[]{6, 7, 8};
            default -> throw new RuntimeException("Invalid sudoku index: " + i);
        };
    }

    private boolean checkSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!(sudoku[i][j] instanceof Integer)) {
                    return false;
                }
            }
        }
        return true;
    }

}
