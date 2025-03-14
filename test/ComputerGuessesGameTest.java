import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputerGuessesGameTest {


    @Test
    void guessTenLows() {
        ComputerGuessesGame g = new ComputerGuessesGame();
        for (int i = 0; i < 10; i++) {
            g.makeGuess(true);
        }
        assertEquals(1, g.getLastGuess());
    }

    @Test
    void guessTenHighs() {
        ComputerGuessesGame g = new ComputerGuessesGame();
        for (int i = 0; i < 10; i++) {
            g.makeGuess(false);
        }
        assertEquals(1000, g.getLastGuess());
    }

    @Test
    void guessThreeLows() {
        ComputerGuessesGame g = new ComputerGuessesGame();
        // round down for guessing lower
        g.makeGuess(true); // 251
        g.makeGuess(true); // 125
        g.makeGuess(true); // 62
        assertEquals(62, g.getLastGuess());
    }

    @Test
    void guessThreeHigh() {
        ComputerGuessesGame g = new ComputerGuessesGame();
        // round up for guessing higher
        g.makeGuess(false); // 751
        g.makeGuess(false); // 876
        g.makeGuess(false); // 938
        assertEquals(938, g.getLastGuess());
    }

    @Test
    void getNumGuessesZero() {
        ComputerGuessesGame g = new ComputerGuessesGame();
        assertEquals(0, g.getNumGuesses());
    }

    @Test
    void getNumGuessesOne() {
        ComputerGuessesGame g = new ComputerGuessesGame();
        g.makeGuess(true);
        assertEquals(1, g.getNumGuesses());
    }

    @Test
    void getNumGuessesTwo() {
        ComputerGuessesGame g = new ComputerGuessesGame();
        g.makeGuess(true);
        g.makeGuess(false);
        assertEquals(2, g.getNumGuesses());
    }

    @Test
    void getNumGuessesNine() {
        ComputerGuessesGame g = new ComputerGuessesGame();
        g.makeGuess(true);
        g.makeGuess(true);
        g.makeGuess(false);
        g.makeGuess(false);
        g.makeGuess(true);
        g.makeGuess(false);
        g.makeGuess(false);
        g.makeGuess(true);
        g.makeGuess(false);
        assertEquals(9, g.getNumGuesses());
    }

    @Test
    void getNumGuessesOverLimit() {
        ComputerGuessesGame g = new ComputerGuessesGame();
        // 20 guesses - spam false at end
        g.makeGuess(true);
        g.makeGuess(true);
        g.makeGuess(true);
        g.makeGuess(false);
        g.makeGuess(false);
        g.makeGuess(true);
        g.makeGuess(false);
        g.makeGuess(false);
        g.makeGuess(false);
        g.makeGuess(true);
        g.makeGuess(false); // where it stops changing numbers
        g.makeGuess(false);
        g.makeGuess(false);
        g.makeGuess(false);
        g.makeGuess(false);
        g.makeGuess(false);
        g.makeGuess(false);
        g.makeGuess(false);
        g.makeGuess(false);
        assertEquals(11, g.getNumGuesses());
    }

}
