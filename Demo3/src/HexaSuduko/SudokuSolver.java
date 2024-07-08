package HexaSuduko;

public class SudokuSolver {
    private static final int SIZE = 16;
    private static final char EMPTY = '.';
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    public boolean solveSudoku(char[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    for (char num : HEX_DIGITS) {
                        if (isValidMove(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = EMPTY;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
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
}
