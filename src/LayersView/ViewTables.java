package LayersView;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import GUIs.*;

import classes.OpenTab;
import classes.Table;


public class ViewTables extends JPanel{
    
    private MainPanel mainPanel;
    private SidePanel sidePanel;
    private BottomPanel bottomPanel;
    private SideBottomPanel sideBottomPanel;
    private MyFrame frame;
    private int amountOfTables = 5;
    private ViewTab nextLayer;
    private Table[] listOfTables = new Table[5];
    private ButtonGroup tableButtonGroup = new ButtonGroup();

    public ViewTables(MyFrame frame, MainPanel mainPanel, SidePanel sidePanel, BottomPanel bottomPanel, SideBottomPanel sideBottomPanel){
        setBottomPanel(bottomPanel);
        setMainPanel(mainPanel);
        setSidePanel(sidePanel);
        setSideBottomPanel(sideBottomPanel);
        setFrame(frame);
        

        bottomPanel.getTabButton().addActionListener(e -> {
            // see open tab
            if (bottomPanel.getCurrentTable().getHasTab()){
                
            }
            // create new tab
            else {
                bottomPanel.getCurrentTable().setActiveTab(new OpenTab(bottomPanel.getCurrentTable(), bottomPanel.getMenuItems()));
            }
        });

        // show transactions
        bottomPanel.getTransactionButton().addActionListener(e -> {
            System.out.println("Open transactions");
        });

        // create tables and add to arrays/groups
        for (int i = 0; i < amountOfTables; i++) {
            listOfTables[i] = new Table(false, i + 1, mainPanel);
            tableButtonGroup.add(listOfTables[i]);
        }

        for (Table table : listOfTables) {
            // adds actions listener to every table
            table.addActionListener(e -> {
                if(e.getSource() == table) {        
                    mainPanel.drawTable(listOfTables);
                    bottomPanel.createInformationBodyBottom(table);
                    sidePanel.createContainerForTables(listOfTables);
                }
            });
        }


    }

    public void drawViewTables(){
        
        frame.add(mainPanel);
        frame.add(sidePanel);
        frame.add(sideBottomPanel);
        frame.add(bottomPanel);
        
        mainPanel.drawTable(listOfTables);
        sidePanel.createContainerForTables(listOfTables);
        bottomPanel.createInformationBodyBottom();

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

    public void setFrame(MyFrame frame) {
        this.frame = frame;
    }

    public void setNextLayer(ViewTab nextLayer) {
        this.nextLayer = nextLayer;
    }

    public ViewTab getNextLayer() {
        return nextLayer;
    }
}
