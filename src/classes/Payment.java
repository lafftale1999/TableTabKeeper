package classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import GUIs.buttons.BorderButton;

public class Payment {
    
    private OpenTab activeTab;
    private Table activeTable;
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
        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMDD");

        int id = Integer.parseInt(today.format(formatter));
        
        
        this.paymentId = id;
    }

    public void setTodaysDate() {
        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");

        String todayFormatted = today.format(formatter);

        this.todaysDate = todayFormatted;
    }
}
