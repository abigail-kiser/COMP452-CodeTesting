import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.*;

class StatsFileTest {
    //one with more than one ret

    //using dependency injection
    @Test
    public void gameWithOneGuess(){
        TestCSVReader r = new TestCSVReader(Reader.nullReader());
        StatsFile s = new StatsFile(r);
        assertEquals(1,s.numGames(1));
    }

    @Test
    public void gameWithThreeGuesses(){
        TestCSVReader r = new TestCSVReader(Reader.nullReader());
        StatsFile s = new StatsFile(r);
        assertEquals(2,s.numGames(3));
    }


    //how many = 6
    @Test
    public void maxNumGuesses(){
        TestCSVReader r = new TestCSVReader(Reader.nullReader());
        StatsFile s = new StatsFile(r);
        assertEquals(13,s.maxNumGuesses());
    }

}