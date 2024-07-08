package HexaSuduko;

import java.util.Random;

public class HexadokuGenerator {
    private static final int SIZE = 16;
    private static final char EMPTY = '.';
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private Random random = new Random();

    public char[][] generatePuzzle() {
        char[][] board = new char[SIZE][SIZE];
        fillEmptyBoard(board);
        fillDiagonalRegions(board);
        SudokuSolver solver = new SudokuSolver();
        solver.solveSudoku(board);
        removeNumbers(board);
        return board;
    }

    private void fillEmptyBoard(char[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void fillDiagonalRegions(char[][] board) {
        for (int i = 0; i < SIZE; i += 4) {
            fillRegion(board, i, i);
        }
    }

    private void fillRegion(char[][] board, int row, int col) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                char num;
                do {
                    num = HEX_DIGITS[random.nextInt(16)];
                } while (!isValidMove(board, row + i, col + j, num));
                board[row + i][col + j] = num;
            }
        }
    }

    private boolean isValidMove(char[][] board, int row, int col, char num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int blockRowStart = (row / 4) * 4;
        int blockColStart = (col / 4) * 4;
        for (int i = blockRowStart; i < blockRowStart + 4; i++) {
            for (int j = blockColStart; j < blockColStart + 4; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private void removeNumbers(char[][] board) {
        int cellsToRemove = 200;
        while (cellsToRemove > 0) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (board[row][col] != EMPTY) {
                board[row][col] = EMPTY;
                cellsToRemove--;
            }
        }
    }
}
