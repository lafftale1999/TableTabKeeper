package classes;

import GUIs.SidePanel;

public class Product {

    private float price;
    private String name;
    private int productId;
    private int amount;
    private SidePanel parentFrame;
    private static int productIdNumberSequence = 1001;
    private Product[] listOfEntreeProducts = new Product[5];
    private Product[] listOfCourseProducts = new Product[5];
    private Product[] listOfDessertProducts = new Product[5];
    private Product[] listOfDrinkProducts = new Product[5];

    public Product(float price, String name, int amount){
        setPrice(price);
        setName(name);
        setAmount(amount);
        setProductId(productIdNumberSequence);
        productIdNumberSequence++;
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

    // -------------- METHODS --------------
    
    public void createEntreesProducts(){
            float[] priceList = {79.00f, 119.00f, 59.00f, 89.00f, 249.00f};
            String[] nameList = {"Gazpacho", "Sardines & Toast", "Caprese Salad", "Oysters n3","Skagen Toast"};
    
            if (priceList.length == nameList.length) {
                for (int i = 0; i < priceList.length; i++){
                    listOfEntreeProducts[i] = new Product(priceList[i], nameList[i], 1);
                }
            }

    }
    
    public void createMainCourseProducts(){

        float[] priceList = {39.00f, 78.00f, 93.00f, 123.00f, 189.00f};
        String[] nameList = {"Ricebowl", "Sallad", "Chicken", "Salmon","Meat"};

        if (priceList.length == nameList.length) {
            for (int i = 0; i < priceList.length; i++){
                listOfCourseProducts[i] = new Product(priceList[i], nameList[i], 1);
            }
        }
    }

    public void createDessertCourseProducts(){
        float[] priceList = {69.00f, 78.00f, 59.00f, 250.00f, 999.00f};
        String[] nameList = {"Ice Cream", "Tiramisu", "Mudcake", "Cheese platter","Goldpainted Chocolate"};

        if (priceList.length == nameList.length) {
            for (int i = 0; i < priceList.length; i++){
                listOfDessertProducts[i] = new Product(priceList[i], nameList[i], 1);
            }
        }
    }

    public void createDrinkProducts(){
        float[] priceList = {24.00f, 33.00f, 39.00f, 29.00f, 55.00f};
        String[] nameList = {"Soda", "Lemonade", "Ice Tea", "Light Soda","Milkshake"};

        if (priceList.length == nameList.length) {
            for (int i = 0; i < priceList.length; i++){
                listOfDrinkProducts[i] = new Product(priceList[i], nameList[i], 1);
            }
        }
    }
}
