package classes;

import java.util.ArrayList;

public class OpenTab {

    private MenuItems menuItems;
    private ArrayList<MenuItems> listOfMenuItems = new ArrayList<MenuItems>();
    private int tabId = 0;
    private float tabTotal = 0;
    private boolean isPaid = false;
    
    public OpenTab(Table table, MenuItems menuItems){
        tabId++;
        setMenuItems(menuItems);
        setTabId(tabId);
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

    public float[] calculateTaxes(){

        float[] listOfTaxes = new float[2];
    
        for (MenuItems product : listOfMenuItems){
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
