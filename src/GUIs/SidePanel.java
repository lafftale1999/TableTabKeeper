package GUIs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import classes.Table;

public class SidePanel extends JPanel{

    private ArrayList<JPanel> listOfPanels = new ArrayList<JPanel>();
    private TablePanel dependingPanel;

    public SidePanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new GridLayout(5,1,10,10));
        

        if (hasBorder == true){
            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            this.setBorder(border);
        }
    }

    public void createContainerForTables(Table table){
        
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(2,1,0,5));
        newPanel.setPreferredSize(new Dimension(this.getWidth() - 10, 50));
        newPanel.setBackground(this.getBackground());

        JLabel tableNumberTitle = new JLabel();
        JLabel tableTabTotal = new JLabel();

        tableNumberTitle.setText("Table " + table.getTableId());
        tableTabTotal.setText("Total: 5 000,00 kr");

        newPanel.add(tableNumberTitle);
        newPanel.add(tableTabTotal);

        listOfPanels.add(newPanel);
    }

    public void drawTablesInSidePanel(){
        
        for (JPanel panel : listOfPanels) {
            this.add(panel);
        }

        this.setVisible(true);
        this.revalidate();
    }

}
