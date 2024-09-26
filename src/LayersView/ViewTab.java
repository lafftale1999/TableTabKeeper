package LayersView;

import javax.swing.JPanel;

import GUIs.*;

import classes.*;
import java.util.ArrayList;


public class ViewTab extends JPanel{

        private ViewTables previousLayer;
        // nextLayer payment
        private OpenTab activeTab;
        private Table activeTable;
        private String clickedButton;

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
            this.activeTab = activeTable.getActiveTab();
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
        }

        public void drawViewTab(){
            // passa in this som argument för tillgång till det aktuella metoderna
            
            // drawmethod för huvudmenyknappar - MAINPANEL
            // drawmethod för underkategorier - MAINPANEL
            decideNextAction();
            
            // drawmethod för kvittot i sidebar
            sidePanel.createContainerForActiveTab(activeTab, activeTable);

            // drawmethod för att lägga till [ PRODUKTNAMN ] [Amount ] [ - ][ + ] [ Add ]

            // drawmethod för kvittototal och momsberäkning

            // drawmethod för [ Back ] [ Pay ]
        }

        public void addListenersToButtons(ArrayList<BorderButton> list){
            for (BorderButton button : list) {
                button.addActionListener(e -> {
                    System.out.println("You have clicked " + button.getText());
                    clickedButton = button.getText();
                    decideNextAction();
                });
            }
        }

        // metod för att avgöra vad som ska göras härnäst
        public void decideNextAction(){

            if (clickedButton.equalsIgnoreCase("entrees"))
                mainPanel.drawMenuOptions(menuItems.getButtonListOfEntreeProducts());
            
            else if (clickedButton.equalsIgnoreCase("main courses"))
                mainPanel.drawMenuOptions(menuItems.getButtonListOfCourseProducts());

            else if (clickedButton.equalsIgnoreCase("desserts"))
            mainPanel.drawMenuOptions(menuItems.getButtonListOfDessertProducts());
        
            else if (clickedButton.equalsIgnoreCase("drinks"))
            mainPanel.drawMenuOptions(menuItems.getButtonListOfDrinkProducts());

            else
                mainPanel.drawMenuOptions(menuItems.getButtonListOfHeadlines());
            
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
