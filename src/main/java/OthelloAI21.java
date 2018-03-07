import java.util.ArrayList;


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

	/**
	 * Get utility of end state (or any other state)
	 * @param s game state
	 * @return state utility for current player
	 */
	public int getStateUtility(GameState s) {
		int[] tokenCount= s.countTokens();
		if (tokenCount[0] == tokenCount[1]) return 0;
		if (currentPlayer == 1) {
			if(tokenCount[0] > tokenCount[1]) return 100;
			else return -100;
		} else {
			if (tokenCount[0] > tokenCount[1]) return -100;
			else return 100;
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
		//return sigmoid(strongPositions(s)+getStateUtility(s)/100);
		int[] tokenCount= s.countTokens();

		if (currentPlayer == 1) {
			return tokenCount[0] - tokenCount[1] + strongPositions(s);
		} else {
			return tokenCount[1] - tokenCount[0] + strongPositions(s);
		}

		//return getStateUtility(s) + strongPositions(s);
		/* //replace with clever evaluation:
		return getStateUtility(s);

		//f_1 empty squares modulo 2
		int emptySquares = emptySquares(s);


		//f_3 moves for me vs moves for opponent
		int[] tokens = s.countTokens();
		int moveValue = tokens[0] - tokens[1];
		int moves = (currentPlayer == 1) ? moveValue : -moveValue; */


	}

	// TODO: Tests sigmoid
	private int sigmoid(double x) {
		Double value = (1 / ( 1 + Math.pow(Math.E, (-1 * x)))) * 10 + 0.5d;
		return value.intValue();
	}

	private int strongPositions(GameState s){
		int strongPositionNum = 0;
		int[][] board = s.getBoard();
		for (int[] boardR : board) {
			if (boardR[0] == currentPlayer) {
				strongPositionNum += 1;
			}
			if (boardR[boardR.length-1] == currentPlayer) {
				strongPositionNum += 1;
			}
		}

		for (int boardC : board[0]) {
			if (boardC == currentPlayer) {
				strongPositionNum += 1;
			}
		}

		for (int boardC : board[board[0].length - 1]) {
			if (boardC == currentPlayer) {
				strongPositionNum += 1;
			}
		}

		if (board[0][0] == currentPlayer) {
			strongPositionNum += 2;
		}
		if (board[0][board[0].length - 1] == currentPlayer) {
			strongPositionNum += 2;
		}
		if (board[board[0].length - 1][0] == currentPlayer) {
			strongPositionNum += 2;
		}
		if (board[board[0].length - 1][board[0].length - 1] == currentPlayer) {
			strongPositionNum += 2;
		}

		return strongPositionNum;
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
}
