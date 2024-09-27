package LayersView;

import classes.*;
import GUIs.panels.*;
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
    private String paymentMethod;


    public ViewPayment(ViewTab previousLayer, ViewTables nextLayer, MainPanel mainPanel, SidePanel sidePanel, BottomPanel bottomPanel, SideBottomPanel sideBottomPanel){
        
        setNextLayer(nextLayer);
        setPreviousLayer(previousLayer);
        setMainPanel(mainPanel);
        setSidePanel(sidePanel);
        setBottomPanel(bottomPanel);
        setSideBottomPanel(sideBottomPanel);

        
    }

    public void newPayment(OpenTab activeTab, Table activeTable){
        
        setActiveTab(activeTab);
        setActiveTable(activeTable);
        
        Payment activePayment = new Payment(activeTab, activeTable);

        for (BorderButton button : activePayment.getButtonsListOfPaymentOptions()){
            button.addActionListener(e -> {
                paymentMethod = button.getText();
            });
        }
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
