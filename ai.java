import java.util.Scanner;
/**
 * defines methods for all 3 kinds of ai 
 * abstract class makes it nice to create ai objects in the main battleship class
 * @author Jason 
 * @version May 25, 2020
 * @author Period: 3
 * @author Assignment: APCSfinal
 * @author Sources: none
 */
public abstract class ai
{

    
    /**
     * returns the next move of the ai player, with the algorithm differing for each level
     * @param b the board of the human player
     * @return the next move of the ai player
     */
    public abstract position nextMove(board b);
   
    /**
     * increments the score of the ai player
     */
    public abstract void addScore();
    /**
     * returns the score of the ai player
     * @return the score of the ai player
     */
    public abstract int getScore();

}
