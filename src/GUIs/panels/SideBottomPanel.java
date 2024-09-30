package GUIs.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import classes.OpenTab;
import classes.Table;

public class SideBottomPanel extends JPanel{

    public SideBottomPanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new GridLayout(5,1,0,-60));
        
        if (hasBorder == true){
            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            this.setBorder(border);
        }
    }

    // ----------------- METHODS -----------------
    public JLabel createLabel(){
        /**Creates label for drawmethods
         * 
         * return JLabel newLabel;
         */

        JLabel newLabel = new JLabel();
        newLabel.setHorizontalAlignment(JLabel.CENTER);
        newLabel.setVerticalAlignment(JLabel.TOP);
        newLabel.setFont(new Font(null, Font.PLAIN, 12));

        return newLabel;
    }

    private ArrayList<JLabel> createListOfLabels(float tabTotal, float taxTotal, float tax25, float tax12){
        /**VIEWTABLES / VIEWTAB / VIEW PAYMENT
         * creates list of labels to write out tabtotal in both viewTab and viewTables. This also formats the currency.
         * 
         * @param float tabTotal
         * @param float taxTotal
         * @param float tax25
         * @param float tax12
         * 
         * return ArrayList<JLabel>; a list of labels ready to be drawn
         */

        ArrayList<JLabel> list = new ArrayList<>();

        // Tabtotal
        JLabel totalTitelText = createLabel();
        totalTitelText.setText("Tab total: " + NumberFormat.getCurrencyInstance().format(tabTotal));
        totalTitelText.setFont(new Font(null, Font.BOLD, 16));
        list.add(totalTitelText);

        // Taxtotal
        JLabel taxTitelText = createLabel();
        taxTitelText.setText("Tax total: " + NumberFormat.getCurrencyInstance().format(taxTotal));
        taxTitelText.setFont(new Font(null, Font.PLAIN, 16));
        list.add(taxTitelText);

        // 25%
        JLabel tax25Percent = createLabel();
        tax25Percent.setText("25%: " + NumberFormat.getCurrencyInstance().format(tax25));
        list.add(tax25Percent);

        // 12%
        JLabel tax12Percent = createLabel();
        tax12Percent.setText("12%: " + NumberFormat.getCurrencyInstance().format(tax12));
        list.add(tax12Percent);

        return list;
    }

    // ----------------- VIEW TABLES -----------------
    public void drawTabTotal(Table[] listOfTables){
        /**VIEWTABLES
         * calculates the tabtotal for all tables, calls the createListOfLabels method and the adds labels to sideBottomPanel
         * 
         * @param TAble[] listOfTables is used to calculate the sum of all amounts
         */

        this.removeAll();
        this.revalidate();

        // initiates variables to save results in
        float totalTaxAmount = 0f;
        float totalTabsAmount = 0f;
        float total25taxAmount = 0f;
        float total12taxAmount = 0f;

        // loops through every table and calculates the different sums
        for (Table table : listOfTables) {
            if (table.getActiveTab() != null) {
                float[] taxList = table.getActiveTab().calculateTaxes();
                totalTabsAmount += table.getActiveTab().getTabTotal();
                totalTaxAmount += taxList[0] + taxList[1];
                total12taxAmount += taxList [0];
                total25taxAmount += taxList [1];
            }
        }
        
        // creates list of labels
        ArrayList<JLabel> listOfLabels = createListOfLabels(totalTabsAmount, totalTaxAmount, total25taxAmount, total12taxAmount);

        // adds labels to sideBottomPanel
        for (JLabel label : listOfLabels) {
            this.add(label);
        }

        // paints panel
        this.revalidate();
        this.repaint();

    }
    
    // ----------------- VIEW TABS -----------------
    public void drawTabTotal(OpenTab activeTab){
        /**VIEWTAB
         * calculates the tabtotal, calls the createListOfLabels method and the adds labels to sideBottomPanel
         * 
         * @param OpenTab activeTab is used to all amounts.
         */

        this.removeAll();
        this.revalidate();

        // creates a string with the total taxamount | [0] = 12% [1] = 25%
        float[] taxList = activeTab.calculateTaxes();

        // saves them in variables for easier management
        float totalTabsAmount = activeTab.getTabTotal();
        float totalTaxAmount = taxList[0] + taxList[1];
        float total25taxAmount = taxList[1];
        float total12taxAmount = taxList[0];

        // creates a list of lables
        ArrayList<JLabel> listOfLabels = createListOfLabels(totalTabsAmount, totalTaxAmount, total25taxAmount, total12taxAmount);

        // adds to sideBottomPanel
        for (JLabel label : listOfLabels) {
            this.add(label);
        }

        // paints the panel
        this.revalidate();
        this.repaint();

    }   
}
