package GUIs.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.util.ArrayList;

import javax.swing.border.Border;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import GUIs.buttons.*;
import classes.Table;
import classes.MenuItems;
import classes.OpenTab;

public class BottomPanel extends JPanel{
    
    private ArrayList<JPanel> listOfPanels = new ArrayList<JPanel>();
    private int bottomComponentsHeight = 30;

    private FilledButton tabButton = new FilledButton();
    private BorderButton transactionButton = new BorderButton("Transactions");
    private FilledButton payButton = new FilledButton();
    private BorderButton backButton = new BorderButton("Go Back");

    private FunctionButton increaseButton = new FunctionButton(75,bottomComponentsHeight," + ");
    private FunctionButton decreaseButton = new FunctionButton(75, bottomComponentsHeight, " - ");
    private BorderButton addButton = new BorderButton("Add");

    private MenuItems menuItems;
    private Table currentTable;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime currentTime = LocalDateTime.now();

    public BottomPanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder, MenuItems menuItems){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new GridLayout(3,1, 0, 0));
        this.setMenuItems(menuItems);

        // set border for panel
        if (hasBorder == true){
            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            this.setBorder(border);
        }

        addButton = new BorderButton("Add");
        addButton.setPreferredSize(new Dimension(150,bottomComponentsHeight));
        addButton.setFont(new Font(null, Font.BOLD, 12));
    }

    // --------------------- METHODS ------------------------------
    public JPanel createButtonPanel(){
        /** Create the Panel to store buttons in.
         * 
         * return; panel that are used to store buttons in.
        */
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        newPanel.setPreferredSize(new Dimension(this.getWidth() - 10, 40));
        newPanel.setBackground(this.getBackground());

        return newPanel;
    }

    public JLabel createTitleLabel(){
        /**Creates lable for titles in bottomPanel
         * 
         * return title; JLabel with a set Font
         */

        JLabel title = new JLabel();
        title.setFont(new Font("Verdana", Font.BOLD, 16));
    
        return title;
    }

    public void clearListOfPanels(){
        /**Clears listOfPanels from all existing by removing all components from bottomPanel, clearing the list and revalidating.
         */

        if (listOfPanels.size() > 0) {
            this.removeAll();

            listOfPanels.clear();

            this.revalidate();
        }
    }

    // --------------------- VIEW TABLES ----------------------------

    public JPanel createInformationPanel(){
        /**Creates a panel to store labels in
         * 
         * return; newPanel to store labels in.
         */

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(3,1,0,5));
        newPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        newPanel.setBackground(this.getBackground());

        return newPanel;
    }

    public void createInformationBodyBottom(Table table){
        /**VIEWTABLES
         * Creates a panel containting two labels. A title and description. Chains together with next method createButtonsBottomPanel.
         * 
         * @param Table table for checking logic of which table is selected.
         */
        
        clearListOfPanels();

        // Creates newPanel to save title and description labels in
        JPanel newPanel = createInformationPanel();

        // create title
        JLabel title = createTitleLabel();
        
        // create description
        JLabel description = new JLabel();

        if (table != null) {
            title.setText("Table " + table.getTableId());

            if (table.getActiveTab() != null) description.setText(NumberFormat.getCurrencyInstance().format(table.getActiveTab().getTabTotal()));
            else description.setText("No active tab");
        }
        
        else {
            title.setText("No table choosen");
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

    public void createInformationBodyBottom(Table activeTable, String paymentMethod){
        /**VIEWPAYMENT
         * Creates panels with labels for the current table, payment method and date
         * 
         * @param Table activeTable is used to get tableId and pass along to creating buttons
         * @param String paymentMethod is used to write out the current choice of payment method
        */
        clearListOfPanels();

        currentTime = LocalDateTime.now();
        
        // Creates newPanel to save title and description labels in
        JPanel newPanel = createInformationPanel();

        // create title
        JLabel title = createTitleLabel();
        title.setText("Table " + activeTable.getTableId());

        // create description
        JLabel currentPaymentOption = new JLabel();
        currentPaymentOption.setText("Payment: " + paymentMethod);

        JLabel dateAndTime = new JLabel();
        dateAndTime.setText("Date: " + format.format(currentTime));
        
        // adds them to the newPanel
        newPanel.add(title);
        newPanel.add(currentPaymentOption);
        newPanel.add(dateAndTime);

        // adds the panel to list of panels to later draw them out
        listOfPanels.add(newPanel);

        // calls method to create buttons
        createButtonsBottomPanel(activeTable, activeTable.getActiveTab());
    }
    
    public void createButtonsBottomPanel(Table table){
        /**VIEWTABLES - chosen table
         * Creates newPanel and two buttons. One for the selected table and one for opening transactions.
         * Adds these buttons to newPanel and calls the drawBottomPanel() function.
         * 
         * @param Table table is the selected table the user is looking at.
         */

        // creates a newPanel to add all buttons to
        JPanel newPanel = createButtonPanel();
        
        if (table != null){
            // updates the currentTable so the ActionListeners are looking at the correct table
            setCurrentTable(table);
    
            // logic for writing out the button text
            if (currentTable.getActiveTab() != null) tabButton.setText("Open Tab");
            else tabButton.setText("Create Tab");

            // adds the buttons to newPanel
            newPanel.add(tabButton);
        }
        
        newPanel.add(transactionButton);

        // adds newPanel to listOfPanels
        listOfPanels.add(newPanel);
        
        // calls the method for drawing out the bottompanel
        drawBottomPanel();
    }

    // -------------------- VIEW TAB ----------------------------

    public JPanel createPanelForAddProduct(){
        /**Creates a panel to store labels in
         * 
         * return; newPanel to store labels in.
         */

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 2,10));
        newPanel.setPreferredSize(new Dimension(this.getWidth(), bottomComponentsHeight));
        newPanel.setBackground(this.getBackground());

        return newPanel;
    }

    public JTextField createTextBox(int width){
        /**Creates a JTextField to hold information in the bottomPanel during viewTab.
         * 
         * @param int width to decide the width of the textfield
         *
         * return textField; returns a constructed textField. 
         */
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(width,bottomComponentsHeight));
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        textField.setFont(new Font(null, Font.PLAIN, 12));
        textField.setEditable(false);
    
        return textField;
    }

    public void addToListOfPanels(JPanel newPanel, JTextField textField, JTextField amountField){
        
        /**Adds all panels and buttons to the list of panels.
         * 
         * @param JPanel newPanel component to hold all childcomponents
         * @param JTextField textField to show product description
         * @param JTextField amountField to show the amount of products
         */

        newPanel.add(textField);
        newPanel.add(amountField);
        newPanel.add(decreaseButton);
        newPanel.add(increaseButton);
        newPanel.add(addButton);
    }

    public void createAddProductPanel(Table activeTable, String productName, int amount){
        /**VIEWTAB
         * Creates a panel showing a table id, descriptionfield, amountfield, increase-, decrease- and addbutton.
         * 
         * @param Table activeTable is uset to identify the active table and to pass this object along to next method
         */

        clearListOfPanels();
        
        // create panel for title
        JPanel titlePanel = createPanelForAddProduct();
        
        // create and adds title text to titlePanel
        JLabel titleText = createTitleLabel();
        titleText.setText("Table " + activeTable.getTableId());
        titlePanel.add(titleText);
        
        // create new panel to add textfields
        JPanel newPanel = createPanelForAddProduct();

        // item description field
        JTextField textField = createTextBox(300);
        if (!productName.equals("") && amount != -1) textField.setText(productName);

        // item amount field
        JTextField amountField = createTextBox(50);
        if (!productName.equals("") && amount != -1) amountField.setText(Integer.toString(amount));

        // add items to newPanel
        addToListOfPanels(newPanel, textField, amountField);
        
        // add panels to list of panels
        listOfPanels.add(titlePanel);
        listOfPanels.add(newPanel);

        // create navigationbuttons
        createButtonsBottomPanel(activeTable, activeTable.getActiveTab());
    }

    public void createButtonsBottomPanel(Table activeTable, OpenTab activeTab){
        /**VIEWTAB
         * Creates newPanel and two buttons. One for the selected table and one for opening transactions.
         * Adds these buttons to newPanel and calls the drawBottomPanel() function.
         * 
         * @param Table table is the selected table the user is looking at.
         */

        // creates a newPanel to add all buttons to
        JPanel newPanel = createButtonPanel();
        
        // updates the currentTable so the ActionListeners are looking at the correct table
        newPanel.add(backButton);

        // logic for writing out the button text
        if (activeTab.getListOfMenuItems().size() == 0)
            payButton.setText("Close Tab");
        else
            payButton.setText("Pay Tab");

        newPanel.add(payButton);

        // adds newPanel to listOfPanels
        listOfPanels.add(newPanel);
        
        // calls the method for drawing out the bottompanel
        drawBottomPanel();
    }

    // -------------------- DRAW ----------------------------

    public void drawBottomPanel(){
        /**Method to draw out the bottomPanel. Uses the listOfPanels created by 
         */

        // Loops through the listOfPanels and adds them to the BottomPanel container
        for (JPanel panel : listOfPanels){
            this.add(panel);
        }

        // to ensure that it gets drawn properly
        this.revalidate();
        this.repaint();
    }


    // -------------SETTERS------------------

    public void setCurrentTable(Table currentTable) {
        this.currentTable = currentTable;
    }

    public void setMenuItems(MenuItems menuItems) {
        this.menuItems = menuItems;
    }

    // -------------GETTERS------------------

    public FilledButton getPayButton() {
        return payButton;
    }

    public BorderButton getBackButton() {
        return backButton;
    }

    public FilledButton getTabButton() {
        return tabButton;
    }

    public BorderButton getTransactionButton() {
        return transactionButton;
    }

    public MenuItems getMenuItems() {
        return menuItems;
    }

    public Table getCurrentTable() {
        return currentTable;
    }

    public JButton getIncreaseButton() {
        return increaseButton;
    }

    public JButton getDecreaseButton() {
        return decreaseButton;
    }

    public BorderButton getAddButton() {
        return addButton;
    }
}
