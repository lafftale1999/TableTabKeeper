package GUIs.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;

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

    public JLabel createLabel(){

        JLabel newLabel = new JLabel();
        newLabel.setHorizontalAlignment(JLabel.CENTER);
        newLabel.setVerticalAlignment(JLabel.TOP);
        newLabel.setFont(new Font(null, Font.PLAIN, 12));

        return newLabel;

    }

    public void drawTabTotal(OpenTab activeTab){

        this.removeAll();
        this.revalidate();

        float[] taxList = activeTab.calculateTaxes();
        System.out.println("12% " + taxList[0]);
        System.out.println("25% " + taxList[1]);

        JLabel totalTitelText = createLabel();
        totalTitelText.setText("Tab total: " + NumberFormat.getCurrencyInstance().format(activeTab.getTabTotal()));
        totalTitelText.setFont(new Font(null, Font.BOLD, 16));

        JLabel taxTitelText = createLabel();
        taxTitelText.setText("Tax total: " + NumberFormat.getCurrencyInstance().format(taxList[0] + taxList[1]));
        taxTitelText.setFont(new Font(null, Font.PLAIN, 16));

        JLabel tax25Percent = createLabel();
        tax25Percent.setText("25%: " + NumberFormat.getCurrencyInstance().format(taxList[1]));

        JLabel tax12Percent = createLabel();
        tax12Percent.setText("12%: " + NumberFormat.getCurrencyInstance().format(taxList[0]));

        this.add(totalTitelText);
        this.add(taxTitelText);
        this.add(tax25Percent);
        this.add(tax12Percent);

        this.revalidate();
        this.repaint();

    }

    public void drawTabTotal(Table[] listOfTables){

        this.removeAll();
        this.revalidate();

        float totalTaxAmount = 0f;
        float totalTabsAmount = 0f;
        float total25taxAmount = 0f;
        float total12taxAmount = 0f;

        for (Table table : listOfTables) {
            if (table.getHasTab()) {
                float[] taxList = table.getActiveTab().calculateTaxes();
                totalTabsAmount += table.getActiveTab().getTabTotal();
                totalTaxAmount += taxList[0] + taxList[1];
                total12taxAmount += taxList [0];
                total25taxAmount += taxList [1];
            }
            
        }
        
        JLabel totalTitelText = createLabel();
        totalTitelText.setText("Tab total: " + NumberFormat.getCurrencyInstance().format(totalTabsAmount));
        totalTitelText.setFont(new Font(null, Font.BOLD, 16));

        JLabel taxTitelText = createLabel();
        taxTitelText.setText("Tax total: " + NumberFormat.getCurrencyInstance().format(totalTaxAmount));
        taxTitelText.setFont(new Font(null, Font.PLAIN, 16));

        JLabel tax25Percent = createLabel();
        tax25Percent.setText("25%: " + NumberFormat.getCurrencyInstance().format(total25taxAmount));

        JLabel tax12Percent = createLabel();
        tax12Percent.setText("12%: " + NumberFormat.getCurrencyInstance().format(total12taxAmount));

        this.add(totalTitelText);
        this.add(taxTitelText);
        this.add(tax25Percent);
        this.add(tax12Percent);

        this.revalidate();
        this.repaint();

    }
}
