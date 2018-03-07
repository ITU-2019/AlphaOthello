import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class SpecificBoardTests {


    private GameState getEmptyGameState(){
        GameState gameState = new GameState(8,1);
        int[][] board = gameState.getBoard();
        board[4][4] = 0;
        board[4][5] = 0;
        board[5][4] = 0;
        board[5][5] = 0;
        return gameState;
    }

    /*
    *   1 0 0 0 0 0 0 0
    *   2 2 0 0 0 0 0 0
    *   0 0 0 2 0 0 0 0
    *   0 0 0 0 0 0 0 0
    *   0 0 0 0 0 0 0 0
    *   0 0 0 0 0 0 0 0
    *   0 0 0 0 0 0 0 0
    *   0 0 0 0 0 0 0 0
    */
    @Test
    public void BoardTest1() {
        GameState gs = getEmptyGameState();
        int[][] board = gs.getBoard();
        board[0][0] = 1;
        board[0][1] = 2;
        board[1][1] = 2;
        board[4][3] = 2;
        


        OthelloAI21 ai = new OthelloAI21();
        Position p1 = gs.legalMoves().get(0);
        Position p2 = ai.decideMove(gs);

        System.out.println(p2);

        assertTrue(p1.equals(p2));
    }
}
