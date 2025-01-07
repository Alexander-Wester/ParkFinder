import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

	/**
	 * **************************************************************************************
	 * 
	 * Class: distanceFinder
	 * 
	 * Description: 
	 * 
	 * @author Alex
	 * 
	 * **************************************************************************************
	 */
	public class distanceFinder 
	{
	    private static final String API_KEY = "AIzaSyBfO6dAHwIlPD8R0vzs7U1TbkGqWN2D8is";
	
	    public static void main(String[] args){
	        CSVReader csvReader = new CSVReader("Parks.csv");
	        List<List<String>> data = csvReader.readCSV(); // Read CSV data
	
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
	    }
	    findDistances(data, "401 Sunset Ave");
	}
	    
    /**
     * **************************************************************************************
     * 
     * Method: findDistances
     * 
     * Description: main method to retrieve a list of parks sorted by distance to the given address, ascending order. 
     * 				Just call this method to get the info. all of other methods are private helper methods for this one. 
     * 
     * @author Alex
     * @param List<List<String>> parks, String givenAddress
     * 
     * **************************************************************************************
     */
    public static List<List<String>> findDistances(List<List<String>> parks, String givenAddress) 
    {
    	
    	System.out.println("given address in distanceFinder: " + givenAddress);
        
        if(givenAddress.equals("null"))
        {
            //System.out.println("here1");
            return parks;
        }

        try 
        {
            double[] givenCoords = fetchCoordinates(givenAddress);
            List<List<String>> results = new ArrayList<>();

            if (givenCoords == null) 
            {
                System.out.println("Failed to fetch coordinates for the given address.");
                return null;
            }

            for (List<String> park : parks) 
            {
                try
                {
                    double dist = calculateDistance(givenCoords[0], givenCoords[1],Double.valueOf(park.get(2)),Double.valueOf(park.get(3)));
                    
                    if (dist<30){
                    park.set(6,dist + "");
                    results.add(park);
                    }
                    
                    //System.out.println(park.get(0)+ ", Distance is: " +park.get(6));
                }
                catch(Exception e)
                {
                    //System.out.println(park.get(0) + " had an issue with distance calc");
                    continue;
                }
            }

            results.sort((a, b) -> 
            {
                double valA = Double.parseDouble(a.get(6));
                double valB = Double.parseDouble(b.get(6));
                return Double.compare(valA, valB);
            });

            return results;

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: fetchCoordinates
     * 
     * Description: Google API prompt to get all info realted to the given address
     * 
     * @author Alex
     * @param String address
     * 
     * **************************************************************************************
     */
    private static double[] fetchCoordinates(String address) 
    {
        try 
        {
            String urlString = "https://maps.googleapis.com/maps/api/geocode/json?address="
                    + URLEncoder.encode(address, "UTF-8") + "&key=" + API_KEY;

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseContent = new StringBuilder();
            String responseLine;
            while ((responseLine = in.readLine()) != null) 
            {
                responseContent.append(responseLine).append("\n");
            }
            in.close();

            return parseCoordinates(responseContent.toString());
        } 
        catch (Exception e) 
        {
            System.out.println("Error fetching coordinates for address: " + address);
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: parseCoordinates
     * 
     * Description: This method parses all of the information from fetchCoordinates and gets only the needed coordinates.
     * 
     * @author Alex
     * @param String jsonResponse
     * 
     * **************************************************************************************
     */
    private static double[] parseCoordinates(String jsonResponse) 
    {
        try 
        {
            JSONObject jsonObj = new JSONObject(jsonResponse);
            JSONArray results = jsonObj.getJSONArray("results");

            if (results.length() > 0) 
            {
                JSONObject result = results.getJSONObject(0);
                JSONObject location = result.getJSONObject("geometry").getJSONObject("location");
                double lat = location.getDouble("lat");
                double lng = location.getDouble("lng");
                return new double[]{lat, lng};
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Error parsing JSON response");
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: calculateDistance
     * 
     * Description: Calculate distance in kilometers from given address to eac park
     * 
     * @author Alex
     * @param double lat1, double lon1, double lat2, double lon2
     * 
     * **************************************************************************************
     */
    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) 
    {
        // Radius of Earth in kilometers
        final double R = 6371.0;

        // Convert degrees to radians
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // Haversine formula
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance in kilometers
        return R * c; 
    }

   
}

