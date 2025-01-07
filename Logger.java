import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * **************************************************************************************
 * 
 * Class: Logger
 * 
 * Description: Generates a text file all log in usernames and passwords
 * 
 * @author Asif
 * 
 * **************************************************************************************
 */
public class Logger 
{
    private static final String LOG_FILE = "login_log.txt";

    /**
     *  **************************************************************************************
     * 
     * Method: logLogin
     * 
     * Description: logs a specific log in instance
     * 
     * @author Asif
     * 
     *  **************************************************************************************
     */
    public static void logLogin(String username) 
    {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) 
        {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("User: " + username + " logged in at " + timestamp + "\n");
        } 
        catch (IOException e) 
        {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}