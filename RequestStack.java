import java.util.ArrayList;

/**
 * **************************************************************************************
 * 
 * Class: RequestStack
 * 
 * Description: Handles requests by placing them in a stack (specifically an array list)
 * 
 * @author Donald, Garion
 * 
 * **************************************************************************************
 */
public class RequestStack 
{
    private ArrayList<Request> requestStack = new ArrayList<>(); // Internal list to hold requests
    private int currentPosition = -1  ; // Tracks the current position in the stack (like a pointer)

    /**
     * **************************************************************************************
     * 
     * Method: pushRequest
     * 
     * Description: Push a request onto the stack
     * 
     * @param Request request
     * @author Donald
     * 
     * **************************************************************************************
     */
    public void pushRequest(Request request) 
    {
        System.out.println(request.getDescription() + ": " + request.getParams());
        requestStack.add( new Request(request)); // Add the request to the end of the list
        currentPosition++; // Move the pointer to the newly added request
    }

    /**
     * **************************************************************************************
     * 
     * Method: observeNextRequest
     * 
     * Description: Go to the next request made recorded in the array list
     * 
     * @return currentRequest - or null if no next request
     * @author Donald
     * 
     * **************************************************************************************
     */
    public Request observeNextRequest() 
    {
        if (currentPosition >= requestStack.size() - 1 || requestStack.isEmpty()) 
        {
            return null; // No more requests in the forward direction
        }
    
        currentPosition = Math.min(currentPosition + 1, requestStack.size() - 1); // Move forward
        return requestStack.get(currentPosition); // Return the request at the new position
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: observePreviousRequest
     * 
     * Description: Go to the prev request made recorded in the array list
     * 
     * @return currentRequest - or null if no prev request
     * @author Donald
     * 
     * **************************************************************************************
     */
    public Request observePreviousRequest() 
    {
        if (requestStack.isEmpty()) 
        {
            return null; // Indicate that the stack is empty
        }
        
        Request currentRequest = requestStack.get(currentPosition);
        currentPosition = Math.max(currentPosition - 1, -1);
    
        return currentRequest;
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: popRequest
     * 
     * Description: Remove/return the top request
     * 
     * @return currentRequest - or null if no prev request
     * @author Donald
     * 
     * **************************************************************************************
     */
    public Request popRequest() 
    {
        if (requestStack.isEmpty()) 
        {
            return null; // Indicate that the stack is empty
        }
    
        return requestStack.remove(requestStack.size() - 1); // Remove and return the top element
    
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: hasRequests
     * 
     * Description: Checks if no requests are made or not
     * 
     * @return boolean value
     * @author Donald
     * 
     * **************************************************************************************
     */
    public boolean hasRequests() 
    {
        return !requestStack.isEmpty();
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: printHistory
     * 
     * Description: For testing purposes: Print all requests in the stack (optional)
     * 
     * @author Donald
     * 
     * **************************************************************************************
     */
    public void printHistory() 
    {
        if (requestStack.isEmpty()) 
        {
            System.out.println("No requests in history.");
        } 
        else 
        {
            for (Request request : requestStack) 
            {
                System.out.println(request.getDescription() + ": " + request.getParams());
            }
        }
    }
}
