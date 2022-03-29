import java.util.Random;

public class Game {

    public static long seed;
    public static long maxseed;
    public static int maxRound;

    public static void main(String[] args) {

        // Logger.enable();

        // if args contains --debug, then debug mode is enabled
        if (args.length > 0 && args[0].equals("--debug")) {
            Logger.enable();
        }

        // while(true){
        START(args);
        // }

    }

    private static void START(String[] args) {
        Board board = new Board();
        Player player1 = new AIPlayerMini("r");
        Player player2 = new AIPlayerMini("b");
        Random rand = new Random();

        seed = rand.nextLong();

        rand.setSeed(seed);
        // rand.setSeed(520212928048828049l);
        // rand.setSeed(611372937516920174l);

        System.out.println("Seed: " + seed);

        int col = 0;

        // // play one random move for each player

        // col = rand.nextInt(7);
        // while (!board.isValidMove(col)) {
        // col = rand.nextInt(7);
        // }
        // board.printBoard();
        // board.makeMove(col, player1.getColor());

        // System.out.println();
        // col = rand.nextInt(7);
        // while (!board.isValidMove(col)) {
        // col = rand.nextInt(7);
        // }
        // board.printBoard();
        // board.makeMove(col, player2.getColor());

        // System.out.println();

        // // System.out.println(rand);

        int currentRound = 0;
        while (true) {

            

            if (currentRound > maxRound) {
                maxRound = currentRound;
                maxseed = seed;
            }

            board.printBoard();
            System.out.println(board.getBoardScore()+"------------------------------------");
            col = player1.AskForMove(board);
            board.makeMove(col, player1.getColor());
            currentRound++;
            if (board.isWinner(player1.getColor())) {
                System.out.println("Player 1 wins!");
                System.out.println("----------------");
                board.printBoard();
                board.printRawBoard();
                System.out.println("----------------");
                System.out.println("----------------"+board.getBoardScore()+"------------------------------------");
                break;
            }
            if (board.isFull()) {
                System.out.println("It's a tie!");
                System.out.println("----------------");
                board.printBoard();
                board.printRawBoard();
                System.out.println("----------------");
                System.out.println("----------------"+board.getBoardScore()+"------------------------------------");
                break;
            }
            board.printBoard();
            col = player2.AskForMove(board);
            board.makeMove(col, player2.getColor());
            currentRound++;
            if (board.isWinner(player2.getColor())) {
                System.out.println("Player 2 wins!");
                System.out.println("----------------");
                board.printBoard();
                board.printRawBoard();
                System.out.println("----------------");
                System.out.println("----------------"+board.getBoardScore()+"------------------------------------");
                break;
            }
            if (board.isFull()) {
                System.out.println("It's a tie!");
                System.out.println("----------------");
                board.printBoard();
                board.printRawBoard();
                System.out.println("----------------");
                System.out.println("----------------"+board.getBoardScore()+"------------------------------------");
                break;
            }
            
            


        }

        

        // System.out.println("Max Round: " + maxRound);
        // System.out.println("Max Seed: " + maxseed);

    }

}
