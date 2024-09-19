package classes;
public class Product {

    private float price;
    private String name;
    private int productId;
    private int amount;
    static int productIdNumberSequence = 1001;

    public Product(float price, String name, int amount){
        setPrice(price);
        setName(name);
        setAmount(amount);
        setProductId(productIdNumberSequence);
        productIdNumberSequence++;
    }

    // GETTERS
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
     
    // SETTERS
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

    // METHODS
    public static Product[] createMainMenu(){

        float[] priceList = {39.00f, 78.00f, 93.00f, 123.00f, 189.00f};
        String[] nameList = {"Ricebowl", "Sallad", "Chicken", "Salmon","Meat"};
        Product[] mainMenu = new Product[priceList.length];

        if (priceList.length == nameList.length) {
            for (int i = 0; i < priceList.length; i++){
                mainMenu[i] = new Product(priceList[i], nameList[i], 1);
            }
        }

        return mainMenu;
    }
}
