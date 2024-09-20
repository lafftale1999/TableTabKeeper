package classes;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

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
    private GUIs.TablePanel parentFrame;

    // -------------- CONSTRUCTOR --------------
    public Table(boolean hasTab, int tableId, /* OpenTab activeTab, */ TablePanel frame){

        setHasTab(hasTab);
        setTableId(tableId);
        /* setActiveTab(activeTab); */
        setParentFrame(frame);
        
        // load images for table logic
        emptyTableImage = new ImageIcon("src/images/EmptyTable.png");
        selectedTableImage = new ImageIcon("src/images/SelectedTable.png");
        openTabTableImage = new ImageIcon("src/images/OpenTabTable.png");
        
        // radiobuttons appearance
        this.setSelectedIcon(selectedTableImage); // chooses the image to show when selected
        this.setText(Integer.toString(tableId) + "."); // show the number of the table when drawn
        this.setHorizontalTextPosition(JButton.CENTER); // sets the number x position
        this.setVerticalTextPosition(JButton.CENTER); // sets the number y position
        this.setBackground(frame.getBackground()); // matches the parentContainers background
        this.setFocusable(false); // removes the focus border around the text
        this.addActionListener(this); // adds ActionListener to every table

        parentFrame.setListOfTables(this);
    }

    // -------------- METHODS --------------
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == this) {
            
            if (this.isSelected()) {
                System.out.println("Table " + this.getTableId() + " is selected!");
                
                if (!this.hasTab) {
                    this.hasTab = true;
                    System.out.println("Tab open");
                }
            }

            else {
                System.out.println("Table " + this.getTableId() + " has been unselected!");
            }
            
            drawTable();
        }
        
    }

    public void drawTable(){
        /**
         * Draws a table and switches color depending on logic. This re-draws all tables every time its called.
        */
        for (Table table : parentFrame.getListOfTables()) {
            parentFrame.remove(table);

            if (table.hasTab){
                table.setIcon(openTabTableImage);
                System.out.println("OPEN Table drawn");
            }

            else if (!table.hasTab){
                table.setIcon(emptyTableImage);
                System.out.println("EMPTY Table drawn");
            }

            parentFrame.add(table);
            
        }

        parentFrame.setVisible(true);
        parentFrame.revalidate();

    }

    public void clearTable(){
        this.getParentFrame().remove(this);

        this.getParentFrame().setVisible(true);
        this.getParentFrame().revalidate();
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

    public void setParentFrame(TablePanel parentFrame) {
        this.parentFrame = parentFrame;
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

    public TablePanel getParentFrame() {
        return parentFrame;
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
