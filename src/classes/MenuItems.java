package classes;

import GUIs.buttons.*;
import GUIs.panels.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuItems {
    private float price;
    private String name;
    private int productId;
    private int amount;
    private SidePanel parentFrame;
    private String typeOfProduct;
    private float taxGroup;
    private int productIdNumberSequence = 1000;

    private ArrayList<MenuItems> listOfEntreeProducts = new ArrayList<MenuItems>();
    private ArrayList<BorderButton> buttonListOfEntreeProducts = new ArrayList<>();

    private ArrayList<MenuItems> listOfCourseProducts = new ArrayList<MenuItems>();
    private ArrayList<BorderButton> buttonListOfCourseProducts = new ArrayList<>();

    private ArrayList<MenuItems> listOfDessertProducts = new ArrayList<MenuItems>();
    private ArrayList<BorderButton> buttonListOfDessertProducts = new ArrayList<>();

    private ArrayList<MenuItems> listOfDrinkProducts = new ArrayList<MenuItems>();
    private ArrayList<BorderButton> buttonListOfDrinkProducts = new ArrayList<>();

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
        /* createEntreesProducts();
        createMainCourseProducts();
        createDessertCourseProducts();
        createDrinkProducts();
        createHeadlines(); */

        createProducts(listOfEntreeProducts, buttonListOfEntreeProducts, "Entrees.txt");
        createProducts(listOfCourseProducts, buttonListOfCourseProducts, "MainCourse.txt");
        createProducts(listOfDessertProducts, buttonListOfDessertProducts, "Desserts.txt");
        createProducts(listOfDrinkProducts, buttonListOfDrinkProducts, "Drinks.txt");

        for (String title : listOfHeadlines){
            System.out.println(title.equalsIgnoreCase("desserts"));
        }

        for (MenuItems product : listOfEntreeProducts){
            System.out.println(product.getName());
        }

        for (MenuItems product : listOfCourseProducts){
            System.out.println(product.getName());
        }

        for (MenuItems product : listOfDessertProducts){
            System.out.println(product.getName());
        }

        for (MenuItems product : listOfDrinkProducts){
            System.out.println(product.getName());
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
    
    private void createProducts(ArrayList<MenuItems> list, ArrayList<BorderButton> listOfButtons, String fileName){

        String productType = fileName.replace(".txt", "");

        try{
            File file = new File("src/files/foodMenu/" + fileName);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String data = scanner.nextLine();

                String[] listOfStrings = data.split(",");

                String name = listOfStrings[0].trim();
                float price = Float.parseFloat(listOfStrings[1].trim());
                float taxGroup = Float.parseFloat(listOfStrings[2].trim());

                list.add(new MenuItems(price, name, 1, productType, taxGroup));
                listOfButtons.add(new BorderButton(name));
            }
            scanner.close();
        }

        catch (FileNotFoundException e){
            System.out.println(fileName + " | File not found!");
        }

        listOfHeadlines.add(productType);
        buttonListOfHeadlines.add(new BorderButton(productType));
    }

    /* private void createEntreesProducts(){
        float[] priceList = {79.00f, 119.00f, 59.00f, 89.00f, 249.00f};
        String[] nameList = {"Gazpacho", "Sardines & Toast", "Caprese Salad", "Oysters n3","Skagen Toast"};
        float[] taxGroupList = {0.12f, 0.12f, 0.12f, 0.12f, 0.12f};

        if (priceList.length == nameList.length) {
            for (int i = 0; i < priceList.length; i++){
                listOfEntreeProducts.add(new MenuItems(priceList[i], nameList[i], 1, "Entree", taxGroupList[i]));
                buttonListOfEntreeProducts.add(new BorderButton(listOfEntreeProducts.get(i).getName()));
            }
        }
    }

    private void createMainCourseProducts(){

        float[] priceList = {39.00f, 78.00f, 93.00f, 123.00f, 189.00f};
        String[] nameList = {"Ricebowl", "Sallad", "Chicken", "Salmon","Meat"};
        float[] taxGroupList = {0.12f, 0.12f, 0.12f, 0.12f, 0.12f};

        if (priceList.length == nameList.length) {
            for (int i = 0; i < priceList.length; i++){
                listOfCourseProducts.add(new MenuItems(priceList[i], nameList[i], 1, "Main", taxGroupList[i]));
                buttonListOfCourseProducts.add(new BorderButton(listOfCourseProducts.get(i).getName()));
            }
        }
    }

    private void createDessertCourseProducts(){
        float[] priceList = {69.00f, 78.00f, 59.00f, 250.00f, 999.00f};
        String[] nameList = {"Ice Cream", "Tiramisu", "Mudcake", "Cheese platter","Goldpainted Chocolate"};
        float[] taxGroupList = {0.12f, 0.12f, 0.12f, 0.12f, 0.12f};

        if (priceList.length == nameList.length) {
            for (int i = 0; i < priceList.length; i++){
                listOfDessertProducts.add(new MenuItems(priceList[i], nameList[i], 1, "Dessert", taxGroupList[i]));
                buttonListOfDessertProducts.add(new BorderButton(listOfDessertProducts.get(i).getName()));
            }
        }
    }

    private void createDrinkProducts(){
        float[] priceList = {24.00f, 33.00f, 39.00f, 29.00f, 55.00f, 129.00f, 89.00f};
        String[] nameList = {"Soda", "Lemonade", "Ice Tea", "Light Soda","Milkshake", "Wine", "Beer"};
        float[] taxGroupList = {0.12f, 0.12f, 0.12f, 0.12f, 0.12f, 0.25f, 0.25f};

        if (priceList.length == nameList.length) {
            for (int i = 0; i < priceList.length; i++){
                listOfDrinkProducts.add(new MenuItems(priceList[i], nameList[i], 1, "Drink",taxGroupList[i]));
                buttonListOfDrinkProducts.add(new BorderButton(listOfDrinkProducts.get(i).getName()));
            }
        }
    }

    private void createHeadlines(){
        String[] headlines = {"Entrees", "Main Courses", "Desserts", "Drinks"};

        for (String headline : headlines) {
            listOfHeadlines.add(headline);
            buttonListOfHeadlines.add(new BorderButton(headline));
        }
    } */

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

    public ArrayList<String> getListOfHeadlines() {
        return listOfHeadlines;
    }

    public ArrayList<BorderButton> getButtonListOfEntreeProducts() {
        return buttonListOfEntreeProducts;
    }

    public ArrayList<BorderButton> getButtonListOfCourseProducts() {
        return buttonListOfCourseProducts;
    }

    public ArrayList<BorderButton> getButtonListOfDessertProducts() {
        return buttonListOfDessertProducts;
    }

    public ArrayList<BorderButton> getButtonListOfDrinkProducts() {
        return buttonListOfDrinkProducts;
    }

    public ArrayList<BorderButton> getButtonListOfHeadlines() {
        return buttonListOfHeadlines;
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

    // --------------- COPY METHODS ----------------------

}
