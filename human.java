import java.util.Scanner;

public class human
{
    public String name;
    public int score;
    
    public human(String n) {
        name = n;
        score = 0;
    }
    
    public position nextMove()
    {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Your Move?");
        String move = sc.nextLine().toUpperCase();
        while(!battleship.isProperMove(move)) {
            System.out.println("Move not recognized. Try again. ");
            move = sc.nextLine().toUpperCase();
        }
        return new position(move);

    }
    

    
    public void addScore() {
        score++;
    }
    
    public int getScore() {
        return score;
    }
    
    public String getName() {
        return name;
    }
    
}
