import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * **************************************************************************************
 * 
 * Class: Controller
 * 
 * Description: Handles requests made and is a singleton instance
 * 
 * @author Donald, Garion
 * 
 * **************************************************************************************
 */
public class Controller 
{

// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    private String parksFilePath = "New_Parks.csv"; // Default file path for parks data
    // // CUSTOM FILE PATH FOR MAC PUT HERE
    //private String parksFilePath = "/New_Parks.csv";

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
    private static Controller instance; // Singleton instance of the Controller
    private Request currentRequest; // Holds the current request being processed
    private final Map<String, Runnable> actions = new HashMap<>(); // A map that holds actions with their associated string descriptions
    
    /**
     *  **************************************************************************************
     * 
     * Constructor: Controller
     * 
     * Description: Private constructor for the Controller to enforce the Singleton pattern
     * 
     * @author Donald
     * 
     *  **************************************************************************************
     */
    private Controller() 
    {
        // Populating the actions map with possible requests and their associated actions
        actions.put("Search Parks", this::searchParks); // Action to search for parks, defined in searchParks method
        actions.put(null, null); // Action to show help (just prints a message)
        actions.put("Exit", () -> System.out.println("Application Closed")); // Action to exit the application (prints a message)
        actions.put("Handle Login", this::handleAllLogin); // Action to open the Search Screen
        actions.put("Create Search Screen", () -> ScreenFactory.createSearchScreen());
        actions.put("Create SignUp Screen", () -> ScreenFactory.createSignUpScreen());
        actions.put("Create Login Screen", () -> ScreenFactory.createLoginScreen());
    }

    /**
     * **************************************************************************************
     * 
     * Method: setCurrentRequest
     * 
     * Description: Setter for the current request, allowing it to be updated when a request is handled
     * 
     * @param Request request
     * @author Donald
     * 
     * **************************************************************************************
     */
    public void setCurrentRequest(Request request) 
    {
        this.currentRequest = request;
    }

    /**
     * **************************************************************************************
     * 
     * Method: getCurrentRequest
     * 
     * Description: Getter for retrieving the current request made
     * 
     * @return currentRequest
     * @author Donald
     * 
     * **************************************************************************************
     */
    public Request getCurrentRequest() 
    {
    	return this.currentRequest;
    }

    /**
     * **************************************************************************************
     * 
     * Method: getInstance
     * 
     * Description: Singleton instance getter
     * 
     * @return instance
     * @author Donald
     * 
     * **************************************************************************************
     */
    public static Controller getInstance() 
    {
        if (instance == null) 
        {
            instance = new Controller(); // If the instance doesn't exist, create a new one
        }
        return instance;
    }

    /**
     * **************************************************************************************
     * 
     * Method: handleRequest
     * 
     * Description: Method to handle a request (either search parks or another action)
     * 
     * @param Request request
     * @author Donald
     * 
     * **************************************************************************************
     */
    public void handleRequest(Request request) 
    {
        setCurrentRequest(request); // Set the current request for handling
        Runnable action = actions.get(request.getDescription()); // Get the action associated with the request description
        if (action != null) 
        {
            action.run(); // Execute the associated action
        } 
        else 
        {
            System.out.println("Unknown request: " + request.getDescription()); // Print a message if the request is unknown
        }
    }

    /**
     * **************************************************************************************
     * 
     * Method: searchParks
     * 
     * Description: Method to search for parks with filters (name, address, accessible, playground, size)
     * 
     * @author Donald
     * 
     * **************************************************************************************
     */
    private void searchParks() 
    {
        // Extract parameters from the current request, which are expected to be comma-separated
        String[] filters = currentRequest.getParams().split(",");  // Split the parameters based on commas

        System.out.println("Number of filters: " +filters.length);

        // Ensure that there are exactly 6 filters
       if (filters.length >= 6) 
       {
            // Extract individual parameters and remove extra spaces using trim()
            String name = filters[0].trim(); // Name of the park
            String address = filters[1].trim(); // Address of the park
            String accessible = filters[2].trim(); // Accessibility of the park
            String playground = filters[3].trim(); // Whether the park has a playground
            String size = filters[4].trim(); // Size of the park
            String myAddress = filters[5].trim(); // Address of the user

            try 
            {
                // Attempt to search parks using the configured file path
                ParkDataSearcher pds = new ParkDataSearcher(parksFilePath); // Initialize the ParkDataSearcher with the file path

                // Call the searchParks method from ParkDataSearcher, passing in the filters
                System.out.println("myAddress in Controller: " + myAddress);
                List<List<String>> result = pds.searchParks(name, address, accessible, playground, size, myAddress);

                // Get the source screen from the request
                AbstractScreen sourceScreen = currentRequest.getSource();

                // Ensure that the source screen is an instance of SearchScreen
                if (sourceScreen instanceof SearchScreen) {
                    SearchScreen searchScreen = (SearchScreen) sourceScreen; // Cast the screen to SearchScreen
                    searchScreen.displaySearchResults(result); // Display the results on the SearchScreen
                } 
                else 
                {
                    System.out.println("Invalid source screen for displaying search results.");
                }
            } 
            catch (Exception e) 
            {
                // Handle any exceptions that occur during the park search
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        } 
        else 
        {
            // If there are not exactly 5 parameters, print an error message
            System.out.println("Error: Invalid number of parameters for searching parks.");
        }
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: handleAllLogin
     * 
     * Description: Method to handle what happens after the user logs in
     * 
     * @author Donald, Garion
     * 
     * **************************************************************************************
     */
    private void handleAllLogin() 
    {
        // Get the source screen from the current request
        AbstractScreen sourceScreen = currentRequest.getSource();
    
        // Ensure that the source screen is an instance of LoginScreen
        if (sourceScreen instanceof LoginScreen) 
        {
            LoginScreen loginScreen = (LoginScreen) sourceScreen; // Cast the screen to LoginScreen
            loginScreen.handleLogin(); // Call the handleLogin() method on the LoginScreen
        } 
        else 
        {
            System.out.println("Invalid source screen for handling login.");
        }
    }

    /**
     * **************************************************************************************
     * 
     * Method: handleRequest
     * 
     * Description: Method to set a new file path for parks data (if you need to dynamically change the path)
     * 
     * @param String path
     * @author Donald
     * 
     * **************************************************************************************
     */
    public void setParksFilePath(String path) 
    {
        this.parksFilePath = path;
    }
}
