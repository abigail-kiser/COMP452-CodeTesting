import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Displays statistics about how many guesses the person took during past games
 * Loads data from the file and displays in a JPanel
 *
 * TODO: refactor this class **DONE**
 */
public class StatsPanel extends JPanel {

    private final JPanel resultsPanel;

    // Stats will display the number of games in each "bin"
    // A bin goes from BIN_EDGES[i] through BIN_EDGES[i+1]-1, inclusive //OBOB
    private static final int [] BIN_EDGES = {1, 2, 4, 6, 8, 10, 12, 14};
    private ArrayList<JLabel> resultsLabels;

    public StatsPanel(JPanel cardsPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel title = mkTextLabel("Your Stats");
        JLabel subtitle = mkTextLabel("(Past 30 Days)");

        this.add(Box.createRigidArea(new Dimension(0,40)));

        resultsPanel = new JPanel();
        mkResultsPanel();
        updateResultsPanel();

        this.add(Box.createVerticalGlue());

        addQuitBtn(cardsPanel);

        this.add(Box.createRigidArea(new Dimension(0,20)));

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                updateResultsPanel();
            }
        });
    }

    private void addQuitBtn(JPanel cardsPanel) {
        JButton quit = new JButton("Back to Home");
        quit.addActionListener(e -> {
            // See itemStateChanged in https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java
            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.HOME.name());
        });
        this.add(quit);
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
    }


    private JLabel mkTextLabel(String msg){
        JLabel txt = new JLabel(msg);
        this.add(txt);
        txt.setAlignmentX(Component.CENTER_ALIGNMENT);
        return txt;
    }

    // returns binName
    private static String getBinName(int binIndex) {
        if(binIndex == BIN_EDGES.length-1){
            // last bin
            return BIN_EDGES[binIndex] + " or more";
        }
        else{
            int upperBound = BIN_EDGES[binIndex +1] - 1;
            if(upperBound > BIN_EDGES[binIndex]){
                return BIN_EDGES[binIndex] + "-" + upperBound;
            }
            else{
                return Integer.toString(BIN_EDGES[binIndex]);
            }
        }
    }

    private void clearResults(){
        for(JLabel lbl : resultsLabels){
            lbl.setText("--");
        }
    }

    // todo: added dependency injection (stats) **DONE**
    private void updateResultsPanel(GameStats stats){
        clearResults();

        for(int binIndex=0; binIndex<BIN_EDGES.length; binIndex++){
            final int lowerBound = BIN_EDGES[binIndex];
            int numGames = 0;

            if(binIndex == BIN_EDGES.length-1){
                // last bin
                // Sum all the results from lowerBound on up
                for(int numGuesses=lowerBound; numGuesses<stats.maxNumGuesses(); numGuesses++){
                    numGames += stats.numGames(numGuesses);
                }
            }
            else{
                int upperBound = BIN_EDGES[binIndex+1];
                for(int numGuesses=lowerBound; numGuesses <= upperBound; numGuesses++) {
                    numGames += stats.numGames(numGuesses);
                }
            }

            JLabel resultLabel = resultsLabels.get(binIndex);
            resultLabel.setText(Integer.toString(numGames));
        }
    }

    private void mkResultsPanel() {
        resultsLabels = new ArrayList<>();
        resultsPanel.setLayout(new GridLayout(0, 2));
        resultsPanel.add(new JLabel("Guesses"));
        resultsPanel.add(new JLabel("Games"));
        for(int binIndex=0; binIndex<BIN_EDGES.length; binIndex++){
            // todo: extract binName logic to separate method **DONE**
            resultsPanel.add(new JLabel(getBinName(binIndex)));
            JLabel result = new JLabel("--");
            resultsLabels.add(result);
            resultsPanel.add(result);
        }

        resultsPanel.setMinimumSize(new Dimension(120, 120));
        this.add(resultsPanel);
        resultsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void updateResultsPanel(){
        clearResults();
        GameStats stats = new StatsFile();
        updateResultsPanel(stats);
    }
}

