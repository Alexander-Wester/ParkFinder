
/**
 * **************************************************************************************
 * 
 * Class: Request
 * 
 * Description: Handles requests 
 * 
 * @author Donald, Garion
 * 
 * **************************************************************************************
 */
public class Request 
{
    // List to hold all sent requests (this could ideally reside in the controller, but is here for design simplicity)

    private String description; // Description of the request, e.g., "Open Search Screen"
    private String params = null; // Parameters related to the request (could be comma-separated string)
    private AbstractScreen source; // The screen from which the request is coming
    
    /**
     *  **************************************************************************************
     * 
     * Constructor: Request
     * 
     * Description: Default constructor
     * 
     * @author Donald
     * 
     *  **************************************************************************************
     */
    public Request() 
    {
        // The description and parameters can be set later via setters
    }

    /**
     *  **************************************************************************************
     * 
     * Constructor: Request
     * 
     * Description: constructor with a passed param
     * 
     * @author Donald
     * @param Request other
     * 
     *  **************************************************************************************
     */
    public Request(Request other) 
    {
        this.description = other.description;
        this.params = other.params;
        this.source = other.source; // Assuming a shallow copy is fine
    }

    /**
     * **************************************************************************************
     * 
     * Method: setDescription
     * 
     * Description: Setter for 'description' (the action or intent of the request)
     * 
     * @param String description
     * @author Donald
     * 
     * **************************************************************************************
     */
    public void setDescription(String description) 
    {
        this.description = description;
    }

    /**
     * **************************************************************************************
     * 
     * Method: getDescription
     * 
     * Description: Getter for 'description'
     * 
     * @return description
     * @author Donald
     * 
     * **************************************************************************************
     */
    public String getDescription() 
    {
        return description;
    }

    /**
     * **************************************************************************************
     * 
     * Method: setParams
     * 
     * Description: Setter for 'params' (additional data passed with the request)
     * 
     * @param String params
     * @author Donald
     * 
     * **************************************************************************************
     */
    public void setParams(String params) 
    {
        this.params = params;
    }

    /**
     * **************************************************************************************
     * 
     * Method: getParams
     * 
     * Description: Getter for 'params'
     * 
     * @return params
     * @author Donald
     * 
     * **************************************************************************************
     */
    public String getParams() 
    {
        return params;
    }

    /**
     * **************************************************************************************
     * 
     * Method: getSource
     * 
     * Description: Getter for the 'source' (the screen from which the request originates)
     * 
     * @return source
     * @author Donald
     * 
     * **************************************************************************************
     */
    public AbstractScreen getSource() 
    {
        return source;
    }

    /**
     * **************************************************************************************
     * 
     * Method: setSource
     * 
     * Description: Setter for 'source'
     * 
     * @param AbstractScreen source
     * @author Donald
     * 
     * **************************************************************************************
     */
    public void setSource(AbstractScreen source) 
    {
        this.source = source;
    }

    /**
     * **************************************************************************************
     * 
     * Method: submit
     * 
     * Description: Method to send the request with only a description
     * 
     * @param String reqString
     * @author Donald
     * 
     * **************************************************************************************
     */
    public void submit(String reqString) 
    {
        // Set the description of the request
        setDescription(reqString);

        // Pass the request to the Controller to handle the action
        Controller.getInstance().handleRequest(this);
    }

    /**
     * **************************************************************************************
     * 
     * Method: submit
     * 
     * Description: Method to send the request with a description and parameters
     * 
     * @param String reqString, String params
     * @author Donald
     * 
     * **************************************************************************************
     */
    public void submit(String reqString, String params) 
    {
        // Set the description and parameters of the request
        setDescription(reqString);
        setParams(params);

        // Pass the request to the Controller to handle the action
        Controller.getInstance().handleRequest(this);
    }

    /**
     * **************************************************************************************
     * 
     * Method: submit
     * 
     * Description: Method to send the request with a description, parameters, and the originating screen
     * 
     * @param String reqString, String params, AbstractScreen screen
     * @author Donald
     * 
     * **************************************************************************************
     */
    public void submit(String reqString, String params, AbstractScreen screen) 
    {
        // Set the description, parameters, and screen source of the request
        setDescription(reqString);
        setSource(screen);
        setParams(params);

        // Pass the request and the screen to the Controller to handle the action
        Controller.getInstance().handleRequest(this);
    }

    /**
     * **************************************************************************************
     * 
     * Method: submit
     * 
     * Description: Method to send the request 
     * 
     * @param Request request
     * @author Donald
     * 
     * **************************************************************************************
     */
    public void submit(Request request) 
    {
        Controller.getInstance().handleRequest(request);
    }
    
}
