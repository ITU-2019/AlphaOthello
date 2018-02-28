import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

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
	private final int CUT_OFF = 10;


	private int memorizerUsed;
	private int memorizerAdded;
	private HashMap<String, Integer> memorizer;

	public OthelloAI21(){
		memorizer = new HashMap<>();
		memorizerAdded = 0;
		memorizerUsed = 0;
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
		if(s.isFinished() || depth >= CUT_OFF){
			return getStateUtility(s);
		}

		int optimalUtilityValue = isMaxPlayer(s) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentUtilityValue;

		//Loop through all possible actions in game state s
		ArrayList<Position> legalMoves = s.legalMoves();

		//If pass
		if(legalMoves.size() == 0){
			GameState gameState = new GameState(s.getBoard(), s.getPlayerInTurn());
			gameState.changePlayer();

			String hashCodeIndex =  getGameStateHashCode(gameState,depth);
			if(memorizer.containsKey(hashCodeIndex)){
				memorizerUsed++;
				return memorizer.get(hashCodeIndex);
			} else {
				int passMoveResult = evaluateTreeNode(gameState, depth+1);
				memorizer.put(hashCodeIndex, passMoveResult);
				memorizerAdded++;
				return passMoveResult;
			}
		}

		for(Position position : legalMoves) {
			GameState gameState = new GameState(s.getBoard(), s.getPlayerInTurn());
			gameState.insertToken(position);

			String hashCodeIndex =  getGameStateHashCode(gameState, depth);
			if(memorizer.containsKey(hashCodeIndex)){
				memorizerUsed++;
				return memorizer.get(hashCodeIndex);
			} else {
				currentUtilityValue = evaluateTreeNode(gameState, depth+1);
				memorizer.put(hashCodeIndex, currentUtilityValue);
				memorizerAdded++;
				//Player-specific functions
				if(isMaxPlayer(s)){
					optimalUtilityValue = (currentUtilityValue > optimalUtilityValue) ? currentUtilityValue : optimalUtilityValue;
					if (currentUtilityValue >= beta) {
						breaked += 1;
						return currentUtilityValue; //Break recursion based on beta-value
					} else {
						nonBreaked += 1;
					}
					alpha = (alpha > currentUtilityValue) ? alpha : currentUtilityValue;
				} else if (!isMaxPlayer(s)){
					optimalUtilityValue = (currentUtilityValue < optimalUtilityValue) ? currentUtilityValue : optimalUtilityValue;
					if (currentUtilityValue <= alpha) {
						breaked += 1;
						return currentUtilityValue; //Break recursion based on alpha-value
					} else {
						nonBreaked += 1;
					}
				}
			}
			
		}
		//Select max utility value of the possible actions (calculated from maxValue)
		return optimalUtilityValue;
	}

	private boolean isMaxPlayer(GameState s){
		return s.getPlayerInTurn() == currentPlayer;
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

	public String getGameStateHashCode(GameState s, int depth){
		int[][] board = s.getBoard();
		String boardString = "" + depth + s.getPlayerInTurn();
		for(int[] r : board){
			boardString += Arrays.toString(r);
		}
		return boardString;
	}

}
