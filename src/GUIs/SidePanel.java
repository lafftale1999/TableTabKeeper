package GUIs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.text.NumberFormat;

import classes.Table;

public class SidePanel extends JPanel{

    private ArrayList<TablePanel> listOfPanels = new ArrayList<TablePanel>();

    public SidePanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new GridLayout(5,1,10,10));
        

        if (hasBorder == true){
            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            this.setBorder(border);
        }
    }

    public void createContainerForTables(Table[] listOfTables){

        if (listOfPanels.size() > 0) {
            for (JPanel panel : listOfPanels){
                this.remove(panel);
            }
            
            listOfPanels.clear();
            
        }

        for (Table table : listOfTables) {
            TablePanel newPanel = new TablePanel();
            newPanel.setLayout(new GridLayout(2,1,0,5));
            newPanel.setPreferredSize(new Dimension(this.getWidth() - 10, 50));
            newPanel.setBackground(this.getBackground());

            JLabel tableNumberTitle = new JLabel();
            tableNumberTitle.setFont(new Font(null, Font.BOLD, 20));
            JLabel tableTabTotal = new JLabel();

            tableNumberTitle.setText("Table " + table.getTableId());
            if (!table.getHasTab())
                tableTabTotal.setText("No open tab");
            else
                tableTabTotal.setText("Total: " + NumberFormat.getCurrencyInstance().format(table.getActiveTab().getTabTotal()));
            
            newPanel.add(tableNumberTitle);
            newPanel.add(tableTabTotal);

            listOfPanels.add(newPanel);
        }
        drawTablesInSidePanel();
    }

    public void drawTablesInSidePanel(){

        for (TablePanel panel : listOfPanels) {
            this.add(panel);
        }

        this.setVisible(true);
        this.revalidate();
    }

}
