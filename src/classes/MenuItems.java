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
        createProducts(listOfEntreeProducts, buttonListOfEntreeProducts, "Entrees.txt");
        createProducts(listOfCourseProducts, buttonListOfCourseProducts, "MainCourse.txt");
        createProducts(listOfDessertProducts, buttonListOfDessertProducts, "Desserts.txt");
        createProducts(listOfDrinkProducts, buttonListOfDrinkProducts, "Drinks.txt");
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
