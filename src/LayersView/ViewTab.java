package LayersView;

import javax.swing.JPanel;

import GUIs.buttons.*;
import GUIs.panels.*;

import classes.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;


public class ViewTab extends JPanel{

        private ViewTables previousLayer;
        // nextLayer payment
        private OpenTab activeTab;
        private Table activeTable;
        private String clickedButton = "menu";
        private String previousClickedButton = "";
        private int chosenAmount = 1;

        // panels
        MainPanel mainPanel;
        SidePanel sidePanel;
        SideBottomPanel sideBottomPanel;
        BottomPanel bottomPanel;

        private MenuItems menuItems;

        public ViewTab( MenuItems menuItems, 
                        Table activeTable, 
                        ViewTables previousLayer, 
                        MainPanel mainPanel, 
                        SidePanel sidePanel, 
                        SideBottomPanel sideBottomPanel, 
                        BottomPanel bottomPanel){
           
            setMenuItems(menuItems);
            setActiveTable(activeTable);
            setPreviousLayer(previousLayer);
            setMainPanel(mainPanel);
            setSidePanel(sidePanel);
            setSideBottomPanel(sideBottomPanel);
            setBottomPanel(bottomPanel);

            addListenersToButtons(menuItems.getButtonListOfEntreeProducts());
            addListenersToButtons(menuItems.getButtonListOfCourseProducts());
            addListenersToButtons(menuItems.getButtonListOfDessertProducts());
            addListenersToButtons(menuItems.getButtonListOfDrinkProducts());
            addListenersToButtons(menuItems.getButtonListOfHeadlines());
            
            bottomPanel.getBackButton().addActionListener(e -> {
                clickedButton = "menu";
                previousClickedButton = "";
                chosenAmount = 1;


                previousLayer.drawViewTables();
            });

            bottomPanel.getPayButton().addActionListener(e -> {
                System.out.println("You have paid!");
            });

            mainPanel.getBackMenuButton().addActionListener(e -> {
                clickedButton = "menu";
                previousClickedButton = "";
                decideMainPanelLayer();
            });

        }
        
        

        public void drawViewTab(){
            
            // drawmethod för huvudmenyknappar - MAINPANEL
            // drawmethod för underkategorier - MAINPANEL
            decideMainPanelLayer();
            
            // drawmethod för kvittot i sidePanel
            sidePanel.createContainerForActiveTab(activeTab, activeTable);

            // drawmethod för att lägga till [ PRODUKTNAMN ] [Amount ] [ - ][ + ] [ Add ]
            // drawmethod för [ Back ] [ Pay ]
            bottomPanel.createAddProductPanel(activeTable);

            // drawmethod för kvittototal och momsberäkning
            sideBottomPanel.drawTabTotal(activeTab);

        }

        public void addListenersToButtons(ArrayList<BorderButton> list){
            for (BorderButton button : list) {
                button.addActionListener(e -> {
                    chosenAmount = 1;
                    previousClickedButton = clickedButton;
                    clickedButton = button.getText();
                    decideMainPanelLayer();
                });

                System.out.println(button.getText() + " has been added");
            }
        }

        public MenuItems findItem(String name, String menu){

            if (menu.equalsIgnoreCase("entrees")) {
                for (MenuItems product : menuItems.getListOfEntreeProducts()){
                    if (name.equalsIgnoreCase(product.getName())){
                        return product;
                    }
                }
            }

            else if(menu.equalsIgnoreCase("maincourse")) {
                for (MenuItems product : menuItems.getListOfCourseProducts()){
                    if (name.equalsIgnoreCase(product.getName())){
                        return product;
                    }
                }
            }

            else if(menu.equalsIgnoreCase("desserts")) {
                for (MenuItems product : menuItems.getListOfDessertProducts()){
                    if (name.equalsIgnoreCase(product.getName())){
                        return product;
                    }
                }
            }

            else if(menu.equalsIgnoreCase("drinks")) {
                for (MenuItems product : menuItems.getListOfDrinksProducts()){
                    if (name.equalsIgnoreCase(product.getName())){
                        return product;
                    }
                }
            }
            
            return null;

        }

