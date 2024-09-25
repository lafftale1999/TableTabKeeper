package LayersView;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import GUIs.*;

import classes.OpenTab;
import classes.Table;
import java.util.ArrayList;


public class ViewTab extends JPanel{

        private ViewTables previousLayer;
        // nextLayer payment
        private OpenTab activeTab;
        private Table activeTable;
        private ArrayList<BorderButton> listOfButtons = new ArrayList<>();
}
