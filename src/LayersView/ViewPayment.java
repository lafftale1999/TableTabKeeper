package LayersView;

import classes.*;
import GUIs.panels.*;

import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import GUIs.buttons.*;;

public class ViewPayment {
    
    private ViewTab previousLayer;
    private ViewTables nextLayer;
    private OpenTab activeTab;
    private Table activeTable;
    private MainPanel mainPanel;
    private SidePanel sidePanel;
    private BottomPanel bottomPanel;
    private SideBottomPanel sideBottomPanel;
    private String paymentMethod = "";


    public ViewPayment(ViewTab previousLayer, ViewTables nextLayer, MainPanel mainPanel, SidePanel sidePanel, BottomPanel bottomPanel, SideBottomPanel sideBottomPanel){
        
        setNextLayer(nextLayer);
        setPreviousLayer(previousLayer);
        setMainPanel(mainPanel);
        setSidePanel(sidePanel);
        setBottomPanel(bottomPanel);
        setSideBottomPanel(sideBottomPanel);

        // reset all actionlisteners from back button and pay button
    }

    public void checkIncomingTab (Table activeTable){
        if (activeTable.getActiveTab().getListOfMenuItems().size() == 0 && activeTable.getActiveTab() != null){
            activeTable.removeTab();
            nextLayer.drawViewTables();
        }
        
        else {
            newPayment(activeTable.getActiveTab(), activeTable);
        }
    }

    public void newPayment(OpenTab activeTab, Table activeTable){
        
        setActiveTab(activeTab);
        setActiveTable(activeTable);
        

        Payment activePayment = new Payment(activeTab, activeTable);
        resetListeners(activePayment);

        drawPayment(activePayment);

    }


    public void drawPayment(Payment activePayment){
        mainPanel.drawPaymentOptions(activePayment.getButtonsListOfPaymentOptions(), paymentMethod);
        bottomPanel.createInformationBodyBottom(activeTable, paymentMethod);
        sidePanel.createContainerForActiveTab(activeTab, activeTable,"viewpayment");
        sideBottomPanel.drawTabTotal(activeTab);
    }

    public void resetListeners(Payment activePayment) {
        
        for (BorderButton button : activePayment.getButtonsListOfPaymentOptions()) {
            for (ActionListener al : button.getActionListeners()){
                button.removeActionListener(al);
            }

            button.addActionListener(e -> {
                paymentMethod = button.getText();
                activePayment.setPaymentMethod(paymentMethod);
                mainPanel.drawPaymentOptions(activePayment.getButtonsListOfPaymentOptions(), paymentMethod);
                bottomPanel.createInformationBodyBottom(activeTable, paymentMethod);
                resetListeners(activePayment);
            });

        }

        // reset listeners
        if (bottomPanel.getPayButton().getActionListeners().length > 0){
            for (ActionListener al : bottomPanel.getPayButton().getActionListeners()) {
                bottomPanel.getPayButton().removeActionListener(al);
            }
        }

        if (bottomPanel.getBackButton().getActionListeners().length > 0){
            for (ActionListener al : bottomPanel.getBackButton().getActionListeners()) {
                bottomPanel.getBackButton().removeActionListener(al);
            }
        }

        
        // add new listeners
        bottomPanel.getPayButton().addActionListener(e -> {

            if (!paymentMethod.equals("")){
                // PAYMENT CODE
                Transaction transaction = new Transaction(activePayment);
                activeTable.setActiveTab(null);
                paymentMethod = "";

                nextLayer.drawViewTables();
            }
            
            else {
                JOptionPane.showMessageDialog(null, "Choose payment method", "Payment message", JOptionPane.PLAIN_MESSAGE);
            }
        });

        bottomPanel.getBackButton().addActionListener(e -> {
            paymentMethod = "";

            previousLayer.drawViewTab();
        });

    }

    public OpenTab getActiveTab() {
        return activeTab;
    }
    public Table getActiveTable() {
        return activeTable;
    }
    public ViewTables getNextLayer() {
        return nextLayer;
    }
    public ViewTab getPreviousLayer() {
        return previousLayer;
    }
    
    public void setActiveTab(OpenTab activeTab) {
        this.activeTab = activeTab;
    }
    public void setActiveTable(Table activeTable) {
        this.activeTable = activeTable;
    }
    public void setNextLayer(ViewTables nextLayer) {
        this.nextLayer = nextLayer;
    }
    public void setPreviousLayer(ViewTab previousLayer) {
        this.previousLayer = previousLayer;
    }
    public void setBottomPanel(BottomPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
    }
    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    public void setSideBottomPanel(SideBottomPanel sideBottomPanel) {
        this.sideBottomPanel = sideBottomPanel;
    }
    public void setSidePanel(SidePanel sidePanel) {
        this.sidePanel = sidePanel;
    }

}
