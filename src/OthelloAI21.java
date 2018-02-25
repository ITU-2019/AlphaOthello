import java.util.ArrayList;

/**
 * A super-complex OthelloAI-implementation using alpha-beta pruning. 
 * @author Group21
 * @version 0.01
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
			currentUtilityValue = minValue(gameState);

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
		return Integer.MIN_VALUE;
	}

	/**
	 * The utility value of current player in a given state
	 * where it is the opponent's turn.
	 * @param s game state at this node of the game tree
	 * @return a utility value
	 * @see GameState
	 */
	public int minValue(GameState s){
		return Integer.MIN_VALUE;
	}
	
}
