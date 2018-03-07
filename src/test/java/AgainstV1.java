import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AgainstV1 {

    @Test
    public void test8by8vsV1White() {
        boolean won = false;

        long startTime = System.nanoTime();

        int[] points = TestGame.playGame(new GameState(8, 1), new OthelloAI21(), new OthelloAI21V1());
        if (points[0] > points[1]) {
            won = true;
        }

        long endTime = System.nanoTime();

        System.out.println("Win: " + won + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(won);
    }

    @Test
    public void test8by8vsV1Black() {
        boolean won = false;

        long startTime = System.nanoTime();

        int[] points = TestGame.playGame(new GameState(8, 2), new OthelloAI21(), new OthelloAI21V1());
        if (points[0] > points[1]) {
             won = true;
        }

        long endTime = System.nanoTime();

        System.out.println("Win: " + won + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(won);
    }

    @Test
    public void test7by7vsV1White() {
        boolean won = false;

        long startTime = System.nanoTime();

        int[] points = TestGame.playGame(new GameState(7, 1), new OthelloAI21(), new OthelloAI21V1());
        if (points[0] > points[1]) {
            won = true;
        }

        long endTime = System.nanoTime();

        System.out.println("Win: " + won + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(won);
    }

    @Test
    public void test7by7vsV1Black() {
        boolean won = false;

        long startTime = System.nanoTime();

        int[] points = TestGame.playGame(new GameState(7, 2), new OthelloAI21(), new OthelloAI21V1());
        if (points[0] > points[1]) {
             won = true;
        }

        long endTime = System.nanoTime();

        System.out.println("Win: " + won + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(won);
    }

    @Test
    public void test9by9vsV1White() {
        boolean won = false;

        long startTime = System.nanoTime();

        int[] points = TestGame.playGame(new GameState(9, 1), new OthelloAI21(), new OthelloAI21V1());
        if (points[0] > points[1]) {
            won = true;
        }

        long endTime = System.nanoTime();

        System.out.println("Win: " + won + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(won);
    }

    @Test
    public void test9by9vsV1Black() {
        boolean won = false;

        long startTime = System.nanoTime();

        int[] points = TestGame.playGame(new GameState(9, 2), new OthelloAI21(), new OthelloAI21V1());
        if (points[0] > points[1]) {
             won = true;
        }

        long endTime = System.nanoTime();

        System.out.println("Win: " + won + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(won);
    }
}
