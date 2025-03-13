import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HumanGuessesGameTest {

    @Test
    void makeLowGuess() {
        HumanGuessesGame g = new HumanGuessesGame(10);
        GuessResult result = g.makeGuess(2);
        assertEquals(GuessResult.LOW, result);
    }

    @Test
    void getNumGuesses() {
    }

    @Test
    void isDone() {
    }
}