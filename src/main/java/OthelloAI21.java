import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.lang.*;

/**
 * A super-complex OthelloAI-implementation using alpha-beta pruning.
 * @author Group21
 * @version 1.0
 */
public class OthelloAI21 implements IOthelloAI{
	private int currentPlayer;
	private int breaked;
	private int nonBreaked;
	private int alpha;
	private int beta;
	private final int CUT_OFF = 9;

	Memorizer memo;

	public OthelloAI21() {
		memo = new Memorizer();
	}

	/**
	 * Returns the optimal move decided by the AI
	 */
	public Position decideMove(GameState s){
		currentPlayer = s.getPlayerInTurn();
		breaked = 0;
		nonBreaked = 0;
		alpha = Integer.MIN_VALUE;
		beta = Integer.MAX_VALUE;
		return minimaxDecision(s);
	}

	/**
	 * Return the optimal action from given game state
	 * for the current player.
	 * @param s state of the game
	 * @return a decicion calculated from the game tree
	 */
	public Position minimaxDecision(GameState s){
		int maxUtilityValue = Integer.MIN_VALUE; //Minus infinity
		Position maxUtilityPosition = null;
		int currentUtilityValue;
		//Loop through all possible actions in game state s
		for(Position position : s.legalMoves()) {
			GameState gameState = new GameState(s.getBoard(), currentPlayer);
			gameState.insertToken(position);
			currentUtilityValue = evaluateTreeNode(gameState, 0);

			//Find maximum position
			if (currentUtilityValue > maxUtilityValue){
				maxUtilityValue = currentUtilityValue;
				maxUtilityPosition = position;
			}
		}
		//Select max utility value of the possible actions (calculated from maxValue)
		/*
		System.out.println("----------------------------");
		System.out.println("Breaked: " + breaked);
		System.out.println("Non-breaked: " + nonBreaked);
		System.out.println("hashMapAdded: " + memorizerAdded);
		System.out.println("hashMapUsed: " + memorizerUsed);
		System.out.println("----------------------------");
		*/
		return maxUtilityPosition;
	}

	/**
	 * The utility value of current player in a given state
	 * @param s game state at this node of the game tree
	 * @return a utility value
	 * @see GameState
	 */
	public int evaluateTreeNode(GameState s, int depth){
		if (s.isFinished()) {
			return getStateUtility(s);
		} else if(depth >= CUT_OFF) {
			return evaluateState(s);
		}

		int optimalUtilityValue = isMaxPlayer(s) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentUtilityValue;

		//Loop through all possible actions in game state s
		ArrayList<Position> legalMoves = s.legalMoves();

		// Check if there is any legal moves from this state.
		if (legalMoves.size() == 0) {
			GameState gameState = new GameState(s.getBoard(), s.getPlayerInTurn());
			gameState.changePlayer();

			String hash = memo.getGameStateHashCode(gameState, depth);
			if (memo.exist(hash)) {
				return memo.get(hash);
			} else {
				int passMoveResult = evaluateTreeNode(gameState, depth + 1);
				memo.put(hash, passMoveResult);
				return passMoveResult;
			}
		}

		// Loop over valid player moves.
		for (Position position : legalMoves) {
			GameState gameState = new GameState(s.getBoard(), s.getPlayerInTurn());
			gameState.insertToken(position);

			String hash = memo.getGameStateHashCode(gameState, depth);
			if (memo.exist(hash)) {
				return memo.get(hash);
			} else {
				currentUtilityValue = evaluateTreeNode(gameState, depth+1);
				memo.put(hash, currentUtilityValue);

				// Player-specific functions
				optimalUtilityValue = getOptimalUtility(s, currentUtilityValue, optimalUtilityValue);
				Integer currentUtility = getCurrentUtility(s, currentUtilityValue, optimalUtilityValue);
				if (currentUtility != null) { // Current utility is 'better', return utility
					return (int) currentUtility;
				}
			}
		}

		//Select max utility value of the possible actions (calculated from maxValue)
		return optimalUtilityValue;
	}

	public int getOptimalUtility(GameState gs, int currentUtility, int optimalUtility) {
		if (isMaxPlayer(gs)) {
			optimalUtility = (currentUtility > optimalUtility) ? currentUtility : optimalUtility;
		} else {
			optimalUtility = (currentUtility < optimalUtility) ? currentUtility : optimalUtility;
		}

		return optimalUtility;
	}

