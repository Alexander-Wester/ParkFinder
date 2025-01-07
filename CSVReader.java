import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * **************************************************************************************
 * 
 * Class: CSVReader
 * 
 * Description: CSVReader reads data from a CSV file.
 * 
 * @author Witold, Donald, Garion
 * 
 * **************************************************************************************
 */
public class CSVReader 
{
    private String path; // hold file path of file on computer

    /**
     *  **************************************************************************************
     * 
     * Constructor: CSVReader
     * 
     * Description: Creates a CSVReader with the specified file path.
     * 
     * @param path Path to the CSV file
     * @author Wiltold, Donald, Garion
     * 
     *  **************************************************************************************
     */
    public CSVReader(String path) 
    {
        this.path = path; // initializes the CSVReader object with the path when creating the object (so the class knows which file to read from)
    }

    /**
     * **************************************************************************************
     * 
     * Method: readCSV
     * 
     * Description: Reads the CSV file and returns its data as a list of lists.
     * 
     * @return List of rows with CSV data
     * @author Wiltold, Donald, Garion
     * 
     * **************************************************************************************
     */
    public List<List<String>> readCSV() 
    {
        List<List<String>> data = new ArrayList<>(); // new ArrayList to store the rows of CSV data (each row is a List of Strings)
        try (BufferedReader br = new BufferedReader(new FileReader(path))) // creates a BufferedReader br that reads the file at the path using FileReader
        {
            String line;
            while ((line = br.readLine()) != null) // reads each line of the CSV file one by one using readLine() until end of file
            {
                String[] values = line.split(","); // splits the line into an array of Strings using commas
                List<String> row = new ArrayList<>(); // new ArrayList row to store the individual values
                for (String value : values) // loop through each value in the array 
                {
                    row.add(value.trim()); // adds each value to the row
                }
                data.add(row); // adds completed row to list
            }
        } 
        catch (IOException e) // catches any IOExceptions that occur while reading the file, prints an error message, and prints the stack trace
        {
            System.out.println("Error reading the file.");
            e.printStackTrace();
        }
        return data; // returns the formatted list of all rows from the file
    }
}
