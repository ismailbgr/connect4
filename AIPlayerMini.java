import java.util.ArrayList;
import java.util.Random;

public class AIPlayerMini extends AIPlayer{

    /**
     * 
     * This is the constructor for the AIPlayer that uses minimax algorithm.
     * 
     */

    public AIPlayerMini(String color) {
        super(color);
    }
    int maxDepth = 6;

    private Move max(Board board, int depth) {
        Random r = new Random();

        
        /* If MAX is called on a state that is terminal or after a maximum depth is reached,
         * then a heuristic is calculated on the state and the move returned.
         */
        if ((board.checkForGameOver()) || (depth == maxDepth)) {
            return new Move(board.getLastMove().getRow(), board.getLastMove().getColumn(), board.getBoardScore());
        }
        // The children-moves of the state are calculated
        ArrayList<Board> children = new ArrayList<>(board.getChildren("b"));
        Move maxMove = new Move(Integer.MIN_VALUE);
        for (Board child : children) {
            // And for each child min is called, on a lower depth
            Move move = min(child, depth + 1);
            // The child-move with the greatest value is selected and returned by max
            if (move.getValue() >= maxMove.getValue()) {
                if ((move.getValue() == maxMove.getValue())) {
                    // If the heuristic has the same value then we randomly choose one of the two moves
                    if (r.nextInt(2) == 0 || move.getValue() == Integer.MIN_VALUE) {
                        maxMove.setRow(child.getLastMove().getRow());
                        maxMove.setColumn(child.getLastMove().getColumn());
                        maxMove.setValue(move.getValue());
                    }
                } else {
                    maxMove.setRow(child.getLastMove().getRow());
                    maxMove.setColumn(child.getLastMove().getColumn());
                    maxMove.setValue(move.getValue());
                }
            }
        }
        return maxMove;
    }
    
    private Move min(Board board, int depth) {
        Random r = new Random();
        if ((board.checkForGameOver()) || (depth == maxDepth)) {
            return new Move(board.getLastMove().getRow(), board.getLastMove().getColumn(), board.getBoardScore());
        }
        ArrayList<Board> children = new ArrayList<>(board.getChildren("r"));
        Move minMove = new Move(Integer.MAX_VALUE);
        for (Board child : children) {
            Move move = max(child, depth + 1);
            if (move.getValue() <= minMove.getValue()) {
                if ((move.getValue() == minMove.getValue())) {
                    if (r.nextInt(2) == 0 || move.getValue() == Integer.MAX_VALUE) {
                        minMove.setRow(child.getLastMove().getRow());
                        minMove.setColumn(child.getLastMove().getColumn());
                        minMove.setValue(move.getValue());
                    }
                } else {
                    minMove.setRow(child.getLastMove().getRow());
                    minMove.setColumn(child.getLastMove().getColumn());
                    minMove.setValue(move.getValue());
                }
            }
        }
        return minMove;
    }

    @Override
    public int AskForMove(Board board) {
        if(!isMinimizing)
            return max(board, 0).getColumn();
        else
            return min(board, 0).getColumn();
    }

}
