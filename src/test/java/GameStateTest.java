import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class GameStateTest {

    @Test
    public void defaultTest() {
        GameState gs = new GameState(4, 1);
        assertEquals(gs.getPlayerInTurn(), 1);

        // Check switching of players after placing token.
        gs.insertToken(gs.legalMoves().get(0));
        assertEquals(gs.getPlayerInTurn(), 2);

        // Check AI is dumb and picks first move.
        DumAI ai = new DumAI();
        Position p1 = gs.legalMoves().get(0);
        Position p2 = ai.decideMove(gs);

        System.out.println(p1);
        System.out.println(p2);

        assertTrue(p1.equals(p2));
    }

    @Test
	public void pattern() {
    	GameState gs = new GameState(8, 1);

    	OthelloAI21 ai = new OthelloAI21();
		int[][] r = ai.genDiag(4, gs.getBoard());
    	for(int i = 0; i < 1; i++){
    		for(int j = 0; j < 4; j++){
    			System.out.println(r[i][j]);
			}
		}

	}
}
