import javax.swing.*;
import java.awt.*;

/**
 * ***************************************************************************************
 *
 * Class: SignUpScreen
 *
 * Description: Interface screen for creating a new account
 *              
 *  @author Asif
 *
 * ***************************************************************************************
 */
public class SignUpScreen extends AbstractScreen 
{
    private JTextField usernameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JPanel panel;

    /**
     * **************************************************************************************
     * 
     * Method: SignUpScreen
     * 
     * Description: Initializes the window 
     * 
     * @author Asif
     * 
     * **************************************************************************************
     */
    public SignUpScreen() 
    {
        super(800, 600); // Custom dimensions
        initializeComponents();
    }

    /**
     * **************************************************************************************
     * 
     * Method: initializeComponents
     * 
     * Description: Initializes the window components
     * 
     * @author Asif
     * 
     * **************************************************************************************
     */
    private void initializeComponents() 
    {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title Label
        JLabel titleLabel = new JLabel("Create a New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Confirm Password Label and Field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(confirmPasswordField, gbc);

        // Register Button
        JButton registerButton = new JButton("Register");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(registerButton, gbc);

        // Register Action
        registerButton.addActionListener(e -> 
        {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (password.equals(confirmPassword)) 
            {
                if (UserManager.addUser(username, password)) 
                {
                    JOptionPane.showMessageDialog(SignUpScreen.this, "Account created successfully!");
                    new LoginScreen(); // Redirect to login screen
                    dispose(); // Close sign-up screen
                } 
                else 
                {
                    JOptionPane.showMessageDialog(SignUpScreen.this, "Username already exists. Try another.");
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(SignUpScreen.this, "Passwords do not match!");
            }
        });
    }
}
