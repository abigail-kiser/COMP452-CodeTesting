import com.opencsv.CSVReader;

import java.io.Reader;
import java.util.ArrayList;

// mock
public class EmptyCSVReader extends CSVReader {

    ArrayList<String[]> s = new ArrayList<>();


    public EmptyCSVReader(Reader reader) {
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
