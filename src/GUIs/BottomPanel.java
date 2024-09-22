package GUIs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.ArrayList;
import javax.swing.border.Border;
import java.awt.Event;

import classes.Table;
import classes.MenuItems;
import classes.OpenTab;

public class BottomPanel extends JPanel{
    
    private TablePanel tablePanel;
    private ArrayList<JPanel> listOfPanels = new ArrayList<JPanel>();
    private JButton tabButton = new JButton();
    private JButton transactionButton = new JButton();
    private MenuItems menuItems;
    private Table currentTable;

    public BottomPanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder, TablePanel tablePanel, MenuItems menuItems){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new GridLayout(3,1, 0, 0));
        this.menuItems = menuItems;

        // set button graphics
        // TAB
        tabButton.setBackground(new Color(0x00507A));
        tabButton.setFont(new Font("Verdana", Font.PLAIN,16));
        tabButton.setForeground(Color.WHITE);
        tabButton.setPreferredSize(new Dimension(200, 50));
        tabButton.setFocusable(false);
        
        tabButton.addActionListener(e -> {
            if (currentTable.getHasTab()){
                System.out.println("The active tab is: " + currentTable.getActiveTab().getTabId());
            }
            else {
                currentTable.setActiveTab(new OpenTab(currentTable, menuItems));
                System.out.println("You have created a new tab with ID: " + currentTable.getActiveTab().getTabId());
                ;
            }
        });

        // TRANSACTIONS
        transactionButton.setBorder(BorderFactory.createLineBorder(new Color(0x00507A), 2));
        transactionButton.setBackground(Color.WHITE);
        transactionButton.setFont(new Font("Verdana", Font.PLAIN,16));
        transactionButton.setForeground(new Color(0x00507A));
        transactionButton.setPreferredSize(new Dimension(200, 50));
        transactionButton.setText("Transactions");
        transactionButton.setFocusable(false);
        transactionButton.addActionListener(e -> {
        System.out.println("Open transactions");
        });

        // get the tablePanel
        this.tablePanel = tablePanel;

        // set border for panel
        if (hasBorder == true){
            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            this.setBorder(border);
        }
    }

    public JPanel createInformationPanel(){
        /** Creates a panel to store labels in
         * 
         * return; newPanel to store labels in.
         */
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(2,1,0,5));
        newPanel.setPreferredSize(new Dimension(this.getWidth() - 10, 50));
        newPanel.setBackground(this.getBackground());
        newPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        return newPanel;
    }
    // CREATE BOTTOMPANEL WITH A TABLE SELECTED
    public void createInformationBodyBottom(Table table){
        /**
         * Creates a panel containting two labels. A title and description. Calls the function create buttons
         * 
         * @param Table table for checking logic of which table is selected.
         */

         // checks if the panel list contains any objects. if so, removes the panels from the container
         // clears the list and reset the panel.
        if (listOfPanels.size() > 0) {
            for (JPanel panel : listOfPanels) {
                this.remove(panel);
            }
            listOfPanels.clear();
            this.setVisible(true);
            this.revalidate();
        }

        // Creates newPanel to save title and description labels in
        JPanel newPanel = createInformationPanel();

        // create title
        JLabel title = new JLabel();
        title.setFont(new Font("Verdana", Font.BOLD, 16));
        title.setText("Table " + table.getTableId());

        // create description
        JLabel description = new JLabel();
        if (table.getHasTab()){
            description.setText(Float.toString(table.getActiveTab().getTabTotal()));
        }

        else {
            description.setText("No active tab");
        }
        

        // adds them to the newPanel
        newPanel.add(title);
        newPanel.add(description);

        // adds the panel to list of panels to later draw them out
        listOfPanels.add(newPanel);

        // calls method to create buttons
        createButtonsBottomPanel(table);
    }

    // CREATE BOTTOM PANEL WITHOUT ANY SELECTED TABLES
    public void createInformationBodyBottom(){
        /** Overloaded method when the user hasn't selected any tables */
        
        // checks if the panel list contains any objects. if so, removes the panels from the container
        // clears the list and reset the panel.
        if (listOfPanels.size() > 0) {
            for (JPanel panel : listOfPanels) {
                this.remove(panel);
            }
            listOfPanels.clear();
            this.setVisible(true);
            this.revalidate();
        }

        // Creates newPanel to save title and description labels in
        JPanel newPanel = createInformationPanel();

        // create title
        JLabel title = new JLabel();
        title.setFont(new Font("Verdana", Font.BOLD, 16));
        title.setText("No table selected");

        // create title
        JLabel description = new JLabel();
        description.setText("Choose a table see more options!");

        // Add lables to newPanel
        newPanel.add(title);
        newPanel.add(description);

        // Add panel to listOfPanels to later be drawn out
        listOfPanels.add(newPanel);

        // Calls method to create button
        createButtonsBottomPanel();
    }

    public JPanel createButtonPanel(){
        /** Create the Panel to store buttons in.
         * 
         * return; panel that are used to store buttons in.
        */
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        newPanel.setPreferredSize(new Dimension(this.getWidth() - 10, 50));
        newPanel.setBackground(this.getBackground());
        newPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        return newPanel;
    }
    // CREATE BUTTON WITH TABLE SELECTED
    public void createButtonsBottomPanel(Table table){
        /** Creates newPanel and two buttons. One for the selected table and one for opening transactions.
         * Adds these buttons to newPanel and calls the drawBottomPanel() function.
         * 
         * @param Table table is the selected table the user is looking at.
         */

        // creates a newPanel to add all buttons to
        JPanel newPanel = createButtonPanel();
        
        // updates the currentTable so the ActionListeners are looking at the correct table
        setCurrentTable(table);

        // logic for writing out the button text
        if (currentTable.getHasTab())
            tabButton.setText("Open Tab");

        else
            tabButton.setText("Create Tab");

        // adds the buttons to newPanel
        newPanel.add(tabButton);
        newPanel.add(transactionButton);

        // adds newPanel to listOfPanels
        listOfPanels.add(newPanel);
        
        // calls the method for drawing out the bottompanel
        drawBottomPanel();
    }

    public void createButtonsBottomPanel(){
        /** OVERLOADED Creates newPanel and one button for opening transactions.
         * Adds this button to newPanel and calls the drawBottomPanel() function.
         */

        // creates a newPanel to add all buttons to
        JPanel newPanel = createButtonPanel();

        // adds transaction button to Panel
        newPanel.add(transactionButton);

        // adds the panel to listOfPanels
        listOfPanels.add(newPanel);

        // calls the drawBottomPanel method
        drawBottomPanel();
    }

    public void drawBottomPanel(){
        /**Method to draw out the bottomPanel. This is the result from
         * 1. calling createInformationBodyBottom which adds title and description labels to a panel and adds panel to listOfPanels
         * 2. calling createButtonsBottomPanel which adds buttons to a panel and adds panel to listOfPanels
         * 
         * This method now loops through the panels in listOfPanels and then draws out the components.
         */

        // Loops through the listOfPanels and adds them to the BottomPanel container
        for (JPanel panel : listOfPanels){
            this.add(panel);
        }

        // to ensure that it gets drawn properly
        this.setVisible(true);
        this.revalidate();
    }

    // -------------SETTERS------------------
    public void setCurrentTable(Table currentTable) {
        this.currentTable = currentTable;
    }
}
