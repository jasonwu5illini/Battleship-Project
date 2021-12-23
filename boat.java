/**
 * 
 *  abstract class for the 5 boats 
 *  useful for putting all the boats into one array
 *
 *  @author  benjamin
 *  @version May 19, 2020
 *  @author  Period: 3
 *  @author  Assignment: APCSfinal
 *
 *  @author  Sources: TODO
 */
public abstract class boat
{

    /**
     * returns the list of positions the boat covers
     * @return the list of positions the boat covers
     */
    public abstract position[] getPos();

    /**
     * 
     * true if the boat is sunk, false otherwise
     * @return true if the boat is sunk, false otherwise
     */
    public abstract boolean isSunk();

    /**
     * checks a move hits this boat, and if it did, increment the number of hits on this boat by 1
     * @param move the move to be checked
     * @return true if the boat was hit, false otherwise
     */
    public abstract boolean checkHit( position move );

    /**
     * checks a move hits this boat
     * @param move the move to be checked
     * @return true if the boat was hit, false otherwise
     */
    public abstract boolean isOn( position p );

    /**
     * returns the name of the boat
     * @return name of the boat
     */
    public abstract String getName();
    

}