	public Integer getCurrentUtility(GameState gs, int currentUtility, int optimalUtility) {
		if (isMaxPlayer(gs)) {
			if (currentUtility >= beta) {
				return currentUtility; //Break recursion based on beta-value
			}
			alpha = (alpha > currentUtility) ? alpha : currentUtility;
		} else {
			if (currentUtility <= alpha) {
				return currentUtility; //Break recursion based on alpha-value
			}
		}

		return null;
	}

	private boolean isMaxPlayer(GameState s) {
		return s.getPlayerInTurn() == currentPlayer;
	}

	public int boardValue (GameState s) {
		// @see http://mnemstudio.org/game-reversi-example-2.htm
		int base = 10;
		int region4 =(int) Math.pow(base, 1),  // Before-corners
			region2 =(int) Math.pow(base, 2),  // Before-edges
		    region1 =(int) Math.pow(base, 3),  // Middle
			region3 =(int) Math.pow(base, 4), // Edges
			region5 =(int) Math.pow(base, 5); // Corners

		int[][] board = s.getBoard();
		int len = board.length - 1;

		// The two players.
		int p1 = 0;
		int p2 = 0;

		// Loop over all board tiles.
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				int val = 0;
				if ( // Corner
					(i == 0 && j == 0) ||
					(i == len && j == len) ||
					(i == 0 && j == len) ||
					(j == 0 && i == len)
				) {
					val = region5;
				} else if ( // Edges
					(i == 0 && j >= 2 && j <= len-2) ||
					(j == 0 && i >= 2 && i <= len-2) ||
					(i == len && j >= 2 && j <= len-2) ||
					(j == len && i >= 2 && i <= len-2)
				) {
					val = region3;
				} else if ( // Before-edges
					(i == 1 && j >= 2 && j <= len-2) ||
					(j == 1 && i >= 2 && i <= len-2) ||
					(i == len-1 && j >= 2 && j <= len-2) ||
					(j == len-1 && i >= 2 && i <= len-2)
				) {
					val = region2;
				} else if ( // Before-corners
					(i == 0 && j == 1) ||
					(i == 1 && j == 0) ||
					(i == 1 && j == 1) ||

					(i == 0 && j == len-1) ||
					(i == 1 && j == len) ||
					(i == 1 && j == len-1) ||

					(i == len && j == 1) ||
					(i == len-1 && j == 0) ||
					(i == len-1 && j == 1) ||

					(i == len && j == len-1) ||
					(i == len-1 && j == len) ||
					(i == len-1 && j == len-1)
				) {
					val = region4;
				} else { // Middle
					val = region1;
				}

				// Check player
				if (board[i][j] == 1) {
					p1 += val;
				} else if (board[i][j] == 2) {
					p2 += val;
				}
			}
		}

		if (currentPlayer == 1) {
			return p1 - p2;
		} else {
			return p2 - p1;
		}
	}

	/**
	 * Get utility of end state (or any other state)
	 * @param s game state
	 * @return state utility for current player
	 */
	public int getStateUtility(GameState s) {
		int state = boardValue(s);
		int win = 1000000;
		int loss = state / 2;

		int[] tokenCount= s.countTokens();
		if (tokenCount[0] == tokenCount[1]) return 0;
		if (currentPlayer == 1) {
			if(tokenCount[0] > tokenCount[1]) return win;
			else return loss;
		} else {
			if (tokenCount[0] > tokenCount[1]) return loss;
			else return win;
		}
	}

	/**
	 * Evaluates the current state.
	 * This is used with a cut-off value to make the AI faster.
	 * This is done by cutting off the evaluation of the game tree
	 * before reaching a terminal state.
	 * @param s game state to evaluate
	 * @return
	 */
	public int evaluateState(GameState s) {
		return boardValue(s);
	}

	// TODO: Tests sigmoid
	private int sigmoid(double x) {
		Double value = (1 / ( 1 + Math.pow(Math.E, (-1 * x)))) * 10 + 0.5d;
		return value.intValue();
	}


	private int emptySquares(GameState s) {
		int[][] board = s.getBoard();

		int tokens = 0;
		for (int[] aBoard : board) {
			for (int j = 0; j < board.length; j++) {
				if (aBoard[j] == 0)
					tokens++;
			}
		}
		return tokens;
	}

	class Memorizer {
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
}
