package GUIs;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import classes.Table;

public class TablePanel extends JPanel{

    private ArrayList<Table> listOfTables = new ArrayList<Table>();

    public TablePanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        

        if (hasBorder == true){
            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            this.setBorder(border);
        }

        
    }

    public void setListOfTables(Table component){
        listOfTables.add(component);
    }

    public ArrayList<Table> getListOfTables(){
        return listOfTables;
    }

    public void drawTable(){
        /**
         * Draws a table and switches color depending on logic. This re-draws all tables every time its called.
        */
        for (Table table : this.getListOfTables()) {
            this.remove(table);

            if (table.getHasTab()){
                table.setIcon(table.getOpenTabTableIcon());
                System.out.println("OPEN Table drawn");
            }

            else if (!table.getHasTab()){
                table.setIcon(table.getEmptyTableIcon());
                System.out.println("EMPTY Table drawn");
            }

            this.add(table);
            
        }

        this.setVisible(true);
        this.revalidate();

    }
}
