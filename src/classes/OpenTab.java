package classes;

import java.util.ArrayList;

public class OpenTab {

    private MenuItems menuItems;
    private ArrayList<MenuItems> listOfMenuItems = new ArrayList<MenuItems>();
    private int tabId = 0;
    private float tabTotal = 0;
    private static int tabCounter = 0;
    
    public OpenTab(Table table, MenuItems menuItems){
        tabCounter++;
        setMenuItems(menuItems);
        setTabId(tabCounter);
        table.setActiveTab(this);
        table.setHasTab(true);
    }

    // ---------- METHODS ---------------
    public void updateTabTotal(float amount){
        /**Updates tabtotal
         * 
         * @param float amount
         */

        this.tabTotal += amount;
    }

    public void addMenuItem(MenuItems product, int amount){
        /**Adds menuItem to our listOfMenuItems
         * 
         * @param MenuItems product decides which product
         * @param int amount decides how many
         */
        
        MenuItems newMenuItem = new MenuItems(product.getName(), product.getPrice(), amount, product.getTypeOfProduct(), product.getProductId(), product.getTaxGroup());
        listOfMenuItems.add(newMenuItem);
        updateTabTotal(newMenuItem.getAmount() * newMenuItem.getPrice());
    }

    public void removeMenuItem(MenuItems itemToRemove){
        /**Remove item from listOfMenuItems
         * 
         * @param MenuItems itemToRemove
         */

        // for loop with index
        for (int i = 0; i < listOfMenuItems.size(); i++){
            
            // check if the objects matches
            if (itemToRemove.equals(listOfMenuItems.get(i))){
                
                // checks if they should only remove 1 amount
                if (listOfMenuItems.get(i).getAmount() > 1) {
                    tabTotal -= listOfMenuItems.get(i).getPrice();
                    listOfMenuItems.get(i).setAmount(listOfMenuItems.get(i).getAmount() - 1);
                }

                // or remove the whole product
                else {
                    tabTotal -= listOfMenuItems.get(i).getPrice();
                    listOfMenuItems.remove(i);
                }
            }
        }

    }

    public float[] calculateTaxes(){
        /**Calculate the taxes of each item in the active tab
         * 
         * return float[] list of taxamount [0] = 12% | [1] = 25%
         */

        // create temporary list
        float[] listOfTaxes = new float[2];
        
        // loop through each item
        for (MenuItems product : this.listOfMenuItems){
            
            // 25%
            if (product.getTaxGroup() == 0.25f){
                listOfTaxes[1] += (product.getPrice() * product.getAmount()) * 0.25f;
                System.out.println(listOfTaxes[1]);
            }
            
            // 12%
            else {
                listOfTaxes[0] += (product.getPrice() * product.getAmount()) * 0.12f;
                System.out.println(listOfTaxes[0]);
            }
        }

        return listOfTaxes;
    }

    // ---------- GETTERS ---------------
    public ArrayList<MenuItems> getListOfMenuItems() {
        return listOfMenuItems;
    }

     public int getTabId() {
        return tabId;
    }

    public float getTabTotal() {
        return tabTotal;
    }

    public MenuItems getMenuItems() {
        return menuItems;
    }

    public static int getTabCounter() {
        return tabCounter;
    }

    // ---------- SETTERS ---------------
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

    public static void setTabCounter(int tabCounter) {
        OpenTab.tabCounter = tabCounter;
    }
}
