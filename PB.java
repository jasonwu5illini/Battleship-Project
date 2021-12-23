/**
 * 
 *  class representing a Patrol Boat in our game
 *
 *  @author  benjamin
 *  @version May 19, 2020
 *  @author  Period: 3
 *  @author  Assignment: APCSfinal
 *
 *  @author  Sources: none
 */
public class PB extends boat
{
    private position[] pos = new position[2];
    private int hits = 0;
    private String name = "Patrol Boat";
    
    /**
     * constructor to make a patrol boat object
     * @param p position of one end of the ship
     * @param d direction of the body
     */
    PB(position p, String d){
        d.toUpperCase();
        if(!"NWES".contains( d ) || d.length() != 1) {
            System.out.println("Invalid Placement");
        }
        else {
            pos[0] = p;
            if(d.equals("N")) {
                pos[1] = new position( pos[0].getX(), pos[0].getY() + 1);
            }
            else if (d.equals("W")) {
                pos[1] = new position( pos[0].getX() - 1, pos[0].getY());
            }
            else if(d.equals("E")) {
                pos[1] = new position( pos[0].getX() + 1, pos[0].getY());
            }
            else {
                pos[1] = new position( pos[0].getX(), pos[0].getY() - 1);
            }
        }

    }
    
    /**
     * returns the name of the ship "patrol boat"
     * @return the string "patrol boat"
     */
    public String getName() {
        return name;
    }
    
    /**
     * Checks if the placement of the boat is inside the boundaries
     * @return if the boat placement is valid
     */
    public boolean isValid()
    {

        for(position p : pos) {
            if (!(p.getX() >= 1 && p.getX() <= 10 && p.getY() >= 1 && p.getY() <= 10)){
                System.out.println("Ship is out of bounds");
                return false;
            }
        }

        return true;
    }
    
    /**
     * 
     * the same as isValid, but does not print anything to the console
     * used for placing the AI's ships, which does not require an message to the player
     * checks if the boat is in a valid location
     * @return if the placement if valid or not
     */
    public boolean isValidNoMSG()
    {

        for(position p : pos) {
            if (!(p.getX() >= 1 && p.getX() <= 10 && p.getY() >= 1 && p.getY() <= 10)){
                return false;
            }
        }

        return true;
    }
    
    @Override
    /**
     * returns an array of positions that the ship covers
     * @return an array of positions that the ship covers
     */
    public position[] getPos()
    {
        // TODO Auto-generated method stub
        return pos;
        
    }
    
    @Override
    /**
     * true if the boat is sunk, false if it is still alive
     * @return true if the boat is sunk, false otherwise
     */
    public boolean isSunk()
    {
        return hits == 2;
    }
    
    /**
     * checks if the boat is covering a specified position, and if it is, 
     * increase the number of hits it took by one
     * @return if the shot hit the boat
     */
    public boolean checkHit(position shot) {
        for(position p : pos) {
            if (p.isSame( shot )){
                hits++;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if a boat is covering a specific position
     * @return true if a boat is covering a position
     */
    public boolean isOn(position other) {
        for(position p : pos) {
            if (p.isSame( other )){
                return true;
            }
        }
        return false;
    
    }
}
