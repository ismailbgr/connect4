import java.util.Scanner;

public class Player {
    
    // instance variables
    private String color;

    Player(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }
    
    public String getOpponentColor(){
        if(this.color.equals("r")){
            return "b";
        }
        else{
            return "r";
        }
    }

    public int AskForMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a column number: ");
        int col = 0;
        
        col = scanner.nextInt();
        

     


        while (!board.isValidMove(col)) {
            System.out.println("Invalid move. Try again: ");
            col = scanner.nextInt();
        }
       
        return col;
    }

}
