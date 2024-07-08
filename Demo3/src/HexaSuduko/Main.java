package HexaSuduko;

public class Main {
    public static void main(String[] args) {
        HexadokuGenerator generator = new HexadokuGenerator();
        char[][] board = generator.generatePuzzle();

        System.out.println("Generated Hexadoku Puzzle:");
        printBoard(board);

        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudoku(board)) {
            System.out.println("\nSolved Hexadoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

