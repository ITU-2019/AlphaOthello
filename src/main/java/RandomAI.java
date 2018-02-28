// package AlphaOthello;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple OthelloAI-implementation. The method to decide the next move just
 * returns the first legal move that it finds.
 * @author Mai Ajspur
 * @version 9.2.2018
 */
public class RandomAI implements IOthelloAI{
	/**
	 * Returns first legal move
	 */
	public Position decideMove(GameState s) {
		Random rand = new Random();

		ArrayList<Position> moves = s.legalMoves();
		if ( !moves.isEmpty() ) {
			int n = rand.nextInt(moves.size());
			return moves.get(n);
		} else {
			return new Position(-1,-1);
		}
	}

}
