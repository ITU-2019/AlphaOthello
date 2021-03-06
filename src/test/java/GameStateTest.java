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

        assertTrue(p1.equals(p2));
    }
}
