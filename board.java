import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 *  A class that represents a board. Represents what one player sees in Battleship
 *
 *  @author  Benjamin and Jason
 *  @version May 19, 2020
 *  @author  Period: 3
 *  @author  Assignment: APCSfinal
 *
 *  @author  Sources: none
 */
public class board
{
    private Set<position> hits = new HashSet<position>();
    private Set<position> misses = new HashSet<position>();
    private boat[] boats = new boat[5];
    private Set<boat> sunkBoats = new HashSet<boat>();
    
    /**
     * 
     * returns true if any boats are still floating, false if all boats are sunk
     * @return if the player is still alive
     */
    public boolean alive() {
        if(boats[0].isSunk() && boats[1].isSunk() && boats[2].isSunk() && boats[3].isSunk() && boats[4].isSunk()) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * Adds a position to the set hits, which represents your shots that hit an enemy boat.
     * @param p the position that was a hit
     */
    public void addHit(position p) {
        hits.add( p );
    }
    
    /**
     * Adds a position to the set misses which represents your shots that missed the enemy boat's.
     * TODO Write your method description here.
     * @param p the position that was a miss
     */
    public void addMiss (position p) {
        misses.add( p );
    }

    /**
     * 
     * Returns the set of hits. All hits that this player made should be in this set
     * @return
     */
    public Set<position> getHits(){
        return hits;
    }
    
    /**
     * Returns the set of misses. All misses that this player made should be in this set
     * @return
     */
    public Set<position> getMisses(){
        return misses;
    }
    
    /**
     * Returns a set of opponent boats that this player has sunk. 
     * @return a set of opponent's boats which have been sunk
     */
    public Set<boat> getSunk(){
        return sunkBoats;
    }
    
    /**
     * Returns an array of boats that this board contains
     * @return the array of boats that this board contains
     */
    public boat[] getBoats(){
        return boats;
    }
    
    /**
     * updates the sunk list
     * @param otherBoard the other player's board
     */
    public void updateSunk(board otherBoard) {
        for(boat b : otherBoard.getBoats()) {
            if(b.isSunk()) {
                sunkBoats.add( b );
            }
        }
    }
    
    /**
     * checks is a move is a hit or a miss on this board
     * @param move the move to be checked
     * @return true if the move hit a boat on this board, false otherwise
     */
    public boolean isHit( position move )
    {
        for(boat b : boats) {
            if(b.checkHit(move)) {
                return true;
            }
        }
        return false;
    }
    /**
     * checks if a move is a hit without doing damage
     * @param move the move to be checked
     * @return true if is hit, false if not. 
     */
    public boolean isOn( position move )
    {
        for(boat b : boats) {
            if(b.isOn(move)) {
                return true;
            }
        }
        return false;
    }
    /**
     * a message for the player when one of their shots hits a boat
     * this is put in its own method so we can vary the hit messages in the future
     */
    public void hitMessage()
    {
        // TODO Auto-generated method stub
        System.out.println("HIT!");

    }

    /**
     * a message for the player when one of their shots misses a boat
     * this is put in its own method so we can vary the hit messages in the future
     */
    public void missMessage()
    {
        // TODO Auto-generated method stub
        System.out.println("Your last shot missed.");
        
    }

    /**
     * prints in to the console a 10 by 10 grid that represents the opponents "ocean"
     * hits that you have made will show up as an x, and misses will be a o
     * any position that is unknown will be a ?
     */
    public void printShots()
    {
        position p = new position();
        int sideNum = 10;
        
        System.out.println("Your hits and misses");
        for(int j = 10; j >= 1; j--) {
            for(int i = 1; i<= 10; i++) {
                p = new position(i, j);
                if( hits.contains( p )) {
                    System.out.print("X ");
                }
                else if( misses.contains( p )){
                    System.out.print( "O " );
                }
                else {
                    System.out.print("- ");
                }
            }
            System.out.print(sideNum);
            sideNum--;
            System.out.println();
        }
        System.out.println("A B C D E F G H I J");
        
    }
    
    /**
     * prints your side of the "ocean"
     * your boats will show up, as well as your opponents hits and misses
     * @param other your opponents board
     */
    public void printBoats(board other) {
        position p = new position();
        
        int sideNum = 10;
        for(int j = 10; j >= 1; j--) {
            for(int i = 1; i <= 10; i++) {
                p = new position(i, j);
                if(other.getHits().contains( p )) {
                    System.out.print("X ");
                }
                else if(other.getMisses().contains( p )) {
                    System.out.print("O ");
                }
                else if( boats[0].isOn(p)) {
                    System.out.print("P ");
                }
                else if(boats[1].isOn( p )) {
                    System.out.print("S ");
                }
                else if(boats[2].isOn( p )) {
                    System.out.print("D ");
                }
                else if(boats[3].isOn( p )) {
                    System.out.print("B ");
                }
                else if(boats[4].isOn( p )) {
                    System.out.print("C ");
                }
                else {
                    System.out.print( ". " );
                }
                
            }
            System.out.print(sideNum);
            sideNum--;
            System.out.println();
        }
        System.out.println("A B C D E F G H I J");
    }
    
    /**
     * 
     * Prints just the boats on this board
     */
    public void printBoats() {
        position p = new position();
        
        int sideNum = 10;
        
        for(int j = 10; j >= 1; j--) {
            for(int i = 1; i <= 10; i++) {
                p = new position(i, j);
                if( boats[0].isOn(p)) {
                    System.out.print("P ");
                }
                else if(boats[1].isOn( p )) {
                    System.out.print("S ");
                }
                else if(boats[2].isOn( p )) {
                    System.out.print("D ");
                }
                else if(boats[3].isOn( p )) {
                    System.out.print("B ");
                }
                else if(boats[4].isOn( p )) {
                    System.out.print("C ");
                }
                else {
                    System.out.print( ". " );
                }
                
            }
            System.out.print(sideNum);
            sideNum--;
            System.out.println();
        }
        System.out.println("A B C D E F G H I J");
    }
    
    
    /**
     * prints an empty board. empty board is useful for visualization of positions
     */
    public void printEmpty() {
        System.out.println(". . . . . . . . . . 10");
        System.out.println(". . . . . . . . . . 9");
        System.out.println(". . . . . . . . . . 8");
        System.out.println(". . . . . . . . . . 7");
        System.out.println(". . . . . . . . . . 6");
        System.out.println(". . . . . . . . . . 5");
        System.out.println(". . . . . . . . . . 4");
        System.out.println(". . . . . . . . . . 3");
        System.out.println(". . . . . . . . . . 2");
        System.out.println(". . . . . . . . . . 1");
        System.out.println("A B C D E F G H I J ");

    }
    
    /**
     * method to print the position of the patrol boat during boat placement
     */
    public void printPB() {
        position p = new position();
        int sideNum = 10;
        
        System.out.println("Your PB's status");
        for(int j = 10; j >= 1; j--) {
            for(int i = 1; i<= 10; i++) {
                p = new position(i, j);
                if( boats[0].isOn(p)) {
                    System.out.print("P ");
                }
                else {
                    System.out.print( ". " );
                }
            }
            System.out.print(sideNum);
            sideNum--;
            System.out.println();
        }
        System.out.println("A B C D E F G H I J");
    }
    
    /**
     * 
     * Method to print the submarine and all other boats placed before it during boat placement
     */
    public void printSUB() {
        position p = new position();
        int sideNum = 10;
        
        System.out.println("Your Submarine's status");
        for(int j = 10; j >= 1; j--) {
            for(int i = 1; i<= 10; i++) {
                p = new position(i, j);
                
                if( boats[0].isOn(p)) {
                    System.out.print("P ");
                }
                
                else if( boats[1].isOn(p)) {
                    System.out.print("S ");
                }
                else {
                    System.out.print( ". " );
                }
            }
            System.out.print(sideNum);
            sideNum--;
            System.out.println();
        }
        System.out.println("A B C D E F G H I J");
    }
    
    /**
     * 
     * Method to print the destroyer and all other boats placed before it during boat placement
     */
    public void printDES() {
        position p = new position();
        int sideNum = 10;
        
        System.out.println("Your Destroyer's status");
        for(int j = 10; j >= 1; j--) {
            for(int i = 1; i<= 10; i++) {
                p = new position(i, j);
                
                if( boats[0].isOn(p)) {
                    System.out.print("P ");
                }
                
                else if( boats[1].isOn(p)) {
                    System.out.print("S ");
                }
                else if(boats[2].isOn( p )) {
                    System.out.print( "D " );
                }
                else {
                    System.out.print( ". " );
                }
            }
            System.out.print(sideNum);
            sideNum--;
            System.out.println();
        }
        System.out.println("A B C D E F G H I J");
    }
    
    /**
     * 
     * Method to print the battleship and all other boats placed before it during boat placement
     */
    public void printBAT() {
        position p = new position();
        int sideNum = 10;
        
        System.out.println("Your Battleship's status");
        for(int j = 10; j >= 1; j--) {
            for(int i = 1; i<= 10; i++) {
                p = new position(i, j);
                
                if( boats[0].isOn(p)) {
                    System.out.print("P ");
                }
                
                else if( boats[1].isOn(p)) {
                    System.out.print("S ");
                }
                else if(boats[2].isOn( p )) {
                    System.out.print( "D " );
                }
                else if(boats[3].isOn( p )) {
                    System.out.print("B ");
                }
                else {
                    System.out.print( ". " );
                }
            }
            System.out.print(sideNum);
            sideNum--;
            System.out.println();
        }
        System.out.println("A B C D E F G H I J");
    }
    
    /**
     * 
     * Method to print the carrier and all other boats placed before it during boat placement
     */
    public void printCAR() {
        position p = new position();
        int sideNum = 10;
        
        System.out.println("Your Carrier's status");
        for(int j = 10; j >= 1; j--) {
            for(int i = 1; i<= 10; i++) {
                p = new position(i, j);
                
                if( boats[0].isOn(p)) {
                    System.out.print("P ");
                }
                
                else if( boats[1].isOn(p)) {
                    System.out.print("S ");
                }
                else if(boats[2].isOn( p )) {
                    System.out.print( "D " );
                }
                else if(boats[3].isOn( p )) {
                    System.out.print("B ");
                }
                else if(boats[4].isOn( p )) {
                    System.out.print("C ");
                }
                else {
                    System.out.print( ". " );
                }
            }
            System.out.print(sideNum);
            sideNum--;
            System.out.println();
        }
        System.out.println("A B C D E F G H I J");
    }
    

    /**
     * 
     * places the patrol boat on to the board at a specified position and direction
     */
    public void putPb() //Asks for a position and puts a boat there
    {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        
        
        System.out.println("Place Patrol Boat. Enter Head Position [A1-J10]");
        
        String headPos = (sc.nextLine().toUpperCase());    
        while(!battleship.isProperMove( headPos )) {
            System.out.println(headPos + " is not a valid position. Enter a new position");
            headPos = sc.nextLine().toUpperCase();
        }
        position p = new position(headPos);
        
        
        
        System.out.println("Place Patrol Boat. Enter Direction [N W E S]");
        
        String direction = sc.nextLine().toUpperCase();   
        direction = direction.toUpperCase();
        while(!"NWES".contains( direction ) || direction.length() != 1) {
            System.out.println(direction + " is not a valid direction. Enter a new direction");
            direction = sc.nextLine().toUpperCase();
        }
        
        
        PB patrol = new PB( p, direction);
        while( !patrol.isValid()) {
            System.out.println("Place Patrol Boat. Enter Head Position [A1-J10]");           
            
            headPos = (sc.nextLine().toUpperCase());    
            while(!battleship.isProperMove( headPos )) {
                System.out.println(headPos + " is not a valid position. Enter a new position");
                headPos = sc.nextLine().toUpperCase();
            }
            p = new position(headPos);
            
            System.out.println("Place Patrol Boat. Enter Direction [N W E S]");            
            direction = sc.nextLine().toUpperCase();

            while(!"NWES".contains( direction ) || direction.length() != 1) {
                System.out.println(direction + " is not a valid direction. Try Again");
                direction = sc.nextLine().toUpperCase();
            }        
            
            patrol = new PB(p, direction);
        }
        boats[0]=( patrol );
    }

    /**
     * 
     * places the submarine on to the board at a specified position and direction
     */
    public void putSub()
    {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Place Submarine. Enter Head Position [A1-J10]");
        String headPos = (sc.nextLine().toUpperCase());    
        while(!battleship.isProperMove( headPos )) {
            System.out.println(headPos + " is not a valid position. Enter a new position");
            headPos = sc.nextLine().toUpperCase();
        }
        position p = new position(headPos);
        
        
        
        System.out.println("Place Submarine. Enter Direction [N W E S]");
        String direction = sc.nextLine().toUpperCase();   
        direction = direction.toUpperCase();
        while(!"NWES".contains( direction ) || direction.length() != 1) {
            System.out.println(direction + " is not a valid direction. Enter a new direction");
            direction = sc.nextLine().toUpperCase();
        }
        
        
        Sub submarine = new Sub( p, direction);
        while( !submarine.isValid(boats[0])) {
            System.out.println("Place Submarine. Enter Head Position [A1-J10]");           
            
            headPos = (sc.nextLine().toUpperCase());    
            while(!battleship.isProperMove( headPos )) {
                System.out.println(headPos + " is not a valid position. Enter a new position");
                headPos = sc.nextLine().toUpperCase();
            }
            p = new position(headPos);
            
            
            
            
            System.out.println("Place Submarine. Enter Direction [N W E S]");            
            direction = sc.nextLine().toUpperCase();
            while(!"NWES".contains( direction ) || direction.length() != 1) {
                System.out.println(direction + " is not a valid direction. Enter a new direction");
                direction = sc.nextLine().toUpperCase();
            }        
            
            submarine = new Sub(p, direction);
        }
        boats[1]=( submarine );
    }

    /**
     * 
     * places the destroyer on to the board at a specified position and direction
     */
    public void putDes()
    {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Place Destroyer. Enter Head Position [A1-J10]");
        String headPos = (sc.nextLine().toUpperCase());    
        while(!battleship.isProperMove( headPos )) {
            System.out.println(headPos + " is not a valid position. Enter a new position");
            headPos = sc.nextLine().toUpperCase();
        }
        position p = new position(headPos);
        
        
        
        System.out.println("Place Destroyer. Enter Direction [N W E S]");
        String direction = sc.nextLine().toUpperCase();   
        direction = direction.toUpperCase();
        while(!"NWES".contains( direction ) || direction.length() != 1) {
            System.out.println(direction + " is not a valid direction. Enter a new direction");
            direction = sc.nextLine().toUpperCase();
        }
        
        
        Des dest = new Des( p, direction);
        while( !dest.isValid(boats[0], boats[1])) {
            System.out.println("Place Destroyer. Enter Head Position [A1-J10]");           
            headPos = (sc.nextLine().toUpperCase());    
            
            while(!battleship.isProperMove( headPos )) {
                System.out.println(headPos + " is not a valid position. Enter a new position");
                headPos = sc.nextLine().toUpperCase();
            }
            p = new position(headPos);
            
            
            
            
            System.out.println("Place Destroyer. Enter Direction [N W E S]");            
            direction = sc.nextLine().toUpperCase();

            while(!"NWES".contains( direction ) || direction.length() != 1) {
                System.out.println(direction + " is not a valid direction. Enter a new direction");
                direction = sc.nextLine().toUpperCase();
            }        
            
            dest = new Des(p, direction);
        }
        boats[2]=( dest );
    }

    /**
     * 
     * places the battleship on to the board at a specified position and direction
     */
    public void putBat()
    {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Place Battleship. Enter Head Position [A1-J10]");
        String headPos = (sc.nextLine().toUpperCase());    
        while(!battleship.isProperMove( headPos )) {
            System.out.println(headPos + " is not a valid position. Enter a new position");
            headPos = sc.nextLine().toUpperCase();
        }
        position p = new position(headPos);
        
        
        
        System.out.println("Place Battleship. Enter Direction [N W E S]");
        String direction = sc.nextLine().toUpperCase();   
        direction = direction.toUpperCase();
        while(!"NWES".contains( direction ) || direction.length() != 1) {
            System.out.println(direction + " is not a valid direction. Enter a new direction");
            direction = sc.nextLine().toUpperCase();
        }
        
        
        Bat batl = new Bat( p, direction);
        while( !batl.isValid(boats[0], boats[1], boats[2])) {
            System.out.println("Place Battleship. Enter Head Position [A1-J10]");                      
            headPos = (sc.nextLine().toUpperCase());    
            while(!battleship.isProperMove( headPos )) {
                System.out.println(headPos + " is not a valid position. Enter a new position");
                headPos = sc.nextLine().toUpperCase();
            }
            p = new position(headPos);
            
            
            
            
            System.out.println("Place Battleship. Enter Direction [N W E S]");            
            direction = sc.nextLine().toUpperCase();

            
            while(!"NWES".contains( direction ) || direction.length() != 1) {
                System.out.println(direction + " is not a valid direction. Enter a new direction");
                direction = sc.nextLine().toUpperCase();
            }        
            
            batl = new Bat(p, direction);
        }
        boats[3]=( batl );
    }

    /**
     * 
     * places the carrier on to the board at a specified position and direction
     */
    public void putCar()
    {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Place Carrier. Enter Head Position [A1-J10]");
        String headPos = (sc.nextLine().toUpperCase());    
        while(!battleship.isProperMove( headPos )) {
            System.out.println(headPos + " is not a valid position. Enter a new position");
            headPos = (sc.nextLine().toUpperCase());    
        }
        position p = new position(headPos);
        
        
        
        System.out.println("Place Carrier. Enter Direction [N W E S]");
        String direction = sc.nextLine().toUpperCase();   
        while(!"NWES".contains( direction ) || direction.length() != 1) {
            System.out.println(direction + " is not a valid direction. Enter a new direction");
            direction = sc.nextLine().toUpperCase();
        }
        
        
        Car cari = new Car( p, direction);
        while( !cari.isValid(boats[0], boats[1], boats[2], boats[3])) {
            System.out.println("Place Carrier. Enter Head Position [A1-J10]");           
            
            headPos = (sc.nextLine().toUpperCase());    
            while(!battleship.isProperMove( headPos )) {
                System.out.println(headPos + " is not a valid position. Enter a new position");
                headPos = sc.nextLine().toUpperCase();
            }
            p = new position(headPos);
            
            
            
            
            System.out.println("Place Carrier. Enter Direction [N W E S]");            
            direction = sc.nextLine().toUpperCase();

            
            while(!"NWES".contains( direction ) || direction.length() != 1) {
                System.out.println(direction + " is not a valid direction. Enter a new direction");
                direction = sc.nextLine().toUpperCase();
            }        
            
            cari = new Car(p, direction);
        }
        boats[4]=( cari );
    }

    /**
     * 
     * places all boats randomly onto the board
     */
    public void putBoats() // Randomly puts all the boats on the field
    {
        // TODO Auto-generated method stub
        
        
        
        PB p = new PB(new position(this.randCoord(), this.randCoord()), this.randD());
        while(!p.isValid())
        {
            
            p = new PB(new position(this.randCoord(), this.randCoord()), this.randD());
        }
        boats[0] = p;
        
        Sub s = new Sub(new position(this.randCoord(), this.randCoord()), this.randD());
        while(!s.isValidNoMSG(boats[0]))
        {
            s = new Sub(new position(this.randCoord(), this.randCoord()), this.randD());
        }
        boats[1] = s;
        
        Des d = new Des(new position(this.randCoord(), this.randCoord()), this.randD());
        while(!d.isValidNoMSG(boats[0],boats[1]))
        {
            d = new Des(new position(this.randCoord(), this.randCoord()), this.randD());
        }
        boats[2] = d;
        
        Bat b = new Bat(new position(this.randCoord(), this.randCoord()), this.randD());
        while(!b.isValidNoMSG(boats[0],boats[1],boats[2]))
        {
            b = new Bat(new position(this.randCoord(), this.randCoord()), this.randD());
        }
        boats[3] = b;
        
        Car c = new Car(new position(this.randCoord(), this.randCoord()), this.randD());
        while (!c.isValidNoMSG(boats[0],boats[1],boats[2],boats[3]))
        {
            c = new Car(new position(this.randCoord(), this.randCoord()), this.randD());
        }
        boats[4] = c;
    }

    /**
     * returns a random integer between 1 and 10 inclusive
     * @return random integer from 1 to 10 inclusive
     */
    public int randCoord() {
        return ((int) (Math.random() * 10) + 1);
    }

    /**
     * 
     * returns a random direction from [N W E S]
     * @return returns a random direction out of [N W E S]
     */
    public String randD() {
        int z;

        z = ((int) (Math.random() * 4) + 1);
        if (z == 1) {
            return ("N");
        } else if (z == 2) {
            return ("W");
        } else if (z == 3) {
            return ("S");
        } else {
            return ("E");
        }
    }


}
