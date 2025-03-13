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
    void makeHighGuess() {
        HumanGuessesGame g = new HumanGuessesGame(10);
        GuessResult result = g.makeGuess(50);
        assertEquals(GuessResult.HIGH, result);
    }

    @Test
    void makeCorrectGuess() {
        HumanGuessesGame g = new HumanGuessesGame(10);
        GuessResult result = g.makeGuess(10);
        assertEquals(GuessResult.CORRECT, result);
    }

    @Test
    void makeVeryHighGuess() {
        HumanGuessesGame g = new HumanGuessesGame(10);
        GuessResult result = g.makeGuess(80000000);
        assertEquals(GuessResult.HIGH, result);
    }

    @Test
    void makeVeryLowGuess() {
        HumanGuessesGame g = new HumanGuessesGame(10);
        GuessResult result = g.makeGuess(-60);
        assertEquals(GuessResult.LOW, result);
    }

//    @Test
//    void getNumGuesses() {
//    }
//
//    @Test
//    void isDone() {
//    }
    //i dont think these need to be tested
}