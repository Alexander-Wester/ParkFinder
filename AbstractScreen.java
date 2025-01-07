import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * ***************************************************************************************
 *
 * Class: AbstractScreen
 *
 * Description: An abstract class that contains a common panel setup, but leaves window
 *              properties (title, size, close operation, location) to be set in the
 *              subclasses.
 *              
 *  @author Donald, Garion
 *
 * ***************************************************************************************
 */
public abstract class AbstractScreen extends JFrame 
{
    protected String title = "UTrails"; // Title of the window
    private int width; // Width of the window
    private int height; // Height of the window
    protected Request request; // Instance-level request object

    // Static controller shared across all screens
    protected static Controller controller;
    
    /**
     *  **************************************************************************************
     * 
     * Constructor: AbstractScreen
     * 
     * Description: Constructor with default width and height
     * 
     * @author Donald
     * 
     *  **************************************************************************************
     */
    public AbstractScreen() 
    {
        this.width = 800;  // Default width
        this.height = 600; // Default height
        initializeWindow(); // Now we call initializeWindow here directly
    }

    /**
     *  **************************************************************************************
     * 
     * Constructor: AbstractScreen
     * 
     * Description: Constructor with custom width and height
     * 
     * @author Donald
     * 
     *  **************************************************************************************
     */
    public AbstractScreen(int width, int height) 
    {
        this.width = width;
        this.height = height;
        initializeWindow(); // This will initialize the window and set the logo
    }

    /**
     * **************************************************************************************
     * 
     * Method: initializeWindow
     * 
     * Description: Initializes the window with common properties
     * 
     * @author Donald
     * 
     * **************************************************************************************
     */
    private void initializeWindow() 
    {
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setWindowIcon("/utrails-logo.png");
        setVisible(true); // Show window
        this.request = new Request();
    }

    /**
     * **************************************************************************************
     * 
     * Method: initializeWindow
     * 
     * Description: Sets the window icon
     * 
     * @author Donald
     * @param String resourcePath
     * 
     * **************************************************************************************
     */
    private void setWindowIcon(String resourcePath) 
    {
        try 
        {
            BufferedImage originalImage = ImageIO.read(getClass().getResource(resourcePath));
            Image scaledImage = originalImage.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            setIconImage(scaledImage);
        } 
        catch (IOException | NullPointerException e) 
        {
            System.err.println("Error loading icon: " + e.getMessage());
        }
    }
}
