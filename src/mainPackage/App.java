package mainPackage;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import GUIs.MyFrame;
import GUIs.MyPanel;
import GUIs.TablePanel;
import classes.Product;
import classes.Table;

public class App {
    public static void main(String[] args) throws Exception {
        MyFrame frame = new MyFrame();

        int amountOfTables = 5;

        // backgrounds colors for our panels
        int[] mainPanelColor = {233,233,233};
        int[] sidePanelColor = {210,210,210};
        int[] bottomPanelColor = {178,178,178};
        
        TablePanel tablePanel = new TablePanel(0,0,700,550,mainPanelColor, true);
        
        MyPanel sidePanel = new MyPanel(tablePanel.getWidth(), 0, frame.getWidth() - tablePanel.getWidth(), 550, sidePanelColor, true);
        
        MyPanel sideBottomPanel = new MyPanel(sidePanel.getX(), sidePanel.getHeight(), sidePanel.getWidth(), frame.getHeight() - sidePanel.getHeight(), bottomPanelColor, true);
        
        MyPanel bottomPanel = new MyPanel(0, tablePanel.getHeight(), frame.getWidth() - sideBottomPanel.getWidth(), frame.getHeight() - tablePanel.getHeight(), bottomPanelColor, true);

        // adds all panels to the frame
        frame.add(tablePanel);
        frame.add(sidePanel);
        frame.add(sideBottomPanel);
        frame.add(bottomPanel);
        
        Table[] listOfTables = new Table[5];
        ButtonGroup tableButtonGroup = new ButtonGroup();

        // create tables and draw tables
        for (int i = 0; i < amountOfTables; i++) {
            listOfTables[i] = new Table(false, i + 1, tablePanel);
            tableButtonGroup.add(listOfTables[i]);
        }

        listOfTables[0].drawTable();

    }
}
