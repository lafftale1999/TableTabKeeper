package classes;

import GUIs.buttons.*;
import GUIs.panels.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class MenuItems {
    private float price;
    private String name;
    private int productId;
    private int amount;
    private SidePanel parentFrame;
    private String typeOfProduct;
    private float taxGroup;
    private int productIdNumberSequence = 1000;
    private String[] menuFilePaths = {"Entrees.txt", "MainCourse.txt", "Desserts.txt", "Drinks.txt"};
    
    private HashMap<String, ProductCategory> listOfProducts = new HashMap<>();

    private ArrayList<String> listOfHeadlines = new ArrayList<>();
    private ArrayList<BorderButton> buttonListOfHeadlines = new ArrayList<>();

    private MenuItems(float price, String name, int amount, String typeOfProduct, float taxGroup){
        productIdNumberSequence++;
        setPrice(price);
        setName(name);
        setAmount(amount);
        setTypeOfProduct(typeOfProduct);
        setProductId(productIdNumberSequence);
        setTaxGroup(taxGroup);
    }

    public MenuItems(){
        for (String category : menuFilePaths) {
            createProducts(listOfProducts, category);
        }
    }

    public MenuItems(String name, float price, int amount, String typeOfProduct, int productId, float taxGroup){
        setName(name);
        setPrice(price);
        setAmount(amount);
        setTypeOfProduct(typeOfProduct);
        setProductId(productId);
        setTaxGroup(taxGroup);
    }

    // -------------- METHODS --------------
    
    private void createProducts(HashMap<String,ProductCategory> listOfProducts, String fileName){

        String productType = fileName.replace(".txt", "");

        ArrayList<MenuItems> tempProductList = new ArrayList<>();
        ArrayList<BorderButton> tempButtonList = new ArrayList<>();

        try{
            File file = new File("src/files/foodMenu/" + fileName);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String data = scanner.nextLine();

                String[] listOfStrings = data.split(",");

                String name = listOfStrings[0].trim();
                float price = Float.parseFloat(listOfStrings[1].trim());
                float taxGroup = Float.parseFloat(listOfStrings[2].trim());
                tempProductList.add(new MenuItems(price, name, 1, productType, taxGroup));
                tempButtonList.add(new BorderButton(name));
            }

            scanner.close();

        }

        catch (FileNotFoundException e){
            System.out.println(fileName + " | File not found!");
        }

        ProductCategory newCategory = new ProductCategory(tempProductList, tempButtonList);

        listOfProducts.put(productType, newCategory);
        listOfHeadlines.add(productType);
        buttonListOfHeadlines.add(new BorderButton(productType));

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

    public int getProductIdNumberSequence() {
        return productIdNumberSequence;
    }

    public SidePanel getParentFrame(){
        return this.parentFrame;
    }

    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public float getTaxGroup() {
        return taxGroup;
    }

    public ArrayList<BorderButton> getButtonListOfHeadlines() {
        return buttonListOfHeadlines;
    }

    public ArrayList<String> getListOfHeadlines() {
        return listOfHeadlines;
    }

    public HashMap<String, ProductCategory> getListOfProducts() {
        return listOfProducts;
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

    public void setTaxGroup(float taxGroup) {
        this.taxGroup = taxGroup;
    }

    public void setListOfProducts(HashMap<String, ProductCategory> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public void setButtonListOfHeadlines(ArrayList<BorderButton> buttonListOfHeadlines) {
        this.buttonListOfHeadlines = buttonListOfHeadlines;
    }

    public void setListOfHeadlines(ArrayList<String> listOfHeadlines) {
        this.listOfHeadlines = listOfHeadlines;
    }

    // --------------- COPY METHODS ----------------------

}
