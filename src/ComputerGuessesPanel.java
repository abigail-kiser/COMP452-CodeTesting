import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * UI screen for when the computer is guessing a number
 *
 * Displays the computer's guesses and processes human's answers
 * Tracks the computer's guesses
 *
 * TODO: refactor this class **DONE**
 */

public class ComputerGuessesPanel extends JPanel {

    private ComputerGuessesGame game;

    public ComputerGuessesPanel(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback){
//        game = new ComputerGuessesGame();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel guessMessage = addTextLabel("I guess ___.", 0, 40);

        JLabel prompt = addTextLabel("Your number is...", 0, 10);

        addButtons(cardsPanel, gameFinishedCallback, guessMessage);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                game = new ComputerGuessesGame();
                guessMessage.setText("I guess " + game.getLastGuess() + ".");
            }
        });
    }

    private JLabel addTextLabel(String displayTxt, int width, int height){
        JLabel txtLabel = new JLabel(displayTxt);
        this.add(txtLabel);
        txtLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(width,height)));
        return txtLabel;
    }

    private void addButtons(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback, JLabel guessMessage) {
        JButton lowerBtn = new JButton("Lower");
        lowerBtn.addActionListener(e -> {
            game.makeGuess(true);
            guessMessage.setText("I guess " + game.getLastGuess() + ".");
        });
        this.add(lowerBtn);
        lowerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        JButton correctBtn = new JButton("Equal");
        correctBtn.addActionListener(e -> {
            guessMessage.setText("I guess ___.");

            // Send the result of the finished game to the callback
            GameResult result = new GameResult(false, game.getLastGuess(), game.getNumGuesses());
            gameFinishedCallback.accept(result);

            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.GAME_OVER.name());
        });
        this.add(correctBtn);
        correctBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        JButton higherBtn = new JButton("Higher");
        higherBtn.addActionListener(e -> {
            game.makeGuess(false);
            guessMessage.setText("I guess " + game.getLastGuess() + ".");
        });
        this.add(higherBtn);
        higherBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }


}
