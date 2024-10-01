package LayersView;

import javax.swing.JPanel;

import GUIs.buttons.*;
import GUIs.panels.*;

import classes.*;
import java.awt.event.ActionListener;


public class ViewTab extends JPanel{

        // attributes
        private ViewTables previousLayer;
        private ViewPayment nextLayer;
        private OpenTab activeTab;
        private Table activeTable;
        private String clickedButton = "menu";
        private String previousClickedButton = "";
        private int chosenAmount = 1;
        private MenuItems menuItems;

        // Panels
        private MainPanel mainPanel;
        private SidePanel sidePanel;
        private SideBottomPanel sideBottomPanel;
        private BottomPanel bottomPanel;

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
            
            // adds listeners to all buttons in mainpanel
            addListenersToProductButtons();

            // and to the back button
            mainPanel.getBackMenuButton().addActionListener(e -> {
                clickedButton = "menu";
                previousClickedButton = "";
                decideMainPanelLayer();
            });
        }
        
        // ----------------- METHODS ------------------
        public void drawViewTab(){
            /**Draws out the ViewTab */
            
            // using clickedButton and previousClickedButton to decide which layer to show in mainPanel
            decideMainPanelLayer();
            
            // shows our tabtotal on the side
            sidePanel.createContainerForActiveTab(activeTab, activeTable, "viewtab");

            // create bottom panel
            bottomPanel.createAddProductPanel(activeTable, "", -1);

            // create tabtotal for side bottom
            sideBottomPanel.drawTabTotal(activeTab);

            // reset all listeners
            resetListeners();
        }
        
        public void decideMainPanelLayer(){
            /**By using clickedButton and previousClickedButton we decide which layer of the menu
             * we are navigating in
             */

            // if "menu" we are in TOPLAYER
            if (clickedButton.equalsIgnoreCase("menu")){
                mainPanel.drawMenuOptions(menuItems.getButtonListOfHeadlines(), "menu");
                bottomPanel.createAddProductPanel(activeTable, "", -1);
            }

            // if clickedButton is not "menu", we are in a SUBMENU
            else if (menuItems.getListOfProducts().containsKey(clickedButton)) {
                mainPanel.drawMenuOptions(menuItems.getListOfProducts().get(clickedButton).getButtons(), "");
                bottomPanel.createAddProductPanel(activeTable, "", -1);
            }
 
            // If none of the above, the user has found the correct item
            else
                bottomPanel.createAddProductPanel(activeTable, clickedButton, chosenAmount);      
        }

        public MenuItems findItem(String name, String menu){
            /**Looks up item in the HashMap with a failsafe. If the user changes its mind
             * during choosing item we have the else statement to catch it.
            */

            // checks previousClicked button for key and then name for value in the list
            if (menuItems.getListOfProducts().containsKey(menu)) {
                for (int i = 0; i < menuItems.getListOfProducts().get(menu).getItems().size(); i++){
                    if (menuItems.getListOfProducts().get(menu).getItems().get(i).getName().equalsIgnoreCase(name))
                        return menuItems.getListOfProducts().get(menu).getItems().get(i);
                }
            }

            // if previousClicked button doesnt match anything
            else {
                for (String key : menuItems.getListOfProducts().keySet()){
                    for (MenuItems item : menuItems.getListOfProducts().get(key).getItems()) {
                        if (name.equalsIgnoreCase(item.getName())) {
                            return item;
                        }
                    }
                }
            }
            
            return null;
        }

        // ----------------- LISTENERS ------------------
        public void addListenersToProductButtons(){
            /** Add listeners to our buttons shown in the main panel*/

            // TOPMENU
            for (BorderButton button : menuItems.getButtonListOfHeadlines()) {
                button.addActionListener(e -> {
                    chosenAmount = 1;
                    previousClickedButton = clickedButton;
                    clickedButton = button.getText();
                    decideMainPanelLayer();
                });
            }

            // SUBMENUS
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
            // ++
            bottomPanel.getIncreaseButton().addActionListener(e -> {
                if (clickedButton.length() > 0 && previousClickedButton.length() > 0) {
                    chosenAmount++;
                    bottomPanel.createAddProductPanel(activeTable, clickedButton, chosenAmount);
                }
            });

            // --
            bottomPanel.getDecreaseButton().addActionListener(e -> {
                if (clickedButton.length() > 0 && previousClickedButton.length() > 0 && chosenAmount > 1) {
                    chosenAmount--;
                    bottomPanel.createAddProductPanel(activeTable, clickedButton, chosenAmount);
                }
            });

            // ADD
            bottomPanel.getAddButton().addActionListener(e -> {

                activeTab.addMenuItem(findItem(clickedButton, previousClickedButton), chosenAmount);
                clickedButton = "menu";
                previousClickedButton = "";
                chosenAmount = 1;

                decideMainPanelLayer();
                bottomPanel.createAddProductPanel(activeTable,"",-1);
                sidePanel.createContainerForActiveTab(activeTab, activeTable,"viewtab");
                sideBottomPanel.drawTabTotal(activeTab);
            });

            // PAY / CLOSE
            bottomPanel.getPayButton().addActionListener(e -> {

                clickedButton = "menu";
                previousClickedButton = "";
                chosenAmount = 1;

                nextLayer.checkIncomingTab(activeTable);

            });

            // GO BACK
            bottomPanel.getBackButton().addActionListener(e -> {
                clickedButton = "menu";
                previousClickedButton = "";
                chosenAmount = 1;

                previousLayer.drawViewTables();
            });
        }
        
        // ----------------- SETTERS ------------------
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

        // ----------------- GETTERS ------------------
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
