import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currPlayer;
    private boolean gameEnded;

    public TicTacToe() {
        board = new char[3][3];
        currPlayer = 'X';
        gameEnded = false;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe!");

        while (!gameEnded) {
            System.out.println("Player " + currPlayer + ", select your move (row [0-2] and column [0-2]):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (isValidMove(row, col)) {
                makeMove(row, col);
                printBoard();
                if (checkForWin(row, col)) {
                    System.out.println("Player " + currPlayer + " wins!");
                    gameEnded = true;
                } else if (isBoardFull()) {
                    System.out.println("It's a tie!");
                    gameEnded = true;
                } else {
                    currPlayer = (currPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    private boolean isValidMove(int row, int col) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-');
    }

    private void makeMove(int row, int col) {
        board[row][col] = currPlayer;
    }

    private boolean checkForWin(int row, int col) {
        // Check row
        if (board[row][0] == board[row][1] && board[row][1] == board[row][2])
            return true;

        // Check column
        if (board[0][col] == board[1][col] && board[1][col] == board[2][col])
            return true;

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] != '-')
            return true;

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] != '-')
            return true;

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-')
                    return false;
            }
        }
        return true;
    }

    private void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.startGame();
    }
}
