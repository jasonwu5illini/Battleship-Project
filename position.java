import java.util.Hashtable;

/**
 * 
 *  class which represents a position in battleship
 *  useful for converting strings like A6 into integer coordinates
 *
 *  @author  benjamin
 *  @version May 19, 2020
 *  @author  Period: 3
 *  @author  Assignment: APCSfinal
 *
 *  @author  Sources: none
 */
public class position
{

    private String pos;
    private int xpos;
    private int ypos;
    Hashtable<Integer, String> numbers = new Hashtable<Integer, String>();
    Hashtable<Character, Integer> numbers2 = new Hashtable<Character, Integer>();
    
    /**
     * constructor which sets x and y to -1 
     */
    position() {
        this.xpos = -1;
        this.ypos = -1;
    }
    
    /**
     * constructor that makes a new position
     * @param x x coordinate
     * @param y y coordinate
     */
    position(int x, int y) {
        this.xpos = x;
        this.ypos = y;
    }
    
    /**
     * constructor that makes a new position from battleship coordinates like A6
     * @param s the battleship coordinate
     */
    public position(String s) {
        s = s.toUpperCase();
        numbers2.put( 'A', 1 );
        numbers2.put( 'B', 2 );
        numbers2.put( 'C', 3 );
        numbers2.put( 'D', 4 );
        numbers2.put( 'E', 5 );
        numbers2.put( 'F', 6 );
        numbers2.put( 'G', 7 );
        numbers2.put( 'H', 8 );
        numbers2.put( 'I', 9 );
        numbers2.put( 'J', 10 );
        this.xpos = numbers2.get(s.charAt( 0 ));
        this.ypos = Integer.parseInt( s.substring( 1 ) );
    }
    
    /**
     * 
     * returns the position in battleship coordinates
     * @return a string that is the position in battleship coordinates
     */
    public String printString() {
        numbers.put( 1, "A" );
        numbers.put( 2, "B" );
        numbers.put( 3, "C" );
        numbers.put( 4, "D" );
        numbers.put( 5, "E" );
        numbers.put( 6, "F" );
        numbers.put( 7, "G" );
        numbers.put( 8, "H" );
        numbers.put( 9, "I" );
        numbers.put( 10, "J" );
        return numbers.get( xpos ) + this.ypos;

    }
    
    /**
     * returns the x coordinate
     * @return x coordinate
     */
    public int getX() {
        return xpos;
    }
    
    /**
     * returns the y coordinate
     * @return y coordinate
     */
    public int getY() {
        return ypos;
    }
    
    /**
     * checks if a position is the same as another position
     * @param other the other position
     * @return if the other position is the same as this one
     */
    public boolean isSame(position other) {
        return (this.getX() == other.getX() && this.getY() == other.getY());
    }
    
    @Override
    /**
     * checks if a position is the same as another position
     * @param other the other position
     * @return if the other position is the same as this one
     */
    public boolean equals(Object other) {
        return (this.getX() == ( (position)other ).getX() && this.getY() == ( (position)other ).getY());
    }
    
    @Override
    /**
     * returns the hashcode for a position
     * used to get the hashtable working properly
     * @return the int that is the hashcode
     */
    public int hashCode() {
        return xpos*100 + ypos;
    }
    
    @Override
    /**
     * returns a string that is the x coordinate and y coordinate
     * @return a string that represents this position
     */
    public String toString() {
        return String.valueOf( xpos ) + " " + String.valueOf( ypos );
    }
}
