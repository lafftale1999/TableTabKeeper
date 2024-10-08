package classes;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import GUIs.panels.*;

public class Table extends JRadioButton{

    // // -------------- ATTRIBUTES --------------
    private ImageIcon emptyTableImage;
    private ImageIcon selectedTableImage;
    private ImageIcon openTabTableImage;

    private boolean hasTab;
    private int tableId;
    private OpenTab activeTab;
    private MainPanel mainPanel;

    // -------------- CONSTRUCTOR --------------
    public Table(boolean hasTab, int tableId, MainPanel mainPanel){

        setHasTab(hasTab);
        setTableId(tableId);
        setMainPanel(mainPanel);
        
        // load images for table logic
        emptyTableImage = new ImageIcon("src/images/EmptyTable.png");
        selectedTableImage = new ImageIcon("src/images/SelectedTable.png");
        openTabTableImage = new ImageIcon("src/images/OpenTabTable.png");
        
        // radiobuttons appearance
        this.setSelectedIcon(selectedTableImage); // chooses the image to show when selected
        this.setText(Integer.toString(tableId) + "."); // show the number of the table when drawn
        this.setHorizontalTextPosition(JButton.CENTER); // sets the number x position
        this.setVerticalTextPosition(JButton.CENTER); // sets the number y position
        this.setBackground(mainPanel.getBackground()); // matches the parentContainers background
        this.setFocusable(false); // removes the focus border around the text
    }

    // -------------- METHODS --------------
    public void removeTab(){
        /**Removes the active tab from table (after payment or closing tab) */
        
        if (this.activeTab != null) {
            this.activeTab = null;
            this.hasTab = false;
        }

        else 
            System.out.println("No active tabs for Table " + this.tableId);
        
    }


    // -------------- SETTERS --------------
    public void setHasTab(boolean hasTab) {
        this.hasTab = hasTab;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setActiveTab(OpenTab tab){
        this.activeTab = tab;
    }

    public void setMainPanel(MainPanel parentFrame) {
        this.mainPanel = parentFrame;
    }

    // -------------- GETTERS --------------
    public boolean getHasTab(){
        return hasTab;
    }

    public int getTableId() {
        return tableId;
    }

    public OpenTab getActiveTab() {
        return activeTab;
    }

    public MainPanel getTablePanel() {
        return mainPanel;
    }
    
    public ImageIcon getEmptyTableIcon(){
        return emptyTableImage;
    }

    public ImageIcon getSelectedTableIcon(){
        return selectedTableImage;
    }

    public ImageIcon getOpenTabTableIcon(){
        return openTabTableImage;
    }
}
