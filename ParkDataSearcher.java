import java.util.ArrayList;
import java.util.List;

/**
 * **************************************************************************************
 * 
 * Class: ParkDataSearcher
 * 
 * Description: ParkDataSearcher searches for park data in a CSV file.
 * 
 * @author Wiltold, Donald, Garion
 * 
 * **************************************************************************************
 */
public class ParkDataSearcher 
{
    private CSVReader csvReader; // private variable csvReader to read data from the CSV file

    /**
     * **************************************************************************************
     * 
     * Method: ParkDataSearcher
     * 
     * Description: Creates a ParkDataSearcher with the specified file path.
     * 
     * @param path Path to the CSV file
     * @author Wiltold, Donald, Garion
     * 
     * **************************************************************************************
     */
    public ParkDataSearcher(String path) 
    {
        csvReader = new CSVReader(path); // sets the csvReader with the provided file path (creating an instance of CSVReader to read the CSV file)
    }
    
	/**
	 * **************************************************************************************
	 * 
	 * Method: getAllData
	 * 
	 * Description: Gets all parks names and addresses
	 * 
	 * @return List with park details, or empty if not found
	 * @author Marc
	 * 
	 * **************************************************************************************
	 */
    public List<List<String>> getAllData() 
    {
        return csvReader.readCSV(); // read and return the data
    }
    
	/**
	 * **************************************************************************************
	 * 
	 * Method: searchParks
	 * 
	 * Description: Gets all parks details that match search conditions
	 * 
	 * @param takes the name, address, accessible, playground, and size keys/filters the user entered when searching
	 * @return List with park details, or empty if not found
	 * @author Garion, Marc, Donald
	 * 
	 * **************************************************************************************
	 */
    public List<List<String>> searchParks(String name, String address, String accessible, String playground, String size, String myAddress) 
    {
        List<List<String>> data = csvReader.readCSV(); // Read CSV data
        List<List<String>> results = new ArrayList<>();
        System.out.println("myAddress in ParkDataSearcher: " + myAddress);

        for (List<String> row : data) 
        {
            // Ensure the row has enough columns to avoid IndexOutOfBoundsException
            if (row.size() < 7) 
            {
                // Fill missing columns with empty strings for consistency
                while (row.size() < 7) 
                {
                    row.add("");
                }
            }

            boolean matches = true;

            // Indices based on CSV file:
            // 0: PARK_NAME, 1: ADDRESS, 2: XCoord, 3: YCoord, 4: ACCESSIBLE, 5: SIZE_

            // Park Name Filter
            if (name != null && !name.trim().isEmpty() && !row.get(0).toLowerCase().contains(name.toLowerCase())) 
            {
                matches = false;
            }

            // Address Filter
            if (address != null && !address.trim().isEmpty() && !row.get(1).toLowerCase().contains(address.toLowerCase())) 
            {
                matches = false;
            }

            // Accessible Filter
            if (!accessible.equals("N/A") && !row.get(4).equalsIgnoreCase(accessible)) 
            {
                matches = false;
            }

            // Playground Filter 
            String sizeValue = row.get(5).trim(); // Check SIZE column (after ensuring row size >= 6)
            boolean hasPlayground = sizeValue.isEmpty(); // True if size column is empty (indicating a playground)

            if (playground.equalsIgnoreCase("YES")) 
            {
                if (hasPlayground) // If "YES", size should be empty (no size means playground)
                {
                    matches = false;
                }
            }
            else if (playground.equalsIgnoreCase("NO"))
            {
               if (!hasPlayground) // If "NO", size must not be empty (indicating no playground)
                {
                    matches = false;
                }
            }    

            // Size Filter
            if (!size.equals("N/A")) 
            {
                if (sizeValue.isEmpty() || !sizeValue.equalsIgnoreCase(size)) 
                {
                    matches = false; // Size must match if specified
                }
            }

            if(row.get(0).equals("PARK NAME")){
                matches = false;
            }

            // Add row to results if all conditions match
            if (matches) 
            {
                results.add(row);
            }
        }
        
        results = distanceFinder.findDistances(results, myAddress);

        return results;
    }
}
