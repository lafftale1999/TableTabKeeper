package classes;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import GUIs.MyPanel;

import java.awt.event.*;

public class Table extends JRadioButton implements ActionListener{

    private ImageIcon emptyTableImage;
    private ImageIcon selectedTableImage;
    private ImageIcon openTabTableImage;

    private boolean hasTab;
    private int tableId;
    private OpenTab activeTab;
    private MyPanel parentFrame;
    private static int tableCount = 0;
    public static Table[] listOfTables = new Table[5];


    public Table(boolean hasTab, int tableId, /* OpenTab activeTab, */ MyPanel frame){

        setHasTab(hasTab);
        setTableId(tableId);
        /* setActiveTab(activeTab); */
        setParentFrame(frame);
        
        // load images for table logic
        emptyTableImage = new ImageIcon("C:\\Users\\prett\\Downloads\\Tables\\EmptyTable.png");
        selectedTableImage = new ImageIcon("C:\\Users\\prett\\Downloads\\Tables\\SelectedTable.png");
        openTabTableImage = new ImageIcon("C:\\Users\\prett\\Downloads\\Tables\\OpenTabTable.png");
        
        this.setSelectedIcon(selectedTableImage); // chooses the image to show when selected
        this.setText(Integer.toString(tableId) + "."); // show the number of the table when drawn
        this.setHorizontalTextPosition(JButton.CENTER); // sets the number x position
        this.setVerticalTextPosition(JButton.CENTER); // sets the number y position
        this.setBackground(frame.getBackground()); // matches the parentContainers background
        this.setFocusable(false); // removes the focus border around the text
        this.addActionListener(this); // adds ActionListener to every table

        // adds created table to listOfTables
        Table.listOfTables[this.tableId - 1] = this;

        // increments tableCount to see when we are done with creating tables
        tableCount++;

        // draw all tables
        if (tableCount == 5) {
            drawTable();
        }


        
    }

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
         * Draws all the tables and switches color depending on logic. This re-draws all tables every time its called.
         */

        for (Table table : listOfTables) {
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

    public static ButtonGroup createButtonGroup(Table[] tables){
        /**
         * Creates a ButtonGroup for our list so the user can't select several options at once.
         * 
         * @param Table[] tables Takes a list of tables to add to the list
         * @return Returns a ButtonGroup
         */
        ButtonGroup tableButtonGroup = new ButtonGroup();

        for (Table table : tables) {
            tableButtonGroup.add(table);
        }

        return tableButtonGroup;
    }

    public void setHasTab(boolean hasTab) {
        this.hasTab = hasTab;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setActiveTab(OpenTab tab){
        this.activeTab = tab;
    }

    public void setParentFrame(MyPanel parentFrame) {
        this.parentFrame = parentFrame;
    }

    public boolean getHasTab(){
        return hasTab;
    }

    public int getTableId() {
        return tableId;
    }

    public OpenTab getActiveTab() {
        return activeTab;
    }

    public MyPanel getParentFrame() {
        return parentFrame;
    }
}
