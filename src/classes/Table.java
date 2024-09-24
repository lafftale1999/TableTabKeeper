package classes;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import GUIs.BottomPanel;
import GUIs.SidePanel;
import GUIs.TablePanel;

import java.awt.event.*;

public class Table extends JRadioButton implements ActionListener{

    // // -------------- ATTRIBUTES --------------
    private ImageIcon emptyTableImage;
    private ImageIcon selectedTableImage;
    private ImageIcon openTabTableImage;

    private boolean hasTab;
    private int tableId;
    private OpenTab activeTab;
    private GUIs.TablePanel tablePanel;
    private BottomPanel bottomPanel;
    private SidePanel sidePanel;

    // -------------- CONSTRUCTOR --------------

    public Table(boolean hasTab, int tableId, TablePanel tablePanel, SidePanel sidePanel, BottomPanel bottomPanel){

        setHasTab(hasTab);
        setTableId(tableId);
        setTablePanel(tablePanel);
        this.bottomPanel = bottomPanel;
        this.sidePanel = sidePanel;
        
        // load images for table logic
        emptyTableImage = new ImageIcon("src/images/EmptyTable.png");
        selectedTableImage = new ImageIcon("src/images/SelectedTable.png");
        openTabTableImage = new ImageIcon("src/images/OpenTabTable.png");
        
        // radiobuttons appearance
        this.setSelectedIcon(selectedTableImage); // chooses the image to show when selected
        this.setText(Integer.toString(tableId) + "."); // show the number of the table when drawn
        this.setHorizontalTextPosition(JButton.CENTER); // sets the number x position
        this.setVerticalTextPosition(JButton.CENTER); // sets the number y position
        this.setBackground(tablePanel.getBackground()); // matches the parentContainers background
        this.setFocusable(false); // removes the focus border around the text
        this.addActionListener(this); // adds ActionListener to every table

        tablePanel.setListOfTables(this);
    }

    // -------------- METHODS --------------
    // SÄTT DENNA I HUVUDMETODEN FÖR VIEWTABLES
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == this) {
            
            if (this.isSelected()) {
                System.out.println("Table " + this.getTableId() + " is selected!");
            }

            tablePanel.drawTable();
            bottomPanel.createInformationBodyBottom(this);
            sidePanel.createContainerForTables();
        }
        
    }

    public void clearTable(){
        this.getTablePanel().remove(this);

        this.getTablePanel().setVisible(true);
        this.getTablePanel().revalidate();
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

    public void setTablePanel(TablePanel parentFrame) {
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

    public TablePanel getTablePanel() {
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
