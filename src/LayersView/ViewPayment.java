package LayersView;

import classes.*;
import GUIs.panels.*;

import java.awt.event.ActionListener;

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
    }

    // ----------------- METHODS -------------------------
    public void checkIncomingTab (Table activeTable){
        /**Checks incoming table if its tab contains any items and if null
         * 
         * @param Table activeTable
         */

        // if table dont have a tab with items
        if (activeTable.getActiveTab().getListOfMenuItems().size() == 0 && activeTable.getActiveTab() != null){
            // removes tab
            activeTable.removeTab();
            // draw viewTables
            nextLayer.drawViewTables();
        }
        
        // if table have tab with items - new payment
        else {
            newPayment(activeTable.getActiveTab(), activeTable);
        }
    }

    public void newPayment(OpenTab activeTab, Table activeTable){
        /**Create new Payment with tab and table
         * 
         * @param OpenTab activeTab used in Payment constructor
         * @param Table activeTable used in payment constructor
         */

        setActiveTab(activeTab);
        setActiveTable(activeTable);
        
        Payment activePayment = new Payment(activeTab, activeTable);
        resetListeners(activePayment);

        drawPayment(activePayment);
    }

    public void drawPayment(Payment activePayment){
        /**Draws out the current payment session
         * 
         * @param Payment activePayment is used to show current information
         */
        
        mainPanel.drawPaymentOptions(activePayment.getButtonsListOfPaymentOptions(), paymentMethod);
        bottomPanel.createInformationBodyBottom(activeTable, paymentMethod);
        sidePanel.createContainerForActiveTab(activeTab, activeTable,"viewpayment");
        sideBottomPanel.drawTabTotal(activeTab);
    }

    // ----------------- LISTENERS -------------------------
    public void resetListeners(Payment activePayment) {
        /**Resets all listeners in payment view
         * 
         * @param Payment activePayment is used to reset paymentoptions buttons
         */

        // for loop to reset and add listeners
        for (BorderButton button : activePayment.getButtonsListOfPaymentOptions()) {
            
            // remove
            for (ActionListener al : button.getActionListeners()){
                button.removeActionListener(al);
            }

            // add
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
        // PAY
        bottomPanel.getPayButton().addActionListener(e -> {

            // If the user has payed
            if (!paymentMethod.equals("")){
                Transaction transaction = new Transaction(activePayment);
                activeTable.setActiveTab(null);
                paymentMethod = "";
                nextLayer.drawViewTables();
            }
            
            else {
                JOptionPane.showMessageDialog(null, "Choose payment method", "Payment message", JOptionPane.PLAIN_MESSAGE);
            }
        });

        // BACK
        bottomPanel.getBackButton().addActionListener(e -> {
            paymentMethod = "";

            previousLayer.drawViewTab();
        });

    }

    // ----------------- GETTERS -------------------------
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
    
    // ----------------- SETTERS -------------------------
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
