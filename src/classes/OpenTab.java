package classes;

import java.awt.Menu;
import java.util.ArrayList;

public class OpenTab {

    private MenuItems menuItems;
    private ArrayList<MenuItems> listOfMenuItems = new ArrayList<MenuItems>();
    private int tabId = 0;
    private float tabTotal = 0;
    private boolean isPaid = false;
    private static int tabCounter = 0;
    
    public OpenTab(Table table, MenuItems menuItems){
        tabCounter++;
        setMenuItems(menuItems);
        setTabId(tabCounter);
        table.setActiveTab(this);
        table.setHasTab(true);
    }

    public void updateTabTotal(float amount){
        this.tabTotal += amount;
    }

    // KOLLA OM DENNA BLIR ETT NYTT OBJEKT ELLER OM DET PEKAR MOT SAMMA.
    public void addMenuItem(MenuItems product, int amount){
        MenuItems newMenuItem = new MenuItems(product.getName(), product.getPrice(), amount, product.getTypeOfProduct(), product.getProductId(), product.getTaxGroup());
        listOfMenuItems.add(newMenuItem);
        updateTabTotal(newMenuItem.getAmount() * newMenuItem.getPrice());
    }

    public void removeMenuItem(MenuItems itemToRemove){

        for (int i = 0; i < listOfMenuItems.size(); i++){
            if (itemToRemove.equals(listOfMenuItems.get(i))){
                if (listOfMenuItems.get(i).getAmount() > 1) {
                    listOfMenuItems.get(i).setAmount(listOfMenuItems.get(i).getAmount() - 1);
                }

                else {
                    listOfMenuItems.remove(i);
                }
            }
        }

    }

    public float[] calculateTaxes(){

        float[] listOfTaxes = new float[2];
    
        for (MenuItems product : this.listOfMenuItems){
            if (product.getTaxGroup() == 0.25f){
                listOfTaxes[1] += (product.getPrice() * product.getAmount()) * 0.25f;
            }
        
            else {
                listOfTaxes[0] = (product.getPrice() * product.getAmount()) * 0.12f;
            }
        }

        return listOfTaxes;
    }

    public ArrayList<MenuItems> getListOfMenuItems() {
        return listOfMenuItems;
    }


    // ----------GETTERS---------------
     public int getTabId() {
        return tabId;
    }

    public float getTabTotal() {
        return tabTotal;
    }

    public MenuItems getMenuItems() {
        return menuItems;
    }

    // ----------SETTERS---------------
    public void setListOfMenuItems(ArrayList<MenuItems> listOfMenuItems) {
        this.listOfMenuItems = listOfMenuItems;
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }

    public void setTabTotal(float tabTotal) {
        this.tabTotal = tabTotal;
    }

    public void setMenuItems(MenuItems menuItems) {
        this.menuItems = menuItems;
    }
}
