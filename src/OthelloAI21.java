import java.util.ArrayList;

/**
 * A super-complex OthelloAI-implementation using alpha-beta pruning. 
 * @author Group21
 * @version 1.0
 */
public class OthelloAI21 implements IOthelloAI{
	private int currentPlayer;

	/**
	 * Returns the optimal move decided by the AI
	 */
	public Position decideMove(GameState s){
		currentPlayer = s.getPlayerInTurn();
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
			currentUtilityValue = evaluateTreeNode(gameState);

			//Find maximum position
			if (currentUtilityValue > maxUtilityValue){
				maxUtilityValue = currentUtilityValue;
				maxUtilityPosition = position;
			}
		}
		//Select max utility value of the possible actions (calculated from maxValue)
		return maxUtilityPosition;
	}

	/**
	 * The utility value of current player in given game state
	 * where it is the current player's turn.
	 * @param s game state at this node of the game tree
	 * @return a utility value
	 */
	public int maxValue(GameState s){
		if(s.isFinished()){
			return getStateUtility(s);
		}

		int maxUtilityValue = Integer.MIN_VALUE; //Minus infinity
		int currentUtilityValue;

		//Loop through all possible actions in game state s
		ArrayList<Position> legalMoves = s.legalMoves();
		if(legalMoves.size() == 0){
			GameState gameState = new GameState(s.getBoard(), s.getPlayerInTurn());
			gameState.changePlayer();
			return minValue(gameState);
		}

		for(Position position : legalMoves) {
			GameState gameState = new GameState(s.getBoard(), s.getPlayerInTurn());
			gameState.insertToken(position);

			currentUtilityValue = minValue(gameState);

			//Find maximum position
			if (currentUtilityValue > maxUtilityValue){
				maxUtilityValue = currentUtilityValue;
			}
		}

		//Select max utility value of the possible actions (calculated from maxValue)
		return maxUtilityValue;
	}

	/**
	 * The utility value of current player in a given state
	 * where it is the opponent's turn.
	 * @param s game state at this node of the game tree
	 * @return a utility value
	 * @see GameState
	 */
	public int minValue(GameState s){
		if(s.isFinished()){
			return getStateUtility(s);
		}

		int minUtilityValue = Integer.MAX_VALUE; //Max infinity
		int currentUtilityValue;

		//Loop through all possible actions in game state s
		ArrayList<Position> legalMoves = s.legalMoves();

		if(legalMoves.size() == 0){
			GameState gameState = new GameState(s.getBoard(), s.getPlayerInTurn());
			gameState.changePlayer();
			return maxValue(gameState);
		}

		for(Position position : legalMoves) {
			GameState gameState = new GameState(s.getBoard(), s.getPlayerInTurn());
			gameState.insertToken(position);

			currentUtilityValue = maxValue(gameState);

			//Find maximum position
			if (currentUtilityValue < minUtilityValue){
				minUtilityValue = currentUtilityValue;
			}
		}
		//Select max utility value of the possible actions (calculated from maxValue)
		return minUtilityValue;
	}

	public int evaluateTreeNode(GameState s){
		if(s.isFinished()){
			return getStateUtility(s);
		}

		int optimalUtilityValue = s.getPlayerInTurn() == currentPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentUtilityValue;

		//Loop through all possible actions in game state s
		ArrayList<Position> legalMoves = s.legalMoves();

		if(legalMoves.size() == 0){
			GameState gameState = new GameState(s.getBoard(), s.getPlayerInTurn());
			gameState.changePlayer();
			return evaluateTreeNode(gameState);
		}

		for(Position position : legalMoves) {
			GameState gameState = new GameState(s.getBoard(), s.getPlayerInTurn());
			gameState.insertToken(position);

			currentUtilityValue = evaluateTreeNode(gameState);

			//Find optimal position
			if (s.getPlayerInTurn() == currentPlayer && currentUtilityValue < optimalUtilityValue){
				optimalUtilityValue = currentUtilityValue;
			} else if (s.getPlayerInTurn() != currentPlayer && currentUtilityValue > optimalUtilityValue){
				optimalUtilityValue = currentUtilityValue;
			}
		}
		//Select max utility value of the possible actions (calculated from maxValue)
		return optimalUtilityValue;
	}

	/**
	 * Get utility of end state (or any other state)
	 * @param s game state
	 * @return state utility for current player
	 */
	public int getStateUtility(GameState s){
		int[] tokenCount= s.countTokens();
		if(currentPlayer == 1){
			return tokenCount[0] - tokenCount[1];
		} else {
			return tokenCount[1] - tokenCount[0];
		}
	}
	
}
