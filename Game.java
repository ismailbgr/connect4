public class Game {
    
    public static void main(String[] args) {
        
        Board board = new Board();
        Player player1 = new AIPlayer("r");
        Player player2 = new AIPlayer("b");

        //play one random move for each player

        int col = (int) (Math.random() * 6);
        board.makeMove(col, "r");
        // board.printBoard();

        col = (int) (Math.random() * 6);
        board.makeMove(col, "b");
        // board.printBoard();




        while (true) {

            
            board.printBoard();
            col = player1.AskForMove(board);
            board.makeMove(col, player1.getColor());
            if (board.isWinner(player1.getColor())) {
                System.out.println("Player 1 wins!");
                System.out.println("----------------");
                board.printBoard();
                System.out.println("----------------");
                break;
            }
            if (board.isFull()) {
                System.out.println("It's a tie!");
                System.out.println("----------------");
                board.printBoard();
                System.out.println("----------------");
                break;
            }
            board.printBoard();
            col = player2.AskForMove(board);
            board.makeMove(col, player2.getColor());
            if (board.isWinner(player2.getColor())) {
                System.out.println("Player 2 wins!");
                System.out.println("----------------");
                board.printBoard();
                System.out.println("----------------");
                break;
            }
            if (board.isFull()) {
                System.out.println("It's a tie!");
                System.out.println("----------------");
                board.printBoard();
                System.out.println("----------------");
                break;
            }
        }

    }

}
