package LayersView;

import javax.swing.JPanel;

import GUIs.buttons.*;
import GUIs.panels.*;

import classes.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;


public class ViewTab extends JPanel{

        private ViewTables previousLayer;
        private ViewPayment nextLayer;
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
            
            addListenersToProductButtons();

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
            sidePanel.createContainerForActiveTab(activeTab, activeTable, "viewtab");

            // drawmethod för att lägga till [ PRODUKTNAMN ] [Amount ] [ - ][ + ] [ Add ]
            // drawmethod för [ Back ] [ Pay ]
            bottomPanel.createAddProductPanel(activeTable);

            // drawmethod för kvittototal och momsberäkning
            sideBottomPanel.drawTabTotal(activeTab);

            resetListeners();
        }

        public void addListenersToProductButtons(){
            
            for (String headline : menuItems.getListOfProducts().keySet()) {
                for (BorderButton button : menuItems.getListOfProducts().get(headline).getButtons()) {
                    button.addActionListener(e -> {
                        chosenAmount = 1;
                        previousClickedButton = clickedButton;
                        clickedButton = button.getText();
                        decideMainPanelLayer();
                    });
                }
            }

            for (BorderButton button : menuItems.getButtonListOfHeadlines()) {
                button.addActionListener(e -> {
                    chosenAmount = 1;
                    previousClickedButton = clickedButton;
                    clickedButton = button.getText();
                    decideMainPanelLayer();
                });
            }
        }

        public MenuItems findItem(String name, String menu){

            if (menuItems.getListOfProducts().containsKey(menu)) {
                for (int i = 0; i < menuItems.getListOfProducts().get(menu).getItems().size(); i++){
                    if (menuItems.getListOfProducts().get(menu).getItems().get(i).getName().equalsIgnoreCase(name))
                        return menuItems.getListOfProducts().get(menu).getItems().get(i);
                }
            }
            
            return null;

        }

        // metod för att avgöra vad som ska göras härnäst
        public void decideMainPanelLayer(){
            if (clickedButton.equalsIgnoreCase("menu")){
                mainPanel.drawMenuOptions(menuItems.getButtonListOfHeadlines(), "menu");
                bottomPanel.createAddProductPanel(activeTable);
                System.out.println("After painting");
            }

            else if (menuItems.getListOfProducts().containsKey(clickedButton)) {
                mainPanel.drawMenuOptions(menuItems.getListOfProducts().get(clickedButton).getButtons(), "");
                bottomPanel.createAddProductPanel(activeTable);
                System.out.println("Submenu created");
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

            if (bottomPanel.getPayButton().getActionListeners().length > 0){
                for (ActionListener al : bottomPanel.getPayButton().getActionListeners()) {
                    bottomPanel.getPayButton().removeActionListener(al);
                }
            }

            if (bottomPanel.getBackButton().getActionListeners().length > 0){
                for (ActionListener al : bottomPanel.getBackButton().getActionListeners()) {
                    bottomPanel.getBackButton().removeActionListener(al);
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
                sidePanel.createContainerForActiveTab(activeTab, activeTable,"viewtab");
                sideBottomPanel.drawTabTotal(activeTab);
            });

            bottomPanel.getPayButton().addActionListener(e -> {

                clickedButton = "menu";
                previousClickedButton = "";
                chosenAmount = 1;

                nextLayer.checkIncomingTab(activeTable);

            });

            bottomPanel.getBackButton().addActionListener(e -> {
                clickedButton = "menu";
                previousClickedButton = "";
                chosenAmount = 1;

                previousLayer.drawViewTables();
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

        public void setNextLayer(ViewPayment nextLayer) {
            this.nextLayer = nextLayer;
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

        public OpenTab getActiveTab() {
            return activeTab;
        }
        public Table getActiveTable() {
            return activeTable;
        }

        public ViewPayment getNextLayer() {
            return nextLayer;
        }

}
