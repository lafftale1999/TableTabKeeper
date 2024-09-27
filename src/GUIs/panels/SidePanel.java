package GUIs.panels;

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

import GUIs.buttons.FunctionButton;

import java.text.NumberFormat;

import classes.MenuItems;
import classes.OpenTab;
import classes.Table;

public class SidePanel extends JPanel{

    private ArrayList<JPanel> listOfPanels = new ArrayList<JPanel>();
    private SideBottomPanel sideBottomPanel;
    

    public SidePanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        

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
            newPanel.setLayout(new GridLayout(2,1,10,0));
            newPanel.setPreferredSize(new Dimension(this.getWidth() - 10, 50));
            newPanel.setBackground(this.getBackground());

            JLabel tableNumberTitle = new JLabel();
            tableNumberTitle.setFont(new Font(null, Font.BOLD, 16));
            tableNumberTitle.setPreferredSize(new Dimension(this.getWidth(), newPanel.getHeight() / 2));
            
            JLabel tableTabTotal = new JLabel();
            tableTabTotal.setPreferredSize(new Dimension(this.getWidth(), newPanel.getHeight() / 2));
            
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
        headlinePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,10));
        headlinePanel.setPreferredSize(new Dimension(this.getWidth() -20 , 30));
        headlinePanel.setBackground(this.getBackground());

        this.revalidate();
        
        JLabel headlineInformation = new JLabel();
        headlineInformation.setFont(new Font(null, Font.BOLD, 16));

        headlineInformation.setText("Table " + activeTable.getTableId() + " | " + "Tab: " + activeTable.getActiveTab().getTabId());

        headlinePanel.add(headlineInformation);
        listOfPanels.add(headlinePanel);

        if (activeTab.getListOfMenuItems().isEmpty()){
                Font font = new Font(null, Font.PLAIN, 12);
                
                JPanel newPanel = new JPanel();
                newPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0,0));
                newPanel.setBackground(this.getBackground());
                
                
                JLabel productInformation = new JLabel();
                productInformation.setFont(font);
                productInformation.setForeground(Color.BLACK);
                productInformation.setText("No products added yet");

                newPanel.add(productInformation);
                listOfPanels.add(newPanel);
        }

        else {
            for (MenuItems product : activeTab.getListOfMenuItems()) {
                
                FunctionButton removeButton = new FunctionButton(40, 20, "-");
                removeButton.addActionListener(e -> {
                    System.out.println("Tab before removal: " + activeTab.getTabTotal() + "kr | " + activeTab.getListOfMenuItems().size()); 
                    activeTab.removeMenuItem(product);
                    System.out.println("Tab after removal: " + activeTab.getTabTotal() + "kr | " + activeTab.getListOfMenuItems().size());

                    sideBottomPanel.drawTabTotal(activeTab);
                    createContainerForActiveTab(activeTab, activeTable);
                });
                
                Font font = new Font(null, Font.PLAIN, 12);
                
                JPanel newPanel = new JPanel();
                newPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                newPanel.setBackground(this.getBackground());
                
                
                JLabel productInformation = new JLabel();
                productInformation.setFont(font);
                productInformation.setPreferredSize(new Dimension(this.getWidth() - 100, 20));
                productInformation.setForeground(Color.BLACK);
                
                float totalProductPrice = product.getAmount() * product.getPrice();
                
                productInformation.setText(product.getAmount() + "x " + product.getName() + " | " + NumberFormat.getCurrencyInstance().format(totalProductPrice));

                newPanel.add(productInformation);
                newPanel.add(removeButton);
    
                listOfPanels.add(newPanel);
            }
        }

        drawSidePanel();

    }

    public void drawSidePanel(){

        for (JPanel panel : listOfPanels) {
            this.add(panel);
        }

        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public SideBottomPanel getSideBottomPanel() {
        return sideBottomPanel;
    }

    public void setSideBottomPanel(SideBottomPanel sideBottomPanel) {
        this.sideBottomPanel = sideBottomPanel;
    }
}
