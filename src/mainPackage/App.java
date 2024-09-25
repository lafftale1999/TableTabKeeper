package mainPackage;


import GUIs.BottomPanel;
import GUIs.MyFrame;
import GUIs.SideBottomPanel;
import GUIs.SidePanel;
import GUIs.MainPanel;
import LayersView.ViewTables;
import classes.MenuItems;

public class App {
    
    public static void main(String[] args) throws Exception {

        MyFrame frame = new MyFrame();
        
        MenuItems menuItems = new MenuItems();

        // backgrounds colors for our panels
        int[] mainPanelColor = {233,233,233};
        int[] sidePanelColor = {210,210,210};
        int[] bottomPanelColor = {178,178,178};
        
        // VIEWTABLES PANELS
        MainPanel tablePanel = new MainPanel(0,0,700,550,mainPanelColor, true);
        
        SidePanel sidePanel = new SidePanel(tablePanel.getWidth(), 0, frame.getWidth() - tablePanel.getWidth(), 550, sidePanelColor, true);

        SideBottomPanel sideBottomPanel = new SideBottomPanel(sidePanel.getX(), sidePanel.getHeight(), sidePanel.getWidth(), frame.getHeight() - sidePanel.getHeight(), bottomPanelColor, true);
        
        BottomPanel bottomPanel = new BottomPanel(0, tablePanel.getHeight(), frame.getWidth() - sideBottomPanel.getWidth(), frame.getHeight() - tablePanel.getHeight(), bottomPanelColor, true, tablePanel, menuItems);

        ViewTables viewTables = new ViewTables(frame, tablePanel, sidePanel, bottomPanel, sideBottomPanel);
        
        // set next layer för varje viewclass
        viewTables.drawViewTables();


    }
}
