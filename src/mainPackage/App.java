package mainPackage;
import java.text.NumberFormat;

import GUIs.MyFrame;
import GUIs.MyPanel;
import classes.Product;

public class App {
    public static void main(String[] args) throws Exception {
        MyFrame frame = new MyFrame();

        // creates the mainMenu list with Product objects
        Product[] mainMenu = Product.createMainMenu();

        // backgrounds colors for our panels
        int[] mainPanelColor = {233,233,233};
        int[] sidePanelColor = {210,210,210};
        int[] bottomPanelColor = {178,178,178};
        
        // intitializes an array of panels
        MyPanel[] arrayOfPanels = new MyPanel[4];
        
        // mainpanel
        arrayOfPanels[0] = new MyPanel(0,0,700,550,mainPanelColor, true);
        
        // sidepanel should be scrollable since it should be able to receive transactions out of bounds.
        arrayOfPanels[1] = new MyPanel(arrayOfPanels[0].getWidth(), 0, frame.getWidth() - arrayOfPanels[0].getWidth(), 550, sidePanelColor, true);
        
        // sidebottompanel
        arrayOfPanels[2] = new MyPanel(arrayOfPanels[1].getX(), arrayOfPanels[1].getHeight(), arrayOfPanels[1].getWidth(), frame.getHeight() - arrayOfPanels[1].getHeight(), bottomPanelColor, true);
        
        // bottompanel
        arrayOfPanels[3] = new MyPanel(0, arrayOfPanels[0].getHeight(), frame.getWidth() - arrayOfPanels[1].getWidth(), frame.getHeight() - arrayOfPanels[0].getHeight(), bottomPanelColor, true);

        // adds all panels to the frame
        for (MyPanel panel : arrayOfPanels) {
            frame.add(panel);
        }

        for (Product food : mainMenu) {
            System.out.println(food.getName() + " " + NumberFormat.getCurrencyInstance().format(food.getPrice()));
        }

    }
}
