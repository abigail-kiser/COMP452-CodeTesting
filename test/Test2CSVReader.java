import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// mock
public class Test2CSVReader extends CSVReader {


    String[] s1 = {"testtesttesttesttesttest","9"}; //old time
    String[] s2 = {"what time is it","1"};
    ArrayList<String[]> s = new ArrayList<>(List.of(s1,s2));


    public Test2CSVReader(Reader reader) {
        super(reader);
    }
    @Override
    public java.lang.String[] readNext() throws java.io.IOException, com.opencsv.exceptions.CsvValidationException {
        if (!s.isEmpty()){
            return s.remove(0);
        }
        else return null;
    }

}
