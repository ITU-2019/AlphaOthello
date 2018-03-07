import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AgainstDumbAI {

    /*
    @Test
    public void test4by4vsDumFirst() {
        int[] points = TestGame.playGame(new GameState(4, 1), new OthelloAI21(), new DumAI());
        System.out.println("OthelloAI : "  + points[0] + " \t Dumb AI: " + points[1]);
        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test4by4vsDumSecond() {
        int[] points = TestGame.playGame(new GameState(4, 2), new OthelloAI21(), new DumAI());
        System.out.println("OthelloAI : "  + points[0] + " \t Dumb AI: " + points[1]);
        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test5by5vsDumFirst() {
        int[] points = TestGame.playGame(new GameState(5, 1), new OthelloAI21(), new DumAI());
        System.out.println("OthelloAI : "  + points[0] + " \t Dumb AI: " + points[1]);
        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test5by5vsDumSecond() {
        int[] points = TestGame.playGame(new GameState(5, 2), new OthelloAI21(), new DumAI());
        System.out.println("OthelloAI : "  + points[0] + " \t Dumb AI: " + points[1]);
        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test6by6vsDumFirst() {
        int[] points = TestGame.playGame(new GameState(6, 1), new OthelloAI21(), new DumAI());
        System.out.println("OthelloAI : "  + points[0] + " \t Dumb AI: " + points[1]);
        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test6by6vsDumSecond() {
        int[] points = TestGame.playGame(new GameState(6, 2), new OthelloAI21(), new DumAI());
        System.out.println("OthelloAI : "  + points[0] + " \t Dumb AI: " + points[1]);
        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test7by7vsDumFirst() {
        int[] points = TestGame.playGame(new GameState(7, 1), new OthelloAI21(), new DumAI());
        System.out.println("OthelloAI : "  + points[0] + " \t Dumb AI: " + points[1]);
        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test7by7vsDumSecond() {
        int[] points = TestGame.playGame(new GameState(7, 2), new OthelloAI21(), new DumAI());
        System.out.println("OthelloAI : "  + points[0] + " \t Dumb AI: " + points[1]);
        assertTrue(points[0] > points[1]);
    }
    */

    @Test
    public void test8by8vsDumFirst() {
        int[] points = TestGame.playGame(new GameState(8, 1), new OthelloAI21(), new DumAI());
        System.out.println("OthelloAI : "  + points[0] + " \t Dumb AI: " + points[1]);
        assertTrue(points[0] > points[1]);
    }

    @Test
    public void test8by8vsDumSecond() {
        int[] points = TestGame.playGame(new GameState(8, 2), new OthelloAI21(), new DumAI());
        System.out.println("OthelloAI : "  + points[0] + " \t Dumb AI: " + points[1]);
        assertTrue(points[0] > points[1]);
    }

}
