import java.util.ArrayList;
import java.util.Collections;
/**
 * 
 * @author Jason
 * @version May 25, 2020
 * @author Period: 3 
 * @author Assignment: APCS
 * 
 * @author Sources: None 
 */
public class easyAI extends ai {

	public int score;
	public board aiBoard;
	private ArrayList<Integer> pos = new ArrayList<Integer>(100);
	private int turn;

	easyAI(board b) {
		aiBoard = b;
		score = 0;
		for (int i = 0; i < 100; i++) {
			pos.add(i);
		}
		Collections.shuffle(pos);
		turn = 0;
	}

	public position nextMove(board b) {
		// TODO Auto-generated method stub
		int x = pos.get(turn) / 10 + 1;
		int y = pos.get(turn) % 10 + 1;
		turn++;
		return (new position(x, y));
	}

	public void addScore() {
		score++;
	}

	public int getScore() {
		return score;
	}

}
