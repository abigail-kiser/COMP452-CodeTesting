import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * File-backed implementation of GameStats
 *
 * Returns the number of games *within the last 30 days* where the person took a given number of guesses
 */
public class StatsFile extends GameStats {
    // todo: shouldn't be public (make private, add pass-by-value getter) **DONE**
    private static final String FILENAME = "guess-the-number-stats.csv";


    // maps the number of guesses required to the number of games within
    // the past 30 days where the person took that many guesses
    private SortedMap<Integer, Integer> statsMap;

    // todo: take I/O out (make separate method) **DONE**

    // dependency injection
    public StatsFile(CSVReader r){
        statsMap = new TreeMap<>();

        try {
            String[] values = null;

            while ((values = r.readNext()) != null) {
                // values should have the date and the number of guesses as the two fields
                addStat(values);
            }
        }
        catch (CsvValidationException e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        } catch (IOException e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        }
    }

    public StatsFile() {
        this(createReader());
    }

    // this is the original FILENAME constructor component
    private static CSVReader createReader() {
        try{
            return new CSVReader(new FileReader(FILENAME));
        } catch (FileNotFoundException e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        }
        return null;
    }

    private void addStat(String[] values) {
        LocalDateTime limit = LocalDateTime.now().minusDays(30);

        try {
            LocalDateTime timestamp = LocalDateTime.parse(values[0]);
            int numGuesses = Integer.parseInt(values[1]);

            if (timestamp.isAfter(limit)) {
                statsMap.put(numGuesses, 1 + statsMap.getOrDefault(numGuesses, 0));
            }
        }
        catch(NumberFormatException nfe){
            // NOTE: In a full implementation, we would log this error and possibly alert the user
            throw nfe;
        }
        catch(DateTimeParseException dtpe){
            // NOTE: In a full implementation, we would log this error and possibly alert the user
            throw dtpe;
        }
    }

    //returns how many games were won in NG guesses
    @Override
    public int numGames(int numGuesses) {
        //numGuesses = the number of guesses it took to win
        return statsMap.getOrDefault(numGuesses, 0);
    }

    @Override
    public int maxNumGuesses(){
        return (statsMap.isEmpty() ? 0 : statsMap.lastKey());
    }

    public static String getFilename() {
        String fn = FILENAME;
        return fn;
    }

}
