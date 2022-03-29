import java.util.ArrayList;

public class Board {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static int NUM_ROWS = 6;
    public static int NUM_COLS = 7;

    private String[][] board;

    public Board() {
        board = new String[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                board[i][j] = "█";
                // board[i][j] = "⬤";
            }
        }
    }

    public Board(String[][] board) {
        this.board = board;
        NUM_COLS = board[0].length;
        NUM_ROWS = board.length;
    }

    public Board(Board board2) {
        this.board = board2.getBoard();
        NUM_COLS = board2.getNumCols();
        NUM_ROWS = board2.getNumRows();
    }

    public void printBoard() {

        System.out.println("   0 1 2 3 4 5 6");
        for (int i = 0; i < NUM_ROWS; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < NUM_COLS; j++) {
                if (board[i][j].equals("r")) {
                    // System.out.print(ANSI_RED + "█" + ANSI_RESET);
                    System.out.print(ANSI_RED + "⬤ " + ANSI_RESET);
                } else if (board[i][j].equals("b")) {
                    // System.out.print(ANSI_BLUE + "█" + ANSI_RESET);
                    System.out.print(ANSI_BLUE + "⬤ " + ANSI_RESET);
                } else {
                    // System.out.print(board[i][j]);
                    System.out.print("⬤ ");
                }
            }
            System.out.println();
        }

    }

    public void printRawBoard() {
        System.out.println("{");
        for (int i = 0; i < NUM_ROWS; i++) {
            System.out.print("{");
            for (int j = 0; j < NUM_COLS; j++) {
                System.out.print("\"" + board[i][j] + "\",");
            }
            System.out.println("},");
        }
        System.out.println("}");
    }

    public boolean isValidMove(int col) {
        if (col < 0 || col >= NUM_COLS) {
            return false;
        }
        for (int i = 0; i < NUM_ROWS; i++) {
            if (board[i][col] == "█") {
                return true;
            }
        }
        return false;
    }

    public boolean makeMove(int col, String color) {
        if (!isValidMove(col)) {
            return false;
        }
        for (int i = NUM_ROWS - 1; i >= 0; i--) {
            if (board[i][col] == "█") {
                board[i][col] = color;
                lastmove = new Move(i, col);
                return true;
            }
        }
        return false;
    }

    /**
     * @return copy of the board
     */
    public String[][] getBoard() {
        String[][] copy = new String[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    public boolean isFull() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (board[i][j] == "█") {
                    return false;
                }
            }
        }
        return true;
    }

    // public boolean isWin(String color) {
    // for (int i = 0; i < NUM_ROWS; i++) {
    // for (int j = 0; j < NUM_COLS; j++) {
    // if (board[i][j] == color) {
    // if (checkHorizontal(i, j, color) || checkVertical(i, j, color) ||
    // checkDiagonal(i, j, color)) {
    // return true;
    // }
    // }
    // }
    // }
    // return false;
    // }

    private boolean checkHorizontal(int row, int col, String color) {
        int count = 0;
        for (int i = col; i < NUM_COLS; i++) {
            if (board[row][i] == color) {
                count++;
            } else {
                break;
            }
        }
        for (int i = col - 1; i >= 0; i--) {
            if (board[row][i] == color) {
                count++;
            } else {
                break;
            }
        }
        return count >= 4;
    }

    private boolean checkVertical(int row, int col, String color) {
        int count = 0;
        for (int i = row; i < NUM_ROWS; i++) {
            if (board[i][col] == color) {
                count++;
            } else {
                break;
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == color) {
                count++;
            } else {
                break;
            }
        }
        return count >= 4;
    }

    private boolean checkDiagonal(int row, int col, String color) {
        int count = 0;
        for (int i = row, j = col; i < NUM_ROWS && j < NUM_COLS; i++, j++) {
            if (board[i][j] == color) {
                count++;
            } else {
                break;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == color) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 4) {
            return true;
        }
        count = 0;
        for (int i = row, j = col; i < NUM_ROWS && j >= 0; i++, j--) {
            if (board[i][j] == color) {
                count++;
            } else {
                break;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < NUM_COLS; i--, j++) {
            if (board[i][j] == color) {
                count++;
            } else {
                break;
            }
        }
        return count >= 4;
    }

    public int getNumRows() {
        return NUM_ROWS;
    }

    public int getNumCols() {
        return NUM_COLS;
    }

    public void clear() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                board[i][j] = "█";
            }
        }
    }

    public boolean isWinner(String color) {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (board[i][j] == color) {
                    if (checkHorizontal(i, j, color) || checkVertical(i, j, color) || checkDiagonal(i, j, color)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getBoardScore(AIPlayer p1, AIPlayer p2) {

        int p1Score = p1.heuristic(this);
        int p2Score = p2.heuristic(this);

        return (int) (Math.pow(10, p2Score) - Math.pow(10, p1Score));
        // return p1Score + p2Score;

    }

    public boolean canMove(int row, int col) {
        return (row > -1) && (col > -1) && (row <= NUM_ROWS) && (col <= NUM_COLS);
    }

    /*
     * +1 for each 2 pieces in a row by Player 1, -1 for each 2 pieces in a row by
     * Player 2.
     * +10 for each 3 pieces in a row by Player 1, -10 for each 3 pieces in a row by
     * Player 2.
     * ..
     * +10^i for each (i+2) pieces in a row by Player 1, -10^i for each (i+2) pieces
     * in a row by Player 2.
     * +10^(4-2) if "4" pieces in a row by Player 1 exist,
     * -10^(4-2) if "4" pieces in a row by Player 2 exist.
     */

    public int getBoardScore() {

        // AIPlayer p1 = new AIPlayer("r");
        // AIPlayer p2 = new AIPlayer("b");

        // int p1Score = p1.heuristic(this);
        // int p2Score = p2.heuristic(this);

        // return (int)(Math.pow(2, p2Score) - Math.pow(2, p1Score));
        // return p1Score + p2Score;

        int player1Score = 0;
        int player2Score = 0;


        // System.out.println("------------------DEBUG------------------");
        // printBoard();
        // System.out.println("------------------DEBUG------------------");

        for (int i = 0; i < 4 - 2; i++) {
            player1Score += countNInARow(i + 2, "r") * Math.pow(10, i);
            player2Score += countNInARow(i + 2, "b") * Math.pow(10, i);
        }



        if (isWinner("r")) {
            player1Score = Integer.MAX_VALUE;
        }
        if (isWinner("b")) {
            player2Score = Integer.MAX_VALUE;
        }

        return player2Score - player1Score;

    }

    public int countNInARow(int N, String player) {
        int times = 0;

        // Check for "4" consecutive checkers of the same player or empty tiles in a
        // row, horizontally.
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (canMove(i, j + 4)) {
                    // Check for "N" consecutive checkers of the same player in a row, horizontally.
                    int k = 0;
                    while (k < N && board[i][j + k].equals(player)) {
                        k++;
                    }
                    // Check for "4 - N" consecutive checkers of the same player or empty tiles in a
                    // row, horizontally.
                    if (k == N) {
                        while (k < 4 && (board[i][j + k].equals(player) || board[i][j + k].equals("█"))) {
                            k++;
                        }
                        if (k == 4)
                            times++;
                    }
                }
            }
        }

        // Check for "4" consecutive checkers of the same player or empty tiles in a
        // row, vertically.
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (canMove(i - 3, j)) {
                    // Check for "N" consecutive checkers of the same player in a row, vertically.
                    int k = 0;
                    while (k < N && board[i - k][j].equals(player)) {
                        k++;
                    }
                    // Check for "4 - N" consecutive checkers of the same player or empty tiles in a
                    // row, vertically.
                    if (k == 4) {
                        while (k < 4 && (board[i - k][j].equals(player) || board[i - k][j].equals("█"))) {
                            k++;
                        }
                        if (k == 4)
                            times++;
                    }
                }
            }
        }

        // Check for "4" consecutive checkers of the same player or empty tiles in a
        // row, in descending diagonal.
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (canMove(i + 4, j + 4)) {
                    // Check for "N" consecutive checkers of the same player in a row, in descending
                    // diagonal.
                    int k = 0;
                    while (k < N && board[i + k][j + k].equals(player)) {
                        k++;
                    }
                    // Check for "4 - N" consecutive checkers of the same player or empty tiles in a
                    // row, in descending diagonal.
                    if (k == N) {
                        while (k < 4 && (board[i + k][j + k].equals(player) || board[i + k][j + k].equals("█"))) {
                            k++;
                        }
                        if (k == 4)
                            times++;
                    }
                }
            }
        }

        // Check for "4" consecutive checkers of the same player or empty tiles in a
        // row, in ascending diagonal.
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                int p1 = i - 4;
                int p2 = j + 4;
                if (canMove(p1, p2)) {
                    // Check for "N" consecutive checkers of the same player in a row, in ascending
                    // diagonal.
                    int k = 0;
                    while (k < N && board[i - k][j + k].equals(player)) {
                        k++;
                    }
                    // Check for "4 - N" consecutive checkers of the same player or empty tiles in a
                    // row, in ascending diagonal.
                    if (k == N) {
                        while (k < 4 && (board[i - k][j + k].equals(player) || board[i - k][j + k].equals("█"))) {
                            k++;
                        }
                        if (k == 4)
                            times++;
                    }
                }
            }
        }

        return times;
    }

    // private boolean isValidMove(int row, int col) {
    //     if (col < 0 || col >= NUM_COLS) {
    //         return false;
    //     }
    //     if (row < 0 || row >= NUM_ROWS) {
    //         return false;
    //     }

    //     if (board[row][col] == "█") {
    //         return true;
    //     }

    //     return false;
    // }

    public boolean checkForGameOver() {
        return isWinner("r") || isWinner("b");
    }

    Move lastmove = null;

    public Move getLastMove() {
        return lastmove;
    }

    public ArrayList<Board> getChildren(String player) {
        ArrayList<Board> children = new ArrayList<Board>();
        for (int col = 0; col < NUM_COLS; col++) {
            if (isValidMove(col)) {
                Board child = new Board(this);
                child.makeMove(col, player);
                children.add(child);
            }
        }
        return children;
    }
}
