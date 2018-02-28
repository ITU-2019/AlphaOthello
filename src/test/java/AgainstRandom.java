import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AgainstRandom {

    private final int RunsVS4by4 = 100;
    private final int RunsVS5by5 = 100;
    private final int RunsVS6by6 = 50;
    private final int RunsVS7by7 = 50;
    private final int RunsVS8by8 = 50;
    
    
    @Test
    public void test4by4vsRandom() {
        int wins = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < RunsVS4by4; i++) {
            int[] points = TestGame.playGame(new GameState(4, 1), new OthelloAI21(), new RandomAI());
            if (points[0] > points[1]) {
                wins++;
            }
        }
        long endTime = System.nanoTime();

        System.out.println("Wins: " + wins + " Losses: " + (RunsVS4by4 - wins) + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(wins > (RunsVS4by4 / 2));
    }

    @Test
    public void test5by5vsRandom() {
        int wins = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < RunsVS5by5; i++) {
            int[] points = TestGame.playGame(new GameState(5, 1), new OthelloAI21(), new RandomAI());
            if (points[0] > points[1]) {
                wins++;
            }
        }
        long endTime = System.nanoTime();

        System.out.println("Wins: " + wins + " Losses: " + (RunsVS5by5 - wins) + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(wins > (RunsVS5by5 / 2));
    }

    @Test
    public void test6by6vsRandom() {
        int wins = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < RunsVS6by6; i++) {
            int[] points = TestGame.playGame(new GameState(6, 1), new OthelloAI21(), new RandomAI());
            if (points[0] > points[1]) {
                wins++;
            }
        }
        long endTime = System.nanoTime();

        System.out.println("Wins: " + wins + " Losses: " + (RunsVS6by6 - wins) + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(wins > (RunsVS6by6 / 2));
    }
    
    @Test
    public void test7by7vsRandom() {
        int wins = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < RunsVS7by7; i++) {
            int[] points = TestGame.playGame(new GameState(6, 1), new OthelloAI21(), new RandomAI());
            if (points[0] > points[1]) {
                wins++;
            }
        }
        long endTime = System.nanoTime();

        System.out.println("Wins: " + wins + " Losses: " + (RunsVS7by7 - wins) + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(wins > (RunsVS7by7 / 2));
    }

    
    @Test
    public void test8by8vsRandom() {
        int wins = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < RunsVS8by8; i++) {
            int[] points = TestGame.playGame(new GameState(6, 1), new OthelloAI21(), new RandomAI());
            if (points[0] > points[1]) {
                wins++;
            }
        }
        long endTime = System.nanoTime();

        System.out.println("Wins: " + wins + " Losses: " + (RunsVS8by8 - wins) + " --- Took " + ((endTime - startTime) / 1000000000) + "s");

        assertTrue(wins > (RunsVS8by8 / 2));
    }
}
