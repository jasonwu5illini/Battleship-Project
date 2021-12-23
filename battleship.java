import java.util.Scanner;
/**
 * 
 *  Main class for console battleship
 *
 *  @author  benjamin
 *  @version May 19, 2020
 *  @author  Period: 3
 *  @author  Assignment: APCSfinal
 *
 *  @author  Sources: TODO
 */
public class battleship
{
    /**
     * 
     * Main driver code
     * @param args for java main class
     */
    public static void main( String[] args ) {
        
        
        System.out.println("Welcome to Console Battleship"); 
        System.out.println("For best results, make sure each character takes up the same amount of space");
        System.out.println("Also make sure the console is not too small");

      //  System.out.println("***** Make sure you can display UNICODE in your console"); 
       // System.out.println("***** For Eclipse: Window->Prefrences->General->Workspace->Text File Encoding to UTF-8");  //this is gimmicky i could not get it to work properly. it would be pretty cool tho. like a 🔥 when you hit something


        Scanner sc = new Scanner(System.in);

        System.out.println("Name?");
        
        human player = new human(sc.nextLine());
        
        String newGame = "Y";
        
        while(newGame.equals("Y")) {      
            
            board humanBoard = new board();
            board AIBoard = new board();
            
            
            System.out.println("Select difficulty ['1'(easy) '2'(medium) '3'(hard)]");
            String difficulty = sc.nextLine();
            while(!(difficulty.equals( "1" ) || difficulty.equals( "2" ) || difficulty.equals( "3" ))) {
                System.out.println("Invalid option");
                
                System.out.println("Select difficulty ['1'(easy) '2'(medium) '3'(hard)]");
                difficulty = sc.nextLine();
            }
            ai compooter;
            if(difficulty.equals( "1" )) {
               compooter = new easyAI(AIBoard); //change to ai computer = new easyAI(AIBoard);
            }
            else if(difficulty.equals( "2" )) {
               compooter = new mediumAI(AIBoard); //change to ai computer = new mediumAI(AIBoard);
            }
            else if(difficulty.equals( "3" )) {
                compooter = new hardAI(AIBoard); //change to ai computer = new hardAI(AIBoard);
            }
            else {
            	compooter = new easyAI(AIBoard); //default difficulty, set it to one of the 3 above;
            }

            System.out.println(player.getName() + ": " + player.getScore() + " vs. AI: " + compooter.getScore());
            System.out.println("Game Start-----------");
            
            System.out.println("Place your ships");
            humanBoard.printEmpty();
            humanBoard.putPb();
            humanBoard.printPB();
            
            humanBoard.putSub();
            humanBoard.printSUB();
            
            humanBoard.putDes();
            humanBoard.printDES();
            
            humanBoard.putBat();
            humanBoard.printBAT();
            
            humanBoard.putCar();
            humanBoard.printCAR();
            
            AIBoard.putBoats();
            AIBoard.printBoats(); //used for testing purposes
            
            
            int turn = 1;
            while (humanBoard.alive() && AIBoard.alive()) {
                System.out.println();
                System.out.println();
                System.out.println("TURN: " + turn + " START---------------------");
                turn++;
                
                System.out.print("Opponent's ships you have sunk:");
                if(humanBoard.getSunk().size() != 0) {
                    for(boat b : humanBoard.getSunk()) {
                        System.out.print(" - " + b.getName());
                    }
                }
                else {
                    System.out.print( " NONE" );
                }
                System.out.println();
                
                System.out.print("Ships you have lost:");
                if(AIBoard.getSunk().size() != 0) {
                    for(boat b : AIBoard.getSunk()) {
                        System.out.print(" - " + b.getName());
                    }
                }
                else {
                    System.out.print( " NONE" );
                }
                System.out.println();
                System.out.println();
                
                humanBoard.printShots();
                System.out.println();
                
                System.out.println("Your ship's status");
                humanBoard.printBoats(AIBoard);
                System.out.println();
                //ask player for move
                position humanMove = player.nextMove();
                while (humanBoard.getHits().contains(humanMove) || humanBoard.getMisses().contains( humanMove )){
                    System.out.println("You already shot there, try a different location");
                    humanMove = player.nextMove();
                }
                //ask the ai if the move is a hit or a miss
                if(AIBoard.isHit(humanMove)){
                    humanBoard.addHit( humanMove );
                    humanBoard.hitMessage(); //notify human it hit
                }
                else {
                    humanBoard.addMiss( humanMove );
                    humanBoard.missMessage(); //notify human it missed
                }
                humanBoard.updateSunk( AIBoard );

    
                
                //end of human turn
                //start of AI turn
                position aiMove = compooter.nextMove(humanBoard);
                
                if(humanBoard.isHit( aiMove )) {
                    AIBoard.addHit(aiMove);
                    System.out.println("You have been hit at position " + aiMove.printString());
                }
                else {
                    AIBoard.addMiss( aiMove );
                    System.out.println("The AI missed at position " + aiMove.printString());

                }
                AIBoard.updateSunk( humanBoard );           
                

                
            }
            System.out.println();
            System.out.println();

            System.out.println();

            if(!humanBoard.alive() && !AIBoard.alive()) {
                System.out.println("Tie!");
                System.out.println("Your Opponent's Ships -------");
                AIBoard.printBoats();
            }
            else if(!humanBoard.alive()) {
                System.out.println("You Lost!");
                System.out.println("Your Opponent's Ships -------");
                AIBoard.printBoats();
                compooter.addScore();
            }
            else if(!AIBoard.alive()) {
                System.out.println("You Won!");
                System.out.println("Your Opponent's Ships -------");
                AIBoard.printBoats();
                player.addScore();
            }
            System.out.println("Start new game?[Y/N]");
            newGame = sc.nextLine().toUpperCase();
            
        }
        System.out.println("Goodbye!");
    }
    
    
    /**
     * 
     * method used to test if a move is in the proper battleship format 
     * ie A6 or H8
     * @param move the move to be tested
     * @return if the move is proper or not
     */
    public static boolean isProperMove( String move ) {
        if(move.length() == 0) {
            return false;
        }
        else if(move.length() != 2 && !move.substring( 1 ).equals( "10" )) {
            return false;
        }
        else if( !"ABCDEFGHIJ".contains( move.substring(0,1) ) ) {
            return false;
        }
        else if( !"1023456789".contains( move.substring(1) ) ){
            System.out.println(move.substring( 1 ));
            return false;
        }
        return true;
    }

    

}
