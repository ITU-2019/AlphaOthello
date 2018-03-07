
import java.util.HashMap;
import java.util.Arrays;



public class Memorizer {
	private int memorizerUsed;
	private int memorizerAdded;
	private HashMap<String, Integer> memorizer;

	public Memorizer() {
		memorizer = new HashMap<String, Integer>();
		memorizerAdded = 0;
		memorizerUsed = 0;
	}

	public int get(GameState gs, int depth) {
		String hash = getGameStateHashCode(gs, depth);
		return get(hash);
	}

	public boolean exist(String hash) {
		if (memorizer.containsKey(hash)) {
			return true;
		}

		return false;
	}

	public int get(String hash) {
		memorizerUsed++;
		return memorizer.get(hash);
	}

	public void put(String hash, int value) {
		memorizer.put(hash, value);
		memorizerAdded++;
	}

	public String getGameStateHashCode(GameState s, int depth) {
		int[][] board = s.getBoard();
		String boardString = "" + depth + s.getPlayerInTurn();
		for (int[] r : board) {
			boardString += Arrays.toString(r);
		}

		return boardString;
	}
}