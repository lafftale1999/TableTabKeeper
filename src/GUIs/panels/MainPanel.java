package GUIs.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import classes.Table;
import GUIs.buttons.*;

public class MainPanel extends JPanel{

    private BorderButton backMenuButton = new BorderButton("<- BACK");

    public MainPanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        if (hasBorder == true){
            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            this.setBorder(border);
        }
    }
    
    // ----------------- VIEW TABLES -----------------
    public void drawTable(Table[] listOfTables){
        /**VIEWTABLES
         * Draws a table and switches color depending on logic. This re-draws all tables every time its called.
        */

        this.removeAll();

        // loops through all tables and checks for logic.
        for (Table table : listOfTables) {

            // if table has an active tab
            if (table.getActiveTab() != null) table.setIcon(table.getOpenTabTableIcon());

            // if table dont have an active tab
            else table.setIcon(table.getEmptyTableIcon());

            // add to mainPanel
            this.add(table);
        }

        this.revalidate();
        this.repaint();
    }

    // ----------------- VIEW TABS -----------------
    public void drawMenuOptions(ArrayList<BorderButton>listOfButtons, String menuLayer){
        /**VIEWTAB
         * Draws out all meny options for the viewtab class. 
         * 
         * @param ArrayList<BorderButton>listOfButtons is used to loop through all the buttons and draw them out
         * @param String menuLayer is used to determine if we are at the top or in a submenu
         */

        this.removeAll();

        // loops through and adds all buttons to mainPanel
        for (BorderButton button : listOfButtons){
            this.add(button);
        }

        // if we are in a submenu - add backbutton
        if (!menuLayer.equalsIgnoreCase("menu")) this.add(backMenuButton);

        this.revalidate();
        this.repaint();
    }

    // ----------------- VIEW PAYMENT -----------------
    public void drawPaymentOptions(ArrayList<BorderButton>listOfButtons, String selectedButton){
        /**VIEWPAYMENT
         * Draws out the options for payment in the mainPanel.
         * 
         * @param ArrayList<BorderButton>listOfButtons loops through the list and adds to mainPanel
         * @param String selectedButton is used to determine which button is selected.
         */

        this.removeAll();

        // loops through the list of buttons
        for (int i = 0; i < listOfButtons.size(); i++){

            // if selected button
            if (listOfButtons.get(i).getText() == selectedButton) {
                listOfButtons.get(i).setBackground(new Color(0x00507A));
                listOfButtons.get(i).setForeground(Color.WHITE);
            }
            
            // if not selected
            else {
                listOfButtons.get(i).setBackground(Color.WHITE);
                listOfButtons.get(i).setForeground(new Color(0x00507A));
            }
            // add to mainPanel
            this.add(listOfButtons.get(i));
        }
        
        this.revalidate();
        this.repaint();
    
    }

    // -------------------- GETTERS -----------------------
    public BorderButton getBackMenuButton() {
        return backMenuButton;
    }

    // -------------------- SETTERS -----------------------
    public void setBackMenuButton(BorderButton backMenuButton) {
        this.backMenuButton = backMenuButton;
    }
}
