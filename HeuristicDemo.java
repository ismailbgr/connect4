public class HeuristicDemo {

    static Board board;

    public static void main(String[] args) {

        String[][] brd = {
            {"█","█","█","█","r","█","█",},
            {"r","█","█","r","█","█","█",},
            {"b","r","r","█","█","█","█",},
            
            };

        board = new Board(brd);

        AIPlayer player1 = new AIPlayer("r");
        AIPlayer player2 = new AIPlayer("b");

        board.printBoard();

        // System.out.println(Integer.MAX_VALUE);
        // System.out.println(Integer.MAX_VALUE*(-1));
        // System.out.println(Integer.MIN_VALUE);

        for (int i = 0; i < brd[0].length; i++) {
            Board copyBoard = new Board(board.getBoard());
            copyBoard.makeMove(i, "r");
            System.out.println("Move: " + i);
            copyBoard.printBoard();
            System.out.println(player1.heuristic(copyBoard));
        }

        
        
        

    }

}
