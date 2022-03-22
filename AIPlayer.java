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

    

    public int heuristic(Board board) {
        /*
        
        The heuristic function is proportional to the power of number of chips that are continuous in a row/column/diagonal. This is added up for every row,column and diagonal.

        */

        int heuristic = 0;

        if(board == null) {
            if(isMinimizing) {

                heuristic = Integer.MAX_VALUE;
                
                 Logger.log("Calculated heuristic for " + this.getColor() + ": " + heuristic);
                return heuristic;
            } else {
                heuristic = Integer.MIN_VALUE;
                 Logger.log("Calculated heuristic for " + this.getColor() + ": " + heuristic);
                return heuristic;
            }
        }

        for (int i = 0; i < board.getNumRows(); i++) {
            // board.printBoard();
            for (int j = 0; j < board.getNumCols(); j++) {
                if (board.getBoard()[i][j] == this.getColor()) {
                    
                    // heuristic += checkRow(board,i, j, this.getColor());
                    // heuristic += checkCol(board,i, j, this.getColor());
                    // heuristic += checkDiagonal(board,i, j, this.getColor());

                    if(isMinimizing){

                        

                        heuristic = heuristic*(-1);

                        if(heuristic == Integer.MIN_VALUE) {
                            heuristic = Integer.MAX_VALUE;
                        }

                        heuristic = Math.max(Math.max(checkRow(board,i, j, this.getColor()), Math.max(checkCol(board,i, j, this.getColor()), checkDiagonal(board,i, j, this.getColor()))), heuristic);
                        heuristic = heuristic*(-1);
                        if(heuristic == Integer.MIN_VALUE + 1) {
                            heuristic = Integer.MIN_VALUE;
                        }
                    }else{
                        heuristic = Math.max(Math.max(checkRow(board,i, j, this.getColor()), Math.max(checkCol(board,i, j, this.getColor()), checkDiagonal(board,i, j, this.getColor()))), heuristic);
                    }

                }
            }
        }

         Logger.log("Calculated heuristic for " + this.getColor() + ": " + heuristic);

        // board.printBoard();
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
        // for (int i = row - 1; i >= 0; i--) {
        //     if (board.getBoard()[i][col] == color) {
        //         count++;
        //     } else {
        //         break;
        //     }
        // }

         // Logger.log("Checked row for " + this.getColor() + ": " + count);

        if (count >= 4) {
            return Integer.MAX_VALUE;
            // if(isMinimizing) {
            //     return Integer.MIN_VALUE;
            // } else {
            //     return Integer.MAX_VALUE;
            // }
        } else {
            return count;
        }

        
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

         // Logger.log("Checked col for " + this.getColor() + ": " + count);
        if (count >= 4) {
            return Integer.MAX_VALUE;
            // if(isMinimizing) {
            //     return Integer.MIN_VALUE;
            // } else {
            //     return Integer.MAX_VALUE;
            // }
        } else {
            return count;
        }
    }

    private int checkDiagonal(Board board, int row, int col, String color) {
        /*
        *
         *
          * 
           *
        */
        int leftDiagCount = 0;
        for (int i = row, j = col; i < board.getNumRows() && j < board.getNumCols(); i++, j++) {
            if (board.getBoard()[i][j] == color) {
                leftDiagCount++;
            } else {
                break;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.getBoard()[i][j] == color) {
                leftDiagCount++;
            } else {
                break;
            }
        }

        /*
           *
          *
         * 
        *
        */

        int rightDiagCount = 0;
        for (int i = row, j = col; i < board.getNumRows() && j >= 0; i++, j--) {
            if (board.getBoard()[i][j] == color) {
                rightDiagCount++;
            } else {
                break;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= board.getNumCols(); i--, j++) {
            if (board.getBoard()[i][j] == color) {
                rightDiagCount++;
            } else {
                break;
            }
        }
        
        int count = Math.max(leftDiagCount, rightDiagCount);


        if (count >= 4) {
            return Integer.MAX_VALUE;
            // if(isMinimizing) {
            //     return Integer.MIN_VALUE;
            // } else {
            //     return Integer.MAX_VALUE;
            // }
        } else {
            return count;
        }
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

        // wait 1 second
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        //print heuristic value
        
         Logger.log("Heuristic value: " + heuristic(board));

        //get all possible moves

        Board[] possibleMoves = getPossibleMoves(board);

        // calculate heuristic value for each possible move

        int[] heuristicValues = new int[possibleMoves.length];

        for (int i = 0; i < possibleMoves.length; i++) {
            AIPlayer dummy = new AIPlayer(this.getOpponentColor());
            if(possibleMoves[i] != null) {
                heuristicValues[i] = possibleMoves[i].getBoardScore(this, dummy);
            }else{
                if(isMinimizing) {
                    heuristicValues[i] = Integer.MAX_VALUE;
                } else {
                    heuristicValues[i] = Integer.MIN_VALUE;
                }
            }
            
        }
        //if minimizing, return the lowest heuristic value index

        //else return the move with the highest heuristic value index

        /**
         * 
         * This is a Greedy Approach to the problem.
         * instead of this we will use a desicion tree to solve the problem.
         * TODO: Implement a decision tree to solve the problem.
         * 
         */
        
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
             Logger.log("AI with isMinimizing:"+ isMinimizing +" chose move " + minOrMaxIndex + " with heuristic value " + minOrMax);
        } else {
             Logger.log("AI with isMinimizing:"+ isMinimizing +" chose move " + minOrMaxIndex + " with heuristic value " + minOrMax);
        }

        return minOrMaxIndex;

        }


    

    
}
