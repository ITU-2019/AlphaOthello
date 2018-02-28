

public class TestGame{
    
    public static Position getPlaceForNextToken(GameState gs, IOthelloAI ai1, IOthelloAI ai2) {
        if ( gs.getPlayerInTurn() == 1 )
			return ai1.decideMove(gs);
		else {
			return ai2.decideMove(gs);
		}
    }

    public static int[] playGame(GameState gs, IOthelloAI ai1, IOthelloAI ai2) {
        while(! gs.isFinished()) {
            Position action = getPlaceForNextToken(gs, ai1, ai2);
            gs.insertToken(action);
            if (gs.legalMoves().isEmpty()) {
                gs.changePlayer();
            }
        }
        int[] points = gs.countTokens();
        return points;
    }
}