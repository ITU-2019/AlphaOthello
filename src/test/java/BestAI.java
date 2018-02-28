import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BestAI {

    private final int RUNS = 100;

    public Position getPlaceForNextToken(GameState gs, IOthelloAI ai1, IOthelloAI ai2) {
        if ( gs.getPlayerInTurn() == 1 )
			return ai1.decideMove(gs);
		else {
			return ai2.decideMove(gs);
		}
    }

    public int[] playGame(GameState gs, IOthelloAI ai1, IOthelloAI ai2) {
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

    @Test
    public void test4by4vsDumFirst() {
        int[] points = playGame(new GameState(4, 1), new OthelloAI21(), new DumAI());
        System.out.println(points[0]);
        System.out.println(points[1]);

        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test4by4vsDumSecond() {
        int[] points = playGame(new GameState(4, 2), new OthelloAI21(), new DumAI());
        System.out.println(points[0]);
        System.out.println(points[1]);

        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test5by5vsDumFirst() {
        int[] points = playGame(new GameState(5, 1), new OthelloAI21(), new DumAI());
        System.out.println(points[0]);
        System.out.println(points[1]);

        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test5by5vsDumSecond() {
        int[] points = playGame(new GameState(5, 2), new OthelloAI21(), new DumAI());
        System.out.println(points[0]);
        System.out.println(points[1]);

        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test6by6vsDumFirst() {
        int[] points = playGame(new GameState(6, 1), new OthelloAI21(), new DumAI());
        System.out.println(points[0]);
        System.out.println(points[1]);

        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test6by6vsDumSecond() {

        int[] points = playGame(new GameState(6, 2), new OthelloAI21(), new DumAI());
        System.out.println(points[0]);
        System.out.println(points[1]);

        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test4by4vsRandom() {
        int wins = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            int[] points = playGame(new GameState(4, 1), new OthelloAI21(), new RandomAI());
            if (points[0] > points[1]) {
                wins++;
            }
        }
        long endTime = System.nanoTime();

        System.out.println("Wins: " + wins + " Losses: " + (RUNS - wins) + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(wins > (RUNS / 2));
    }

    @Test
    public void test5by5vsRandom() {
        int wins = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            int[] points = playGame(new GameState(5, 1), new OthelloAI21(), new RandomAI());
            if (points[0] > points[1]) {
                wins++;
            }
        }
        long endTime = System.nanoTime();

        System.out.println("Wins: " + wins + " Losses: " + (RUNS - wins) + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(wins > (RUNS / 2));
    }

    @Test
    public void test6by6vsRandom() {
        int wins = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            int[] points = playGame(new GameState(6, 1), new OthelloAI21(), new RandomAI());
            if (points[0] > points[1]) {
                wins++;
            }
        }
        long endTime = System.nanoTime();

        System.out.println("Wins: " + wins + " Losses: " + (RUNS - wins) + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(wins > (RUNS / 2));
    }
}
