import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

/**
 ***************************************************************************************
 * 
 * Class: SearchInterface
 * 
 * Description: SearchInterface is a GUI for searching parks.
 * 
 * @author Donald, Witold, Garion
 * 
 * **************************************************************************************
 */
public class SearchScreen extends AbstractScreen 
{
	private JTextField searchField;
    private JTextField addressField; 
    private JTextField myAddressField; 
    private JComboBox<String> accessibleComboBox;
    private JComboBox<String> sizeComboBox;
    private JComboBox<String> playgroundComboBox; 
    private JButton searchButton;
    private JTextArea detailsArea;
    private ParkDataSearcher parksDataSearcher;
    private JButton nextButton;
    private JButton previousButton;
    private JButton viewAllButton;
    private RequestStack requestStack = new RequestStack(); ; // Each screen has its own RequestStack

    /**
     * **************************************************************************************
     * 
     * Method: SearchInterface
     * 
     * Creates the search interface for park data.
     * 
     * @author Donald, Garion, Witold
     * 
     * **************************************************************************************
     */
    public SearchScreen() 
    {
        super();
	    
        // Main panel setup
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        // Filter Panel (Top)
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(3, 2, 10, 10)); // Grid layout for three rows, two columns

        searchField = new JTextField(10); // Park name text field
        addressField = new JTextField(10); // Address text field
        myAddressField = new JTextField(10); // Address text field

        String[] accessibilityOptions = {"N/A", "Yes", "No"}; // Dropdown for accessibility
        accessibleComboBox = new JComboBox<>(accessibilityOptions);
        String[] sizeOptions = {"N/A", "Small", "Medium", "Large"}; // Dropdown for park size
        sizeComboBox = new JComboBox<>(sizeOptions);
        String[] playgroundOptions = {"N/A", "Yes", "No"};
        playgroundComboBox = new JComboBox<>(playgroundOptions);

        // Add Park Name and Address fields to the filter panel in one row
        filterPanel.add(new JLabel("Park Name:"));
        filterPanel.add(searchField);
        filterPanel.add(new JLabel("Address:"));
        filterPanel.add(addressField);

        // Add dropdowns for Playground, Accessibility, and Size
        filterPanel.add(new JLabel("Playground:"));
        filterPanel.add(playgroundComboBox);
        filterPanel.add(new JLabel("Accessible:"));
        filterPanel.add(accessibleComboBox);
        filterPanel.add(new JLabel("Size:"));
        filterPanel.add(sizeComboBox);
        
        filterPanel.add(new JLabel("My Address:"));
        filterPanel.add(myAddressField);

        // Details Area (Center)
        detailsArea = new JTextArea(10, 30);
        detailsArea.setEditable(false);
        detailsArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scrollPane = new JScrollPane(detailsArea);

        // Buttons Panel (Bottom)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        previousButton = new JButton("Previous");
        searchButton = new JButton("Search");
        nextButton = new JButton("Next");
        viewAllButton = new JButton("View All");
        
        // Add actions to search button taking in all filter values
        searchButton.addActionListener(new ActionListener() 
        {
            /**
             * **************************************************************************************
             * 
             * Method: actionPerformed
             * 
             * Description: Overridden method for action performed when search button is clicked
             * 
             * @author Garion, Donald
             * 
             * **************************************************************************************
             */
        	@Override
            public void actionPerformed(ActionEvent e) 
            {
                // Gather user input values
                String nameFilter = searchField.getText().trim();         // Park Name
                String addressFilter = addressField.getText().trim();     // Address
                String accessibleFilter = accessibleComboBox.getSelectedItem().toString(); // Accessible (YES/NO/N/A)
                String playgroundFilter = playgroundComboBox.getSelectedItem().toString(); // Playground (YES/NO/N/A)
                String sizeFilter = sizeComboBox.getSelectedItem().toString();             // Size filter
                String myAddressFilter = myAddressField.getText().trim();     // Address
                
                // Perform search using the filters
                if(myAddressFilter.equals("")){
                    //System.out.println("here");
                    myAddressFilter=null;
                }
                
                request.submit("Search Parks",nameFilter + "," + addressFilter + "," + accessibleFilter + "," + playgroundFilter + "," + sizeFilter + "," + myAddressFilter + ",", SearchScreen.this);
                requestStack.pushRequest(request);
            }
        });
        
