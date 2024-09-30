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

    private BorderButton backMenuButton = new BorderButton("<-- BACK");

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

            if (table.getActiveTab() != null){
                table.setIcon(table.getOpenTabTableIcon());
            }

            else {
                table.setIcon(table.getEmptyTableIcon());
            }

            this.add(table);
            
        }

        this.setVisible(true);
        this.revalidate();
        this.repaint();

    }

    public void drawMenuOptions(ArrayList<BorderButton>listOfButtons, String menuLayer){
        
        this.removeAll();

        for (BorderButton button : listOfButtons){
            this.add(button);
            System.out.println(button.getText() + " has been added");
        }

        if (!menuLayer.equals("menu"))
            this.add(backMenuButton);

        this.setVisible(true);
        this.revalidate();
        this.repaint();
    
    }

    public void drawPaymentOptions(ArrayList<BorderButton>listOfButtons, String selectedButton){
        
        this.removeAll();

        for (int i = 0; i < listOfButtons.size(); i++){

            if (listOfButtons.get(i).getText() == selectedButton) {
                listOfButtons.get(i).setBackground(new Color(0x00507A));
                listOfButtons.get(i).setForeground(Color.WHITE);
            }

            else {
                listOfButtons.get(i).setBackground(Color.WHITE);
                listOfButtons.get(i).setForeground(new Color(0x00507A));
            }

            this.add(listOfButtons.get(i));
        }

        this.setVisible(true);
        this.revalidate();
        this.repaint();
    
    }

    public BorderButton getBackMenuButton() {
        return backMenuButton;
    }

    public void setBackMenuButton(BorderButton backMenuButton) {
        this.backMenuButton = backMenuButton;
    }
}
