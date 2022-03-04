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

    public static final int NUM_ROWS = 7;
    public static final int NUM_COLS = 7;

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

}
