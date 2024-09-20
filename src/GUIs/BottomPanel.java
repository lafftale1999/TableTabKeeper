package GUIs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.ArrayList;
import javax.swing.border.Border;

import classes.Table;

public class BottomPanel extends JPanel{
    
    private TablePanel tablePanel;
    private ArrayList<JPanel> listOfPanels = new ArrayList<JPanel>();
    private JButton tabButton = new JButton();
    private JButton transactionButton = new JButton();

    public BottomPanel(int xPosition, int yPosition, int width, int height, int[] colorRGB, boolean hasBorder, TablePanel tablePanel){
        this.setBounds(xPosition,yPosition,width,height);
        this.setBackground(new Color(colorRGB[0], colorRGB[1], colorRGB[2]));
        this.setLayout(new GridLayout(2,1));

        
        this.tablePanel = tablePanel;

        if (hasBorder == true){
            Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
            this.setBorder(border);
        }
    }

    public void createInformationBodyBottom(Table table){
        if (listOfPanels.size() > 0) {
            for (JPanel panel : listOfPanels) {
                this.remove(panel);
            }
            listOfPanels.clear();
            this.setVisible(true);
            this.revalidate();
        }

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(2,1,0,5));
        newPanel.setPreferredSize(new Dimension(this.getWidth() - 10, 50));
        newPanel.setBackground(this.getBackground());
        newPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        JLabel title = new JLabel();
        title.setFont(new Font("Verdana", Font.BOLD, 16));

        JLabel description = new JLabel();

        title.setText("Table " + table.getTableId());
        description.setText("Total: 5 000,00 kr");

        newPanel.add(title);
        newPanel.add(description);

        listOfPanels.add(newPanel);
        createButtonsBottomPanel(table);
        
    }

    public void createInformationBodyBottom(){
        if (listOfPanels.size() > 0) {
            for (JPanel panel : listOfPanels) {
                this.remove(panel);
            }
            listOfPanels.clear();
            this.setVisible(true);
            this.revalidate();
        }

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(2,1,0,5));
        newPanel.setPreferredSize(new Dimension(this.getWidth() - 10, 50));
        newPanel.setBackground(this.getBackground());
        newPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        JLabel title = new JLabel();
        title.setFont(new Font("Verdana", Font.BOLD, 16));

        JLabel description = new JLabel();
        
        title.setText("No table selected");
        description.setText("Choose a table see more options!");

        newPanel.add(title);
        newPanel.add(description);

        listOfPanels.add(newPanel);
        createButtonsBottomPanel();
        
    }

    public void createButtonsBottomPanel(Table table){
        
        JPanel newPanel = new JPanel();

        newPanel.setLayout(new GridLayout(2,1,0,5));
        newPanel.setPreferredSize(new Dimension(this.getWidth() - 10, 50));
        newPanel.setBackground(this.getBackground());
        newPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        
        if (table.getHasTab())
            tabButton.setText("Open Tab");
        else
            tabButton.setText("Create Tab");
        
        transactionButton.addActionListener(e -> {
            System.out.println("Open transactions");
        });

        tabButton.addActionListener(e -> {
            System.out.println(table.getTableId());
        });

        newPanel.add(tabButton);
        newPanel.add(transactionButton);

        listOfPanels.add(newPanel);
        
        drawBottomPanel();
    }

    public void createButtonsBottomPanel(){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(2,1,0,5));
        newPanel.setPreferredSize(new Dimension(this.getWidth() - 10, 50));
        newPanel.setBackground(this.getBackground());
        newPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        
        transactionButton.addActionListener(e -> {
            System.out.println("Open transactions");
        });

        newPanel.add(transactionButton);

        listOfPanels.add(newPanel);
        drawBottomPanel();
    }

    public void drawBottomPanel(){
        for (JPanel panel : listOfPanels){
            this.add(panel);
        }

        this.setVisible(true);
        this.revalidate();

        System.out.println("Now the list is: " + listOfPanels.size());
    }
}
