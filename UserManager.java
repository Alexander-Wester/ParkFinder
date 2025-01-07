import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * **************************************************************************************
 * 
 * Class: UserManager
 * 
 * Description: Handles all the users and their accounts
 * 
 * @author Asif
 * 
 * **************************************************************************************
 */
public class UserManager 
{
    private static final Map<String, String> users = new HashMap<>();
    private static final String USERS_FILE = "users.txt";

    static 
    {
        loadUsers(); // Load users from file on startup
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: authenticate
     * 
     * Description: Authenticates user
     * 
     * @param String username, String password
     * @author Asif
     * 
     * **************************************************************************************
     */
    public static boolean authenticate(String username, String password) 
    {
        return users.containsKey(username) && users.get(username).equals(password);
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: addUser
     * 
     * Description: Add a new user
     * 
     * @param String username, String password
     * @author Asif
     * 
     * **************************************************************************************
     */
    public static boolean addUser(String username, String password) 
    {
        if (users.containsKey(username)) 
        {
            return false; // Username already exists
        }
        users.put(username, password);
        saveUsers(); // Save updated user data to file
        return true;
    }

    /**
     * **************************************************************************************
     * 
     * Method: loadUsers
     * 
     * Description: Load users from a file
     * 
     * @author Asif
     * 
     * **************************************************************************************
     */
    private static void loadUsers() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                if (parts.length == 2) 
                {
                    users.put(parts[0], parts[1]);
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: saveUsers
     * 
     * Description: Save users to a file
     * 
     * @author Asif
     * 
     * **************************************************************************************
     */
    private static void saveUsers() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) 
        {
            for (Map.Entry<String, String> entry : users.entrySet()) 
            {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }
}
