package classes;

import GUIs.SidePanel;
import java.util.ArrayList;

public class MenuItems {

    private float price;
    private String name;
    private int productId;
    private int amount;
    private SidePanel parentFrame;
    private String typeOfProduct;
    private static int productIdNumberSequence = 1001;
    private ArrayList<MenuItems> listOfEntreeProducts = new ArrayList<MenuItems>();
    private ArrayList<MenuItems> listOfCourseProducts = new ArrayList<MenuItems>();
    private ArrayList<MenuItems> listOfDessertProducts = new ArrayList<MenuItems>();
    private ArrayList<MenuItems> listOfDrinkProducts = new ArrayList<MenuItems>();

    private MenuItems(float price, String name, int amount, String typeOfProduct){
        setPrice(price);
        setName(name);
        setAmount(amount);
        setTypeOfProduct(typeOfProduct);
        setProductId(productIdNumberSequence);
        productIdNumberSequence++;
    }

    public MenuItems(){
        createEntreesProducts();
        createMainCourseProducts();
        createDessertCourseProducts();
        createDrinkProducts();
    }

    public MenuItems(String name, float price, int amount, String typeOfProduct, int productId){
        setName(name);
        setPrice(price);
        setAmount(amount);
        setTypeOfProduct(typeOfProduct);
        setProductId(productId);
    }

    // -------------- GETTERS --------------

    public int getAmount() {
        return amount;
    }
    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public int getProductId() {
        return productId;
    }
    public static int getProductIdNumberSequence() {
        return productIdNumberSequence;
    }

    public SidePanel getParentFrame(){
        return this.parentFrame;
    }

    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public ArrayList<MenuItems> getListOfCourseProducts(){
        return listOfCourseProducts;
    }

    public ArrayList<MenuItems> getListOfEntreeProducts(){
        return listOfEntreeProducts;
    }

    public ArrayList<MenuItems> getListOfDessertProducts(){
        return listOfDessertProducts;
    }

    public ArrayList<MenuItems> getListOfDrinksProducts(){
        return listOfDrinkProducts;
    }

    // -------------- SETTERS --------------

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setParentFrame(SidePanel parentFrame) {
        this.parentFrame = parentFrame;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    // -------------- METHODS --------------
    
    private void createEntreesProducts(){
            float[] priceList = {79.00f, 119.00f, 59.00f, 89.00f, 249.00f};
            String[] nameList = {"Gazpacho", "Sardines & Toast", "Caprese Salad", "Oysters n3","Skagen Toast"};
    
            if (priceList.length == nameList.length) {
                for (int i = 0; i < priceList.length; i++){
                    listOfEntreeProducts.add(new MenuItems(priceList[i], nameList[i], 1, "Entree"));
                }
            }

    }
    
    private void createMainCourseProducts(){

        float[] priceList = {39.00f, 78.00f, 93.00f, 123.00f, 189.00f};
        String[] nameList = {"Ricebowl", "Sallad", "Chicken", "Salmon","Meat"};

        if (priceList.length == nameList.length) {
            for (int i = 0; i < priceList.length; i++){
                listOfCourseProducts.add(new MenuItems(priceList[i], nameList[i], 1, "Main"));
            }
        }
    }

    private void createDessertCourseProducts(){
        float[] priceList = {69.00f, 78.00f, 59.00f, 250.00f, 999.00f};
        String[] nameList = {"Ice Cream", "Tiramisu", "Mudcake", "Cheese platter","Goldpainted Chocolate"};

        if (priceList.length == nameList.length) {
            for (int i = 0; i < priceList.length; i++){
                listOfDessertProducts.add(new MenuItems(priceList[i], nameList[i], 1, "Dessert"));
            }
        }
    }

    private void createDrinkProducts(){
        float[] priceList = {24.00f, 33.00f, 39.00f, 29.00f, 55.00f};
        String[] nameList = {"Soda", "Lemonade", "Ice Tea", "Light Soda","Milkshake"};

        if (priceList.length == nameList.length) {
            for (int i = 0; i < priceList.length; i++){
                listOfDrinkProducts.add(new MenuItems(priceList[i], nameList[i], 1, "Drink"));
            }
        }
    }
}
