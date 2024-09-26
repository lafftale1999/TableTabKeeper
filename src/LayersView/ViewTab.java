package LayersView;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import GUIs.*;

import classes.*;
import java.util.ArrayList;


public class ViewTab extends JPanel{

        private ViewTables previousLayer;
        // nextLayer payment
        private OpenTab activeTab;
        private Table activeTable;
        private ArrayList<BorderButton> listOfDessertButtons = new ArrayList<>();
        private ArrayList<BorderButton> listOfMainCourseButtons = new ArrayList<>();
        private ArrayList<BorderButton> listOfEntreesButtons = new ArrayList<>();
        private ArrayList<BorderButton> listOfDrinksButtons = new ArrayList<>();
        private ButtonGroup listOfButtonGroups = new ButtonGroup();
        private MenuItems menuItems;

        public ViewTab(MenuItems menuItems, Table activeTable){
            setMenuItems(menuItems);
            setActiveTable(activeTable);
        }

        public void createListOfButtons(){
            
            for (int i = 0; i < menuItems.getListOfEntreeProducts().size(); i++){
                list.add(new BorderButton(menuItems.getListOfEntreeProducts().get(i).getName()));
                listOfButtonGroups.add(list.get(i));
            }

            for (BorderButton button : list) {
                button.addActionListener(e -> {
                    System.out.println("You have clicked " + button.getName());
                });
            }         

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

        public void createHeaderMenuButtons(){

        }
}
