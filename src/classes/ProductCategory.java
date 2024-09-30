package classes;

import GUIs.buttons.*;
import java.util.ArrayList;

public class ProductCategory {

    private ArrayList<MenuItems> items;
    private ArrayList<BorderButton> buttons;

    ProductCategory(ArrayList<MenuItems> items, ArrayList<BorderButton> buttons){
        setButtons(buttons);
        setItems(items);
    }

    public ArrayList<MenuItems> getItems() {
        return items;
    }

    public ArrayList<BorderButton> getButtons() {
        return buttons;
    }

    public void setItems(ArrayList<MenuItems> items) {
        this.items = items;
    }

    public void setButtons(ArrayList<BorderButton> buttons) {
        this.buttons = buttons;
    }

}
