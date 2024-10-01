package GUIs.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import GUIs.buttons.FunctionButton;

import java.text.NumberFormat;

import classes.MenuItems;
import classes.OpenTab;
import classes.Table;

public class SidePanel extends JPanel{

    private ArrayList<JPanel> listOfPanels = new ArrayList<JPanel>();
    private SideBottomPanel sideBottomPanel;
    private BottomPanel bottomPanel;
    
    public SidePanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        
        if (hasBorder == true){
            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            this.setBorder(border);
        }
    }

    // ----------------- METHODS -----------------
    public void clearPanels(){
        /**Clears listOfPanels and removes all components from sidePanel
        */

        if (listOfPanels.size() > 0) {
            this.removeAll();
            listOfPanels.clear();
        }
    }

    // ----------------- VIEWTABLES -----------------
    public void createContainerForTables(Table[] listOfTables){
        /**VIEWTABLES
         * Creates a container and fills it with content.
         * 
         * @param Table[] listOfTables is looped through to write out information about every Table
         */

        clearPanels();

        // loops through all tables
        for (Table table : listOfTables) {
            
            // creates panel to save labels to
            JPanel newPanel = new JPanel();
            newPanel.setLayout(new GridLayout(2,1,10,0));
            newPanel.setPreferredSize(new Dimension(this.getWidth() - 10, 50));
            newPanel.setBackground(this.getBackground());

            // create title
            JLabel tableNumberTitle = new JLabel();
            tableNumberTitle.setFont(new Font("Verdana", Font.BOLD, 16));
            tableNumberTitle.setPreferredSize(new Dimension(this.getWidth(), newPanel.getHeight() / 2));
            tableNumberTitle.setText("Table " + table.getTableId());

            // create tab total
            JLabel tableTabTotal = new JLabel();
            tableTabTotal.setPreferredSize(new Dimension(this.getWidth(), newPanel.getHeight() / 2));
            
            if (table.getActiveTab() == null) tableTabTotal.setText("No open tab");
            else tableTabTotal.setText("Total: " + NumberFormat.getCurrencyInstance().format(table.getActiveTab().getTabTotal()));
            
            // add to panel
            newPanel.add(tableNumberTitle);
            newPanel.add(tableTabTotal);

            // add panel to listOfPanels
            listOfPanels.add(newPanel);
        }

        // paint container
        drawSidePanel();
    }

    // ------------ METHODS FOR VIEWTAB ------------------
    private JLabel createLabelForActiveTab(){
        /**VIEWTAB & VIEWPAYMENT
         * Creates a label for writing out information in sidePanel during ViewTab and ViewPayment
         * 
         * return JLabel;
         */

        Font font = new Font(null, Font.PLAIN, 12);

        JLabel label = new JLabel();
        label.setFont(font);
        label.setPreferredSize(new Dimension(this.getWidth() - 100, 20));
        label.setForeground(Color.BLACK);

        return label;
    }

    public void createContainerForActiveTab(OpenTab activeTab, Table activeTable, String activeLayer){
        
        clearPanels();

        // creates panel to save headline in
        JPanel headlinePanel = new JPanel();
        headlinePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,10));
        headlinePanel.setPreferredSize(new Dimension(this.getWidth() -20 , 30));
        headlinePanel.setBackground(this.getBackground());
        
        // creates headline and adds to panel
        JLabel headlineInformation = new JLabel();
        headlineInformation.setFont(new Font(null, Font.BOLD, 16));
        headlineInformation.setText("Table " + activeTable.getTableId() + " | " + "Tab: " + activeTable.getActiveTab().getTabId());
        headlinePanel.add(headlineInformation);

        // add panel to listOfPanels
        listOfPanels.add(headlinePanel);

        // if tab HAS NO items
        if (activeTab.getListOfMenuItems().isEmpty()){
                
            // creates new panel
            JPanel newPanel = new JPanel();
            newPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0,0));
            newPanel.setBackground(this.getBackground());
            
            // creates label for message
            JLabel productInformation = createLabelForActiveTab();
            productInformation.setText("No products added yet");

            // adds to panel
            newPanel.add(productInformation);
            
            // adds panel to list
            listOfPanels.add(newPanel);
        }

        // if tab HAS items
        else {

            // loops through every item in the active tab
            for (MenuItems product : activeTab.getListOfMenuItems()) {
                
                // calculates price for each product in list
                float totalProductPrice = product.getAmount() * product.getPrice();

                // create new panel
                JPanel newPanel = new JPanel();
                newPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                newPanel.setBackground(this.getBackground());
                
                // creates label to carry product information
                JLabel productInformation = createLabelForActiveTab();
                productInformation.setForeground(Color.BLACK);
                productInformation.setText(product.getAmount() + "x " + product.getName() + " | " + NumberFormat.getCurrencyInstance().format(totalProductPrice));

                // adds to panel
                newPanel.add(productInformation);
                
                // if we are in VIEWTAB
                if (activeLayer.equalsIgnoreCase("viewtab")) {
                    
                    // create a remove button
                    FunctionButton removeButton = new FunctionButton(40, 20, "-");
                    
                    // add actionlistener to button
                    removeButton.addActionListener(e -> {
                        
                        // removes the product
                        activeTab.removeMenuItem(product);

                        // redraws window
                        sideBottomPanel.drawTabTotal(activeTab);
                        bottomPanel.createAddProductPanel(activeTable, "", -1);
                        createContainerForActiveTab(activeTab, activeTable, activeLayer);
                    });
                    
                    // add button to panel
                    newPanel.add(removeButton);
                }

                // add panels to list
                listOfPanels.add(newPanel);
            }
        }

        // draw panel
        drawSidePanel();
    }

    // ------------ DRAW ------------------
    public void drawSidePanel(){
        /**Draws out the sidePanel */
        
        for (JPanel panel : listOfPanels) {
            this.add(panel);
        }

        this.revalidate();
        this.repaint();
    }

    // ----------------- GETTERS -----------------
    public SideBottomPanel getSideBottomPanel() {
        return sideBottomPanel;
    }

    // ----------------- SETTERS -----------------
    public void setSideBottomPanel(SideBottomPanel sideBottomPanel) {
        this.sideBottomPanel = sideBottomPanel;
    }

    public void setBottomPanel(BottomPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
    }
}
