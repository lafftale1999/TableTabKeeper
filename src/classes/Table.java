package classes;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import GUIs.buttons.*;
import GUIs.panels.*;

public class Table extends JRadioButton{

    // // -------------- ATTRIBUTES --------------
    private ImageIcon emptyTableImage;
    private ImageIcon selectedTableImage;
    private ImageIcon openTabTableImage;

    private boolean hasTab;
    private int tableId;
    private OpenTab activeTab;
    private MainPanel tablePanel;

    // -------------- CONSTRUCTOR --------------

    public Table(boolean hasTab, int tableId, MainPanel mainPanel){

        setHasTab(hasTab);
        setTableId(tableId);
        setTablePanel(tablePanel);
        
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

    public void clearTable(MainPanel mainPanel){
        mainPanel.remove(this);

        mainPanel.setVisible(true);
        mainPanel.revalidate();
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

    public void setTablePanel(MainPanel parentFrame) {
        this.tablePanel = parentFrame;
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
        return tablePanel;
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
