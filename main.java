import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {

        //Check to see if the file exists before trying to create a new one (NOT WORKING YET)
        /*
        if("sheet_1.csv".exists())
        */

        FileWriter csvWriter = new FileWriter("sheet_1.csv");
        add_col(csvWriter, "Employee Name");
        csvWriter.close();

    }
    public static void updateCSV(String fileToUpdate, String replace,
                                 int row, int col) throws IOException {

        File inputFile = new File(fileToUpdate);

// Read existing file
        CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
        List<String[]> csvBody = reader.readAll();
// get CSV row column  and replace with by using row and column
        csvBody.get(row)[col] = replace;
        reader.close();

// Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }
    public static void add_col(FileWriter csvWriter, String col_name) throws IOException {
        csvWriter.append(col_name);
        csvWriter.append(",");
        return;
    }
    public static void add_item(FileWriter csvWriter, String col_name) throws IOException {
        csvWriter.append(col_name);
        csvWriter.append(",");
        return;
    }

}

