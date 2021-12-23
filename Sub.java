/**
 * 
 *  class that represents a submarine in our game
 *
 *  @author  benjamin
 *  @version May 19, 2020
 *  @author  Period: 3
 *  @author  Assignment: APCSfinal
 *
 *  @author  Sources: none
 */
public class Sub extends boat
{
    private position[] pos = new position[3];
    private int hits = 0;
    private String name = "Submarine";

    
    /**
     * constructor to make a submarine object
     * @param p position of one end of the ship
     * @param d direction of the body
     */
    Sub(position p, String d){
        d.toUpperCase();
        if(!"NWES".contains( d ) || d.length() != 1) {
            System.out.println("Invalid Placement");
        }
        else {
            pos[0] = p;
            if(d.equals("N")) {
                pos[1] = new position( pos[0].getX(), pos[0].getY() + 1);
                pos[2] = new position( pos[0].getX(), pos[0].getY() + 2);
            }
            else if (d.equals("W")) {
                pos[1] = new position( pos[0].getX() - 1, pos[0].getY());
                pos[2] = new position( pos[0].getX() - 2, pos[0].getY());
            }
            else if(d.equals("E")) {
                pos[1] = new position( pos[0].getX() + 1, pos[0].getY());
                pos[2] = new position( pos[0].getX() + 2, pos[0].getY());
            }
            else {
                pos[1] = new position( pos[0].getX(), pos[0].getY() - 1);
                pos[2] = new position( pos[0].getX(), pos[0].getY() - 2);
            }
        }

    }
    
    /**
     * returns the name of the ship "submarine"
     * @return the string "submarine"
     */
    public String getName() {
        return name;
    }
    
    /**
     * Checks if the placement of the boat is valid in accordance with 1 previously placed boat
     * @param b1 a boat to check overlap with
     * @return if the boat placement is valid
     */
    public boolean isValid(boat b0)
    {
        // TODO Auto-generated method stub
        position[] b0p = b0.getPos();


        for(position p : pos) {
            if (!(p.getX() >= 1 && p.getX() <= 10 && p.getY() >= 1 && p.getY() <= 10)){
                System.out.println("Ship is out of bounds");
                return false;
            }
        }
        for(position p : pos) {
            for(position other : b0p) {
                if (p.isSame( other )){
                    System.out.println("Ship overlap with Patrol Boat");
                    return false;
                }
            }
            
        }
        return true;
    }
    
    /**
     * 
     * the same as isValid, but does not print anything to the console
     * used for placing the AI's ships, which does not require an message to the player
     * checks if the boat is in a valid location
     * @param b1 a boat to check overlap with
     * @return if the placement if valid or not
     */
    public boolean isValidNoMSG(boat b0)
    {
        // TODO Auto-generated method stub
        position[] b0p = b0.getPos();


        for(position p : pos) {
            if (!(p.getX() >= 1 && p.getX() <= 10 && p.getY() >= 1 && p.getY() <= 10)){
                return false;
            }
        }
        for(position p : pos) {
            for(position other : b0p) {
                if (p.isSame( other )){
                    return false;
                }
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
        return pos;
        
    }
    @Override
    /**
     * true if the boat is sunk, false if it is still alive
     * @return true if the boat is sunk, false otherwise
     */
    public boolean isSunk()
    {
        return hits == 3;
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
    public boolean isOn(position shot) {
        for(position p : pos) {
            if (p.isSame( shot )){
                return true;
            }
        }
        return false;
    }
}
