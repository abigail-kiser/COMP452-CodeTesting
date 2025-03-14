public class ComputerGuessesGame {

    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    public ComputerGuessesGame() {
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;
        lastGuess = (lowerBound + upperBound + 1) / 2;
    }

    public void makeGuess(boolean isLowerBtn) {
        if (isLowerBtn) {
            upperBound = Math.min(upperBound, lastGuess);
        }
        else { // if higher btn
            lowerBound = Math.max(lowerBound, lastGuess + 1);
        }

        lastGuess = (lowerBound + upperBound + 1) / 2;
        numGuesses += 1;
    }

    public int getLastGuess() {
        return lastGuess;
    }

    public int getNumGuesses() {
        return numGuesses;
    }

}
