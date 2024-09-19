package classes;

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

    public Table(boolean hasTab, int tableId, /* OpenTab activeTab, */ MyPanel frame){

        setHasTab(hasTab);
        setTableId(tableId);
        /* setActiveTab(activeTab); */
        setParentFrame(frame);
        
        emptyTableImage = new ImageIcon("C:\\Users\\prett\\Downloads\\Tables\\EmptyTable.png");
        selectedTableImage = new ImageIcon("C:\\Users\\prett\\Downloads\\Tables\\SelectedTable.png");
        openTabTableImage = new ImageIcon("C:\\Users\\prett\\Downloads\\Tables\\OpenTabTable.png");
        
        this.setSelectedIcon(selectedTableImage);

        this.setText(Integer.toString(tableId) + ".");
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setBackground(frame.getBackground());
        this.setFocusable(false);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this) {
            System.out.println("Table " + this.getTableId() + " is selected!");
        }
        
    }

    public void drawTable(){

        parentFrame.remove(this);

        if (this.hasTab){
            this.setIcon(openTabTableImage);
            System.out.println("OPEN Table drawn");
        }

        else if (!this.hasTab){
            this.setIcon(emptyTableImage);
            System.out.println("EMPTY Table drawn");
        }

        parentFrame.add(this);
        parentFrame.setVisible(true);
        parentFrame.revalidate();
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
