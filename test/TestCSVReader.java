import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// mock
public class TestCSVReader extends CSVReader {


    String[] s1 = {LocalDateTime.now().toString(),"3"};
    String[] s2 = {LocalDateTime.now().toString(),"3"};
    String[] s3 = {"2020-02-24T20:15:28.750793100","9"}; //old time
    String[] s4 = {LocalDateTime.now().toString(),"1"};
    String[] s5 = {"2020-02-24T20:15:28.750793100","10"}; //old time
    String[] s6 = {LocalDateTime.now().toString(),"13"};
    ArrayList<String[]> s = new ArrayList<>(List.of(s1,s2,s3,s4,s5,s6));


    public TestCSVReader(Reader reader) {
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
