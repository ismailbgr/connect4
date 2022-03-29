public class HeuristicDemo {

    static Board board;

    public static void main(String[] args) {

        String[][] brd = {
            {"█","█","█","█","█","█","█",},
            {"█","█","█","█","█","█","█",},
            {"█","█","█","█","█","█","█",},
            {"█","█","█","█","█","█","█",},
            {"█","█","█","█","r","█","█",},
            {"r","█","█","r","█","█","█",},
            {"b","r","r","█","█","█","█",},
            
            };

        board = new Board(brd);

        AIPlayer player1 = new AIPlayer("r");
        AIPlayer player2 = new AIPlayer("b");

        board.printBoard();
        System.out.println(board.getBoardScore());

        // System.out.println(Integer.MAX_VALUE);
        // System.out.println(Integer.MAX_VALUE*(-1));
        // System.out.println(Integer.MIN_VALUE);

        

        
        
        

    }

}
