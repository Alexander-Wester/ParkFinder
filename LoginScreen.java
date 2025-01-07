import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* ***************************************************************************************
*
* Class: LoginScreen
*
* Description: Interface for the login page where the user signs into their account
*              
*  @author Asif, Donald, Garion
*
* ***************************************************************************************
*/
public class LoginScreen extends AbstractScreen 
{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel panel;
    protected RequestStack requestStack; // Each screen has its own RequestStack

    /**
     *  **************************************************************************************
     * 
     * Constructor: LoginScreen
     * 
     * Description: initialize log in screen with super
     * 
     * @author Asif
     * 
     *  **************************************************************************************
     */
    public LoginScreen() 
    {
        super();
        initializeUI();
        setVisible(true); // Make the frame visible after adding components
    }

    /**
     * **************************************************************************************
     * 
     * Method: initializeUI
     * 
     * Description: Initialize and set up the UI components.
     * 
     * @author Asif
     * 
     * **************************************************************************************
     */
    private void initializeUI() 
    {
        panel = createBackgroundPanel();
        panel.setLayout(new GridBagLayout());
        add(panel);

        GridBagConstraints gbc = createGridBagConstraints();

        addTitleLabel(panel, gbc);
        addUsernameField(panel, gbc);
        addPasswordField(panel, gbc);
        addLoginButton(panel, gbc);
        addSignUpButton(panel, gbc);
    }

    /**
     * **************************************************************************************
     * 
     * Method: initializeUI
     * 
     * Description: Create a panel with a custom background image.
     * 
     * @author Asif
     * @return JPanel with a painted background.
     * 
     * **************************************************************************************
     */
    private JPanel createBackgroundPanel() 
    {
        return new JPanel() 
        {
            /**
             * **************************************************************************************
             * 
             * Method: paintComponent
             * 
             * Description: Overriden paintComponent with specific graphics
             * 
             * @author Asif
             * @return JPanel with a painted background.
             * 
             * **************************************************************************************
             */
            @Override
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                drawBackgroundImage(g);
            }
        };
    }

    /**
     * **************************************************************************************
     * 
     * Method: drawBackgroundImage
     * 
     * Description: g Graphics object to draw on.
     * 
     * @author Asif
     * @param g Graphics object to draw on.
     * 
     * **************************************************************************************
     */
    private void drawBackgroundImage(Graphics g) 
    {
        try 
        {
            BufferedImage backgroundImage = ImageIO.read(getClass().getResource("/background-image.png"));
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } 
        catch (IOException e) 
        {
            e.printStackTrace(); // Log error if the background image fails to load
        }
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: createGridBagConstraints
     * 
     * Description: Create and configure GridBagConstraints.
     * 
     * @author Asif
     * @return Configured GridBagConstraints object.
     * 
     * **************************************************************************************
     */
    private GridBagConstraints createGridBagConstraints() 
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        return gbc;
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: addTitleLabel
     * 
     * Description: Add the title label to the panel.
     * 
     * @author Asif
     * @param JPanel panel, GridBagConstraints gbc
     * 
     * **************************************************************************************
     */
    private void addTitleLabel(JPanel panel, GridBagConstraints gbc) 
    {
        JLabel titleLabel = new JLabel("Welcome To UTrails");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
    }

    /**
     * **************************************************************************************
     * 
     * Method: addUsernameField
     * 
     * Description: Add the username label and text field to the panel.
     * 
     * @author Asif
     * @param JPanel panel, GridBagConstraints gbc
     * 
     * **************************************************************************************
     */
    private void addUsernameField(JPanel panel, GridBagConstraints gbc) {
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);
    }

    /**
     * **************************************************************************************
     * 
     * Method: addPasswordField
     * 
     * Description: Add the password label and password field to the panel.
     * 
     * @author Asif
     * @param JPanel panel, GridBagConstraints gbc
     * 
     * **************************************************************************************
     */
    private void addPasswordField(JPanel panel, GridBagConstraints gbc) 
    {
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);
    }

    /**
     * **************************************************************************************
     * 
     * Method: addLoginButton
     * 
     * Description: Add the login button and its action listener to the panel.
     * 
     * @author Asif
     * @param JPanel panel, GridBagConstraints gbc
     * 
     * **************************************************************************************
     */
    private void addLoginButton(JPanel panel, GridBagConstraints gbc) {
        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {                
                // Perform search using the filters
                request.submit("Handle Login",null,LoginScreen.this);
                requestStack.pushRequest(request);
                
            }
        });
    }

    /**
     * **************************************************************************************
     * 
     * Method: addSignUpButton
     * 
     * Description: Add the sign-up button and its action listener to the panel.
     * 
     * @author Asif
     * @param JPanel panel, GridBagConstraints gbc
     * 
     * **************************************************************************************
     */
    private void addSignUpButton(JPanel panel, GridBagConstraints gbc) 
    {
        JButton signUpButton = new JButton("Sign Up");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(signUpButton, gbc);
    
        // Create the ActionListener for the SignUp button
        signUpButton.addActionListener(new ActionListener() 
        {
        	/**
             * **************************************************************************************
             * 
             * Method: addSignUpButton
             * 
             * Description: Overridden method to handle the sign up button action
             * 
             * @author Asif
             * @param ActionEvent e
             * 
             * **************************************************************************************
             */
            @Override
            public void actionPerformed(ActionEvent e) 
            {                
                // Perform search using the filters
                request.submit("Create SignUp Screen");
                requestStack.pushRequest(request);
            }
        });
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: handleLogin
     * 
     * Description: Handle the login button action.
     * 
     * @author Asif
     * @param ActionEvent e
     * 
     * **************************************************************************************
     */
    public void handleLogin() 
    {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (UserManager.authenticate(username, password)) 
        {
            request.submit("Create Search Screen");
            dispose();
            // Proceed to the next screen
        } 
        else 
        {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Try again.");
        }
    }
}
