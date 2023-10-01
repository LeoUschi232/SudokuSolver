package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class SudokuSolver {
    private final Object[][] sudoku;
    private boolean changedInLastIteration;

    /**
     * Initializes the SudokuSolver with the given Sudoku numbers
     * Throws a RuntimeException for invalid Sudoku input
     * Cells with zeros are considered empty and are initialized with a list of all possible values
     *
     * @param numbers the Sudoku numbers to initialize the SudokuSolver with
     */
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
        changedInLastIteration = true;
    }

    /**
     * Validates the sudoku numbers, ensuring no duplicates exist in any row, column, or box
     * Throws a RuntimeException for invalid Sudoku input
     *
     * @param numbers the Sudoku numbers to validate
     */
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

    /**
     * Solves the Sudoku puzzle and returns the solved puzzle
     * Throws a RuntimeException if the puzzle is unsolvable
     *
     * @param sudoku the Sudoku puzzle to solve
     */
    public static int[][] solveSudoku(int[][] sudoku) {
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);
        sudokuSolver.solve();
        if (sudokuSolver.checkSudoku()) {
            return sudokuSolver.getSudoku();
        }
        throw new RuntimeException("Unable to solve Sudoku" + Arrays.toString(sudoku));
    }

    /**
     * The main solver method, iteratively refines the puzzle until a solution is reached by removing values
     * from array lists which are impossible to be true for the given cells and assigning numbers to cells if
     * they cannot be assigned to any other cell in the same row, column, or box
     * After filling all cells, checks if the sudoku solution is valid
     */
    private void solve() {
        while (!checkSudoku()) {
            while (changedInLastIteration) {
                changedInLastIteration = false;
                removeImpossibles();
            }
            assignIsolatedNumbers();
        }
        checkNumbersValidity(getSudoku());
    }

    /**
     * Assigns numbers to cells in rows, columns, and boxes where only one cell can contain a certain number
     * For example, if a row has only one cell where the number 5 can go, it assigns 5 to that cell
     */
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
            if (Arrays.stream(amountLeftPossibilitiesPerValueRow).anyMatch(amount -> amount == 1)) {
                assignIsolatedNumber(amountLeftPossibilitiesPerValueRow, i, DigitCollectionType.ROW);
            }
            if (Arrays.stream(amountLeftPossibilitiesPerValueColumn).anyMatch(amount -> amount == 1)) {
                assignIsolatedNumber(amountLeftPossibilitiesPerValueColumn, i, DigitCollectionType.COLUMN);
            }
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
                if (Arrays.stream(amountLeftPossibilitiesPerValueBox).anyMatch(amount -> amount == 1)) {
                    for (int leftPossibility = 1; leftPossibility < 10; leftPossibility++) {
                        int amount = amountLeftPossibilitiesPerValueBox[leftPossibility];
                        if (amount == 1) {
                            for (int delta_i = -1; delta_i <= 1; delta_i++) {
                                for (int delta_j = -1; delta_j <= 1; delta_j++) {
                                    if (sudoku[i + delta_i][j + delta_j] instanceof ArrayList<?> possibles && possibles.contains(leftPossibility)) {
                                        sudoku[i + delta_i][j + delta_j] = leftPossibility;
                                        removeImpossibles();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Assigns numbers to cells in the row or column where only one cell can contain a certain number
     *
     * @param amountLeftPossibilitiesPerValue the amount of cells in the row or column where a certain number can go
     * @param i                               the row or column index
     * @param type                            the DigitCollectionType, either ROW or COLUMN
     */
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
                                removeImpossibles();
                            }
                        }
                        case COLUMN -> {
                            if (sudoku[j][i] instanceof ArrayList<?> possibles && possibles.contains(leftPossibility)) {
                                sudoku[j][i] = leftPossibility;
                                removeImpossibles();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Converts the internal representation of the Sudoku puzzle to a 2D int array and returns it
     * Empty cells are represented by zeros
     *
     * @return the Sudoku puzzle as a 2D int array
     */
    private int[][] getSudoku() {
        return Arrays.stream(sudoku).map(
                rows -> Arrays.stream(rows).
                        mapToInt(object -> object instanceof Integer value ? value : 0).
                        toArray()
        ).toArray(int[][]::new);
    }

    /**
     * Iterates through each cell of the Sudoku puzzle
     * If a number is assigned to a cell, it removes this number from the array lists of possibilities of empty cells
     * in the corresponding row, column, and box
     * If a cell has only one possibility left, it assigns this possibility to the cell
     */
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

    /**
     * Returns the number assigned to the cell at the given coordinates
     * If the cell is empty, it returns 0
     *
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @return the number assigned to the cell at the given coordinates, 0 if the cell is empty
     */
    private int getNumber(int x, int y) {
        return sudoku[x][y] instanceof Integer number ? number : 0;
    }

    /**
     * Removes the given number from the array list of possibilities of the cell at the given coordinates
     *
     * @param x      the x coordinate of the cell
     * @param y      the y coordinate of the cell
     * @param number the number to remove from the array list of possibilities of the cell
     */
    private void removePossibleNumber(int x, int y, Integer number) {
        if (sudoku[x][y] instanceof ArrayList<?> possibles) {
            possibles.remove(number);
            if (possibles.size() == 1) {
                sudoku[x][y] = possibles.get(0);
                changedInLastIteration = true;
            }
        }
    }

    /**
     * Returns the indices of the cells in the same box as the cell at the given index
     * The indices are returned in the order of the rows of the box
     *
     * @param i the index of the cell
     * @return the indices of the cells in the same box as the cell at the given index
     */
    private int[] getBoxIndices(int i) {
        return switch (i) {
            case 0, 1, 2 -> new int[]{0, 1, 2};
            case 3, 4, 5 -> new int[]{3, 4, 5};
            case 6, 7, 8 -> new int[]{6, 7, 8};
            default -> throw new RuntimeException("Invalid sudoku index: " + i);
        };
    }

    /**
     * Returns true if the Sudoku puzzle is solved, false otherwise
     *
     * @return true if the Sudoku puzzle is solved, false otherwise
     */
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

    @Override
    public String toString() {
        return Arrays.stream(getSudoku()).map(
                row -> Arrays.toString(row).replaceAll("[\\[\\],]", "") + "\n"
        ).collect(Collectors.joining());
    }

    public static String sudokuToString(int[][] sudoku) {
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);
        return Arrays.stream(sudokuSolver.getSudoku()).map(
                row -> Arrays.toString(row).replaceAll("[\\[\\],]", "") + "\n"
        ).collect(Collectors.joining());
    }
}
