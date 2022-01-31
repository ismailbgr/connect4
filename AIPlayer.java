public class AIPlayer extends Player {

    boolean isMinimizing;

    public AIPlayer(String color) {

        super(color);

        if(color == "r") {
            isMinimizing = true;
        } else {
            isMinimizing = false;
        }
        
    }

    

    private int heuristic(Board board) {
        /*
        
        The heuristic function is proportional to the power of number of chips that are continuous in a row/column/diagonal. This is added up for every row,column and diagonal.

        */

        int heuristic = 0;

        if(board == null) {
            if(isMinimizing) {

                heuristic = Integer.MAX_VALUE;
                
                System.out.println("Calculated heuristic for " + this.getColor() + ": " + heuristic);
                return heuristic;
            } else {
                heuristic = Integer.MIN_VALUE;
                System.out.println("Calculated heuristic for " + this.getColor() + ": " + heuristic);
                return heuristic;
            }
        }

        for (int i = 0; i < board.getNumRows(); i++) {
            for (int j = 0; j < board.getNumCols(); j++) {
                if (board.getBoard()[i][j] == this.getColor()) {
                    heuristic += checkRow(board,i, j, this.getColor());
                    heuristic += checkCol(board,i, j, this.getColor());
                    heuristic += checkDiagonal(board,i, j, this.getColor());
                }
            }
        }

        System.out.println("Calculated heuristic for " + this.getColor() + ": " + heuristic);

        return heuristic;

    }

    private int checkRow(Board board, int row, int col, String color) {
        int count = 0;
        for (int i = row; i < board.getNumRows(); i++) {
            if (board.getBoard()[i][col] == color) {
                count++;
            } else {
                break;
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            if (board.getBoard()[i][col] == color) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private int checkCol(Board board, int row, int col, String color) {
        int count = 0;
        for (int i = col; i < board.getNumCols(); i++) {
            if (board.getBoard()[row][i] == color) {
                count++;
            } else {
                break;
            }
        }
        for (int i = col - 1; i >= 0; i--) {
            if (board.getBoard()[row][i] == color) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private int checkDiagonal(Board board, int row, int col, String color) {
        int count = 0;
        for (int i = row, j = col; i < board.getNumRows() && j < board.getNumCols(); i++, j++) {
            if (board.getBoard()[i][j] == color) {
                count++;
            } else {
                break;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.getBoard()[i][j] == color) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }


    public Board[] getPossibleMoves(Board board) {
        
        Board[] possibleMoves = new Board[board.getNumCols()];

        for (int i = 0; i < board.getNumCols(); i++) {
            if (board.isValidMove(i)) {
                Board newBoard = new Board(board.getBoard());
                newBoard.makeMove(i, this.getColor());
                possibleMoves[i] = newBoard;
            }
        }

        return possibleMoves;

    }

    @Override
    public int AskForMove(Board board) {

        //wait 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //find best possible move using A*

        //TODO implement A*
        
        //print heuristic value
        
        System.out.println("Heuristic value: " + heuristic(board));

        //get all possible moves

        Board[] possibleMoves = getPossibleMoves(board);

        // calculate heuristic value for each possible move

        int[] heuristicValues = new int[possibleMoves.length];

        for (int i = 0; i < possibleMoves.length; i++) {
            heuristicValues[i] = heuristic(possibleMoves[i]);
        }
        //if minimizing, return the lowest heuristic value index

        //else return the move with the highest heuristic value index
        
        //find min or max heuristic value

        int minOrMax = 0;
        int minOrMaxIndex = 0;

        if (isMinimizing) {
            minOrMax = Integer.MAX_VALUE;
        } else {
            minOrMax = Integer.MIN_VALUE;

        }

        for (int i = 0; i < heuristicValues.length; i++) {
            if (isMinimizing) {
                if (heuristicValues[i] < minOrMax) {
                    minOrMax = heuristicValues[i];
                    minOrMaxIndex = i;
                }
            } else {
                if (heuristicValues[i] > minOrMax) {
                    minOrMax = heuristicValues[i];
                    minOrMaxIndex = i;
                }
            }
        }

        if(isMinimizing) {
            System.out.println("AI with isMinimizing:"+ isMinimizing +" chose move " + minOrMaxIndex + " with heuristic value " + minOrMax);
        } else {
            System.out.println("AI with isMinimizing:"+ isMinimizing +" chose move " + minOrMaxIndex + " with heuristic value " + minOrMax);
        }

        return minOrMaxIndex;

        }


    

    
}
