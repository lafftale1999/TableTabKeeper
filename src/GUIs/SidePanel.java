package GUIs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.text.NumberFormat;

import classes.MenuItems;
import classes.OpenTab;
import classes.Table;

public class SidePanel extends JPanel{

    private ArrayList<JPanel> listOfPanels = new ArrayList<JPanel>();

    public SidePanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new GridLayout(5,1,10,10));
        

        if (hasBorder == true){
            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            this.setBorder(border);
        }
    }

    public void clearPanels(){
        if (listOfPanels.size() > 0) {
            for (JPanel panel : listOfPanels){
                this.remove(panel);
            }
            listOfPanels.clear();
        }
    }

    // ------------ METHODS FOR VIEWTABLE ------------------
    public void createContainerForTables(Table[] listOfTables){

        clearPanels();

        for (Table table : listOfTables) {
            JPanel newPanel = new JPanel();
            newPanel.setLayout(new GridLayout(2,1,0,5));
            newPanel.setPreferredSize(new Dimension(this.getWidth(), 25));
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
        drawSidePanel();
    }

    // ------------ METHODS FOR VIEWTAB ------------------
    public void createContainerForActiveTab(OpenTab activeTab, Table activeTable){
        
        clearPanels();

        JPanel headlinePanel = new JPanel();
        headlinePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0,0));
        headlinePanel.setPreferredSize(new Dimension(this.getWidth(), 50));
        headlinePanel.setBackground(this.getBackground());
        
        
        JLabel headlineInformation = new JLabel();
        headlineInformation.setFont(new Font(null, Font.BOLD, 16));
        headlineInformation.setPreferredSize(new Dimension(headlinePanel.getWidth(),headlinePanel.getHeight()));

        headlineInformation.setText("Table " + activeTable.getTableId());

        headlinePanel.add(headlineInformation);
        listOfPanels.add(headlinePanel);

        if (activeTab.getListOfMenuItems().size() == 0){
                Font font = new Font(null, Font.PLAIN, 12);
                
                JPanel newPanel = new JPanel();
                newPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0,0));
                newPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
                newPanel.setBackground(this.getBackground());
                
                
                JLabel productInformation = new JLabel();
                productInformation.setFont(font);
                productInformation.setPreferredSize(new Dimension(newPanel.getWidth(),newPanel.getHeight()));

                productInformation.setText("No products added yet");

                newPanel.add(productInformation);
                listOfPanels.add(newPanel);
        }

        else {
            for (MenuItems product : activeTab.getListOfMenuItems()) {
                Font font = new Font(null, Font.PLAIN, 12);
                
                JPanel newPanel = new JPanel();
                newPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0,0));
                newPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
                newPanel.setBackground(this.getBackground());
                
                
                JLabel productInformation = new JLabel();
                productInformation.setFont(font);
                productInformation.setPreferredSize(new Dimension(newPanel.getWidth(),newPanel.getHeight()));
                
                float totalProductPrice = product.getAmount() * product.getPrice();
                
                productInformation.setText(product.getAmount() + "x | " + product.getName() + " | " + NumberFormat.getCurrencyInstance().format(totalProductPrice));

                newPanel.add(productInformation);
    
                listOfPanels.add(newPanel);
            }
        }
        
        

    }

    public void drawSidePanel(){

        for (JPanel panel : listOfPanels) {
            this.add(panel);
        }

        this.setVisible(true);
        this.revalidate();
    }

}