        // Add action listeners to the "Next" and "Previous" buttons
        nextButton.addActionListener(new ActionListener() 
        {
        	 /**
             * **************************************************************************************
             * 
             * Method: actionPerformed
             * 
             * Description: Overridden method for action performed when next button is clicked
             * 
             * @author Garion, Donald
             * 
             * **************************************************************************************
             */
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try {
                    if (requestStack.hasRequests()) {
                        Request nextRequest = requestStack.observeNextRequest(); // Get the previous request
                        request.submit(nextRequest); // Send it to the controller
                    }
                    } catch (IllegalStateException ex) {
                         System.out.println("No previous requests to process.");
                    }           
        }
        });
        
        previousButton.addActionListener(new ActionListener() 
        {
        	 /**
             * **************************************************************************************
             * 
             * Method: actionPerformed
             * 
             * Description: Overridden method for action performed when prev button is clicked
             * 
             * @author Garion, Donald
             * 
             * **************************************************************************************
             */
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                try {
                    if (requestStack.hasRequests()) {
                        Request previousRequest = requestStack.observePreviousRequest(); // Get the previous request
                        request.submit(previousRequest); // Send it to the controller
                    }
                    } catch (IllegalStateException ex) {
                         System.out.println("No previous requests to process.");
                    }
            }
        });

        // Add button actions (View All example)
        viewAllButton.addActionListener(new ActionListener() 
        {
        	 /**
             * **************************************************************************************
             * 
             * Method: actionPerformed
             * 
             * Description: Overridden method for action performed when view all button is clicked
             * 
             * @author Garion, Donald
             * 
             * **************************************************************************************
             */
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String nameFilter = "";         // Park Name
                String addressFilter = "";     // Address
                String accessibleFilter = "N/A"; // Accessible (YES/NO/N/A)
                String playgroundFilter ="N/A"; // Playground (YES/NO/N/A)
                String sizeFilter = "N/A";             // Size filter

                request.submit("Search Parks",nameFilter + "," + addressFilter + "," + accessibleFilter + "," + playgroundFilter + "," + sizeFilter + "," + null + ",", SearchScreen.this);
                requestStack.pushRequest(request);

            }
        });

        // Add components to button panel
        buttonPanel.add(viewAllButton);
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(searchButton); // Search button at the bottom

        // Add components to the main panel
        mainPanel.add(filterPanel, BorderLayout.NORTH); // Filter panel at the top
        mainPanel.add(scrollPane, BorderLayout.CENTER); // Details in the center
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Buttons at the bottom

        // Final window setup
        add(mainPanel);
    }

    /**
     * **************************************************************************************
     * 
     * Method: displaySearchResults
     * 
     * Description: Displays the park details based on users search filters
     * 
     * @author Garion, Donald
     * 
     * **************************************************************************************
     */
    public void displaySearchResults(List<List<String>> results) 
    {
        // Clear previous results
        detailsArea.setText("");

        if (results.isEmpty()) 
        {
            detailsArea.setText("No parks found matching the criteria.");
        } 
        else 
        {
            StringBuilder parkDetails = new StringBuilder("Matching Parks:\n\n");
            for (List<String> row : results) {
                parkDetails.append("Name: ").append(row.get(0)).append("\n");
                parkDetails.append("Address: ").append(row.get(1)).append("\n");
                parkDetails.append("Coordinates: ").append(row.get(2)).append(", ").append(row.get(3)).append("\n");

                // Accessible Information
                String accessibleInfo = row.get(4).isEmpty() ? "Information Unavailable" : row.get(4);
                parkDetails.append("Accessible: ").append(accessibleInfo).append("\n");

                // Size Information
                String sizeInfo = row.get(5).isEmpty() ? "Information Unavailable" : row.get(5);
                parkDetails.append("Size: ").append(sizeInfo).append("\n");

                // Playground Information
                boolean hasPlayground = !row.get(4).isEmpty() && !row.get(5).isEmpty();
                parkDetails.append("Playground: ").append(hasPlayground ? "Yes" : "No").append("\n");

                //Distance Information
                String distanceInfo;  
                
                if(row.get(6).isEmpty() || !Character.isDigit(row.get(6).charAt(0))){
                    distanceInfo= "Information Unavailable"; 
                }
                else{
                distanceInfo = row.get(6);
                double tempDouble = Double.parseDouble(distanceInfo);
                DecimalFormat df = new DecimalFormat("#.##");      
                tempDouble = Double.valueOf(df.format(tempDouble));
                distanceInfo = Double.toString(tempDouble);
                distanceInfo = distanceInfo + " km away";
                }
                parkDetails.append("Distance: ").append(distanceInfo).append("\n\n");

                
            }
            detailsArea.setText(parkDetails.toString());
        }
    }
}
