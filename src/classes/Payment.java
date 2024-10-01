package classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import GUIs.buttons.BorderButton;

public class Payment {
    
    private OpenTab activeTab;
    private Table activeTable;
    private String paymentMethod;
    private String[] paymentOptions = {"Cash", "Card", "Giftcard"};
    private int paymentId;
    private String todaysDate;

    ArrayList<BorderButton> buttonsListOfPaymentOptions = new ArrayList<>();

    public Payment(OpenTab activeTab, Table activeTable){

        setActiveTab(activeTab);
        setActiveTable(activeTable);
        setPaymentId(activeTab.getTabId());
        setTodaysDate();
        
        for (String option : paymentOptions) {
            buttonsListOfPaymentOptions.add(new BorderButton(option));
        }
    }

    // ---------- GETTERS ---------------
    public OpenTab getActiveTab() {
        return activeTab;
    }

    public Table getActiveTable() {
        return activeTable;
    }

    public ArrayList<BorderButton> getButtonsListOfPaymentOptions() {
        return buttonsListOfPaymentOptions;
    }

    public String[] getPaymentOptions() {
        return paymentOptions;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public String getTodaysDate() {
        return todaysDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    // ---------- SETTERS ---------------
    public void setActiveTab(OpenTab activeTab) {
        this.activeTab = activeTab;
    }

    public void setActiveTable(Table activeTable) {
        this.activeTable = activeTable;
    }

    public void setButtonsListOfPaymentOptions(ArrayList<BorderButton> buttonsListOfPaymentOptions) {
        this.buttonsListOfPaymentOptions = buttonsListOfPaymentOptions;
    }

    public void setPaymentOptions(String[] paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setTodaysDate() {
        LocalDate today = LocalDate.now();

        System.out.println(today);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String todayFormatted = today.format(formatter);

        this.todaysDate = todayFormatted;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
