package mainPackage;

import GUIs.panels.*;
import GUIs.frames.*;
import LayersView.ViewPayment;
import LayersView.ViewTab;
import LayersView.ViewTables;
import classes.MenuItems;
import classes.Transaction;

public class App {
    
    public static void main(String[] args) throws Exception {

        // creates the frame
        MyFrame frame = new MyFrame();
        
        // create menuItems
        MenuItems menuItems = new MenuItems();

        // read all saved transactions
        Transaction transactionList = new Transaction();

        // backgrounds colors for our panels
        int[] mainPanelColor = {233,233,233};
        int[] sidePanelColor = {210,210,210};
        int[] bottomPanelColor = {178,178,178};
        
        // Create panels
        MainPanel mainPanel = new MainPanel(0,0,700,550,mainPanelColor, true);
        
        SidePanel sidePanel = new SidePanel(mainPanel.getWidth(), 0, frame.getWidth() - mainPanel.getWidth(), 550, sidePanelColor, true);

        SideBottomPanel sideBottomPanel = new SideBottomPanel(sidePanel.getX(), sidePanel.getHeight(), sidePanel.getWidth(), frame.getHeight() - sidePanel.getHeight(), bottomPanelColor, true);
        
        BottomPanel bottomPanel = new BottomPanel(0, mainPanel.getHeight(), frame.getWidth() - sideBottomPanel.getWidth(), frame.getHeight() - mainPanel.getHeight(), bottomPanelColor, true, menuItems);
        sidePanel.setSideBottomPanel(sideBottomPanel);
        sidePanel.setBottomPanel(bottomPanel);
        
        // Create Layers
        ViewTables viewTables = new ViewTables(frame, mainPanel, sidePanel, bottomPanel, sideBottomPanel);
        
        ViewTab viewTab = new ViewTab(menuItems, null, viewTables, mainPanel, sidePanel, sideBottomPanel, bottomPanel);
        viewTables.setNextLayer(viewTab);
        viewTables.addTabButtonListener();

        ViewPayment viewPayment = new ViewPayment(viewTab, viewTables, mainPanel, sidePanel, bottomPanel, sideBottomPanel);
        viewTab.setNextLayer(viewPayment);

        // DRAW GRAPHICS
        viewTables.drawViewTables();
        
    }
}
