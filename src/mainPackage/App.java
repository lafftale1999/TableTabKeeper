package mainPackage;

import javax.swing.ButtonGroup;
import GUIs.BottomPanel;
import GUIs.MyFrame;
import GUIs.MyPanel;
import GUIs.SidePanel;
import GUIs.TablePanel;
import classes.MenuItems;
import classes.Table;

public class App {
    public static void main(String[] args) throws Exception {
        MyFrame frame = new MyFrame();
        MenuItems menuItems = new MenuItems();

        int amountOfTables = 5;

        // backgrounds colors for our panels
        int[] mainPanelColor = {233,233,233};
        int[] sidePanelColor = {210,210,210};
        int[] bottomPanelColor = {178,178,178};
        
        TablePanel tablePanel = new TablePanel(0,0,700,550,mainPanelColor, true);
        
        SidePanel sidePanel = new SidePanel(tablePanel.getWidth(), 0, frame.getWidth() - tablePanel.getWidth(), 550, sidePanelColor, true, tablePanel);

        MyPanel sideBottomPanel = new MyPanel(sidePanel.getX(), sidePanel.getHeight(), sidePanel.getWidth(), frame.getHeight() - sidePanel.getHeight(), bottomPanelColor, true);
        
        BottomPanel bottomPanel = new BottomPanel(0, tablePanel.getHeight(), frame.getWidth() - sideBottomPanel.getWidth(), frame.getHeight() - tablePanel.getHeight(), bottomPanelColor, true, tablePanel, menuItems);

        // adds all panels to the frame
        frame.add(tablePanel);
        frame.add(sidePanel);
        frame.add(sideBottomPanel);
        frame.add(bottomPanel);
        
        // intialise two arrays for the tables
        Table[] listOfTables = new Table[5];
        ButtonGroup tableButtonGroup = new ButtonGroup();

        // create tables and add to arrays/groups
        for (int i = 0; i < amountOfTables; i++) {
            listOfTables[i] = new Table(false, i + 1, tablePanel, sidePanel, bottomPanel);
            tableButtonGroup.add(listOfTables[i]);
        }

        // draw components
        tablePanel.drawTable();
        sidePanel.createContainerForTables();
        bottomPanel.createInformationBodyBottom();
        
        for (MenuItems item : menuItems.getListOfCourseProducts()) {
            System.out.println(item.getName());
            System.out.println(item.getPrice());
        }
    }
}
