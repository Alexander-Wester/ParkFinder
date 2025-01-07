/**
 * **************************************************************************************
 * 
 * Class: ScreenFactory
 * 
 * Description: The screen factory implements a factory coding pattern where all screens are created here
 * 
 * @author Donald, Garion
 * 
 * **************************************************************************************
 */
public class ScreenFactory 
{
    /**
     * **************************************************************************************
     * 
     * Method: createSearchScreen
     * 
     * Description: Factory method for creating SearchScreen
     * 
     * @return SearchScreen
     * @author Donald
     * 
     * **************************************************************************************
     */
    public static AbstractScreen createSearchScreen() 
    {
        return new SearchScreen();
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: createSearchScreen
     * 
     * Description: Factory method for creating SignUpScreen
     * 
     * @return SignUpScreen
     * @author Donald
     * 
     * **************************************************************************************
     */
    public static AbstractScreen createSignUpScreen() 
    {
        return new SignUpScreen();
    }
    
    /**
     * **************************************************************************************
     * 
     * Method: createLoginScreen
     * 
     * Description: Factory method for creating LoginScreen
     * 
     * @return LoginScreen
     * @author Donald
     * 
     * **************************************************************************************
     */
    public static AbstractScreen createLoginScreen() 
    {
        return new LoginScreen();
    }
}