        // metod för att avgöra vad som ska göras härnäst
        public void decideMainPanelLayer(){

            if (clickedButton.equalsIgnoreCase("entrees"))
                mainPanel.drawMenuOptions(menuItems.getButtonListOfEntreeProducts(),"");
            
            else if (clickedButton.equalsIgnoreCase("maincourse"))
                mainPanel.drawMenuOptions(menuItems.getButtonListOfCourseProducts(), "");

            else if (clickedButton.equalsIgnoreCase("desserts"))
                mainPanel.drawMenuOptions(menuItems.getButtonListOfDessertProducts(), "");
        
            else if (clickedButton.equalsIgnoreCase("drinks"))
                mainPanel.drawMenuOptions(menuItems.getButtonListOfDrinkProducts(), "");

            else if (clickedButton.equalsIgnoreCase("menu")){
                System.out.println("-----------------ABOUT TO DRAW THE MENU------------------");
                System.out.println(menuItems.getButtonListOfHeadlines());
                mainPanel.drawMenuOptions(menuItems.getButtonListOfHeadlines(), "menu");
                System.out.println("-----------------MENU HAS BEEN DRAWN------------------");
            }
                

            // If the user have clicked an item that they want to add
            else
                bottomPanel.createAddProductPanel(activeTable, clickedButton, chosenAmount);      
        }

        public void resetListeners() {
            
            // reset listeners
            if (bottomPanel.getIncreaseButton().getActionListeners().length > 0){
                for (ActionListener al : bottomPanel.getIncreaseButton().getActionListeners()) {
                    bottomPanel.getIncreaseButton().removeActionListener(al);
                }
            }

            if (bottomPanel.getDecreaseButton().getActionListeners().length > 0){
                for (ActionListener al : bottomPanel.getDecreaseButton().getActionListeners()) {
                    bottomPanel.getDecreaseButton().removeActionListener(al);
                }
            }

            if (bottomPanel.getAddButton().getActionListeners().length > 0){
                for (ActionListener al : bottomPanel.getAddButton().getActionListeners()) {
                    bottomPanel.getAddButton().removeActionListener(al);
                }
            }

            // add new listeners
            bottomPanel.getIncreaseButton().addActionListener(e -> {
                if (clickedButton.length() > 0 && previousClickedButton.length() > 0) {
                    chosenAmount++;
                    bottomPanel.createAddProductPanel(activeTable, clickedButton, chosenAmount);
                }
            });

            bottomPanel.getDecreaseButton().addActionListener(e -> {
                if (clickedButton.length() > 0 && previousClickedButton.length() > 0 && chosenAmount > 1) {
                    chosenAmount--;
                    bottomPanel.createAddProductPanel(activeTable, clickedButton, chosenAmount);
                }
            });

            bottomPanel.getAddButton().addActionListener(e -> {
                activeTab.addMenuItem(findItem(clickedButton, previousClickedButton), chosenAmount);
                clickedButton = "menu";
                previousClickedButton = "";
                chosenAmount = 1;

                decideMainPanelLayer();
                bottomPanel.createAddProductPanel(activeTable);
                sidePanel.createContainerForActiveTab(activeTab, activeTable);
                sideBottomPanel.drawTabTotal(activeTab);
                System.out.println("Tab when calling draw from viewTab: " + activeTab.getTabTotal() + "kr | " + activeTab.getListOfMenuItems().size()); 
            });
        }
           
        public void setActiveTab(OpenTab activeTab) {
            this.activeTab = activeTab;
        }

        public void setActiveTable(Table activeTable) {
            this.activeTable = activeTable;
        }

        public void setMenuItems(MenuItems menuItems) {
            this.menuItems = menuItems;
        }

        public void setPreviousLayer(ViewTables previousLayer) {
            this.previousLayer = previousLayer;
        }

        public void setMainPanel(MainPanel mainPanel) {
            this.mainPanel = mainPanel;
        }

        public void setSidePanel(SidePanel sidePanel) {
            this.sidePanel = sidePanel;
        }
        
        public void setSideBottomPanel(SideBottomPanel sideBottomPanel) {
            this.sideBottomPanel = sideBottomPanel;
        }

        public void setBottomPanel(BottomPanel bottomPanel) {
            this.bottomPanel = bottomPanel;
        }

        public MainPanel getMainPanel() {
            return mainPanel;
        }

        public SidePanel getSidePanel() {
            return sidePanel;
        }

        public SideBottomPanel getSideBottomPanel() {
            return sideBottomPanel;
        }

        public BottomPanel getBottomPanel() {
            return bottomPanel;
        }

}
