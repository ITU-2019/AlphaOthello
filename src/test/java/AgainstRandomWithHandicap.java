import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class AgainstRandomWithHandicap {

    private final int RUNS = 10;
    
    private GameState GameStateWithHandicap(int size, int player, int handicap){
        GameState gameState = new GameState(size,1);
        int[][] board = gameState.getBoard();
        if(handicap >= 1) board[0][0] = 2;
        if(handicap >= 2) board[size-1][0] = 2;
        if(handicap >= 3) board[0][size-1] = 2;
        if(handicap >= 4) board[size-1][size-1] = 2;
        return gameState;
    }

    @Test
    public void test8by8vsRandomWithHandicap1() {
        int wins = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            int[] points = TestGame.playGame(GameStateWithHandicap(8, 1, 1), new OthelloAI21(), new RandomAI());
            if (points[0] > points[1]) {
                wins++;
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Wins: " + wins + " Losses: " + (RUNS - wins) + " --- Took " + ((endTime - startTime) / 1000000000) + "s");
        assertTrue(wins > (RUNS / 2));
    }

    @Test
    public void test8by8vsRandomWithHandicap2() {
        int wins = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            int[] points = TestGame.playGame(GameStateWithHandicap(8, 1, 2), new OthelloAI21(), new RandomAI());
            if (points[0] > points[1]) {
                wins++;
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Wins: " + wins + " Losses: " + (RUNS - wins) + " --- Took " + ((endTime - startTime) / 1000000000) + "s");
        assertTrue(wins > (RUNS / 2));
    }

    @Test
    public void test8by8vsRandomWithHandicap3() {
        int wins = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            int[] points = TestGame.playGame(GameStateWithHandicap(8, 1, 3), new OthelloAI21(), new RandomAI());
            if (points[0] > points[1]) {
                wins++;
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Wins: " + wins + " Losses: " + (RUNS - wins) + " --- Took " + ((endTime - startTime) / 1000000000) + "s");
        assertTrue(wins > (RUNS / 2));
    }


    @Test
    public void test8by8vsRandomWithHandicap4() {
        int wins = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            int[] points = TestGame.playGame(GameStateWithHandicap(8, 1, 4), new OthelloAI21(), new RandomAI());
            if (points[0] > points[1]) {
                wins++;
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Wins: " + wins + " Losses: " + (RUNS - wins) + " --- Took " + ((endTime - startTime) / 1000000000) + "s");
        assertTrue(wins > (RUNS / 2));
    }
}
