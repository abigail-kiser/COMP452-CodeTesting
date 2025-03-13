import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * UI screen for when the computer is guessing a number
 *
 * Displays the computer's guesses and processes human's answers
 * Tracks the computer's guesses
 *
 * TODO: refactor this class
 */
public class ComputerGuessesPanel extends JPanel {

    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    // todo: separate UI stuff from logic (lambda functions are all logic except
    //  for setText)
    //  maybe move logic to non-UI class?
    public ComputerGuessesPanel(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback){
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel guessMessage = new JLabel("I guess ___.");
        guessMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(guessMessage);
        guessMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel prompt = new JLabel("Your number is...");
        this.add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));


        addButtons(cardsPanel, gameFinishedCallback, guessMessage);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            // todo: move this method out
            public void componentShown(java.awt.event.ComponentEvent e) {
                numGuesses = 0;
                upperBound = 1000;
                lowerBound = 1;

                lastGuess = (lowerBound + upperBound + 1) / 2;
                guessMessage.setText("I guess " + lastGuess + ".");
            }
        });
    }

    private void addButtons(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback, JLabel guessMessage) {
        JButton lowerBtn = new JButton("Lower");
        lowerBtn.addActionListener(e -> {
            upperBound = Math.min(upperBound, lastGuess);

            lastGuess = (lowerBound + upperBound + 1) / 2;
            numGuesses += 1;
            guessMessage.setText("I guess " + lastGuess + ".");
        });
        this.add(lowerBtn);
        lowerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        JButton correctBtn = new JButton("Equal");
        correctBtn.addActionListener(e -> {
            guessMessage.setText("I guess ___.");

            // Send the result of the finished game to the callback
            // todo: do we need to do this?? we only save if human was playing...
            GameResult result = new GameResult(false, lastGuess, numGuesses);
            gameFinishedCallback.accept(result);

            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.GAME_OVER.name());
        });
        this.add(correctBtn);
        correctBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        JButton higherBtn = new JButton("Higher");
        higherBtn.addActionListener(e -> {
            lowerBound = Math.max(lowerBound, lastGuess + 1);

            lastGuess = (lowerBound + upperBound + 1) / 2;
            numGuesses += 1;
            guessMessage.setText("I guess " + lastGuess + ".");
        });
        this.add(higherBtn);
        higherBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

}
