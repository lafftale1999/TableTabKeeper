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

    public MainPanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        

        if (hasBorder == true){
            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            this.setBorder(border);
        }

        
    }

    public void drawTable(Table[] listOfTables){
        /**
         * Draws a table and switches color depending on logic. This re-draws all tables every time its called.
        */
        for (Table table : listOfTables) {
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
        this.repaint();

    }

    public void drawMenuOptions(ArrayList<BorderButton>listOfButtons){
        
        this.removeAll();

        for (BorderButton button : listOfButtons){
            this.add(button);
        }

        this.setVisible(true);
        this.revalidate();
        this.repaint();
    
    }
}
