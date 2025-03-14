import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class StatsFileTest {

    // all but the last 2 tests are using dependency injection
    @Test
    public void gameWithOneGuess(){
        //using dependency injection
        TestCSVReader r = new TestCSVReader(Reader.nullReader());
        StatsFile s = new StatsFile(r);
        assertEquals(1,s.numGames(1));
    }

    @Test
    public void gameWithTwoGuesses(){
        TestCSVReader r = new TestCSVReader(Reader.nullReader());
        StatsFile s = new StatsFile(r);
        assertEquals(2,s.numGames(3));
    }

    @Test
    public void gameWithBiggerGuess(){
        TestCSVReader r = new TestCSVReader(Reader.nullReader());
        StatsFile s = new StatsFile(r);
        assertEquals(0,s.numGames(15));
    }

    @Test
    public void gameWithSmallerGuess(){
        TestCSVReader r = new TestCSVReader(Reader.nullReader());
        StatsFile s = new StatsFile(r);
        assertEquals(0,s.numGames(0));
    }

    @Test
    public void gameWithOldGuesses(){
        TestCSVReader r = new TestCSVReader(Reader.nullReader());
        StatsFile s = new StatsFile(r);
        assertEquals(0,s.numGames(9));
    }

    // expects an exception
    @Test
    public void numGamesWithWrongTime(){
        //catches error
        Test2CSVReader r = new Test2CSVReader(Reader.nullReader());
        try {
            StatsFile s = new StatsFile(r);
            //s.numGames(9);
            //should not get here
            fail();
        } catch (DateTimeParseException dtpe) {
            //should go here
        }
    }

    @Test
    public void maxNumGuesses(){
        TestCSVReader r = new TestCSVReader(Reader.nullReader());
        StatsFile s = new StatsFile(r);
        assertEquals(13,s.maxNumGuesses());
    }

    @Test
    public void emptyMaxNumGuesses(){
        EmptyCSVReader r = new EmptyCSVReader(Reader.nullReader());
        StatsFile s = new StatsFile(r);
        assertEquals(0,s.maxNumGuesses());
    }
}