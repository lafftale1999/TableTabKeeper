package GUIs.frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import classes.Transaction;

public class TransactionFrame extends JFrame{

    ArrayList<JPanel> listOfPanels = new ArrayList<>();
    JPanel mainPanel;
    ArrayList<Transaction> listOfTransactions = new ArrayList<>();
    private int panelHeight = 20;

    public TransactionFrame(){
        
        // set frame
        this.setSize(500,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Transactions");
        this.setListOfTransactions(Transaction.getSavedTransactions());
        
        // add icon
        ImageIcon image = new ImageIcon("TableTabLogo.png");
        this.setIconImage(image.getImage());

        // create panel for all transactions
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // make panel scrollable
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(mainPanel);

        this.setVisible(true);
        this.add(scrollPane, BorderLayout.CENTER);

        createTranscationPanels();
    }

    // ------------------- METHODS -----------------------
    private ArrayList<JLabel> createTitles(String[] headlines){
        /**Creates title labels
         * 
         * @param String[] headlines is used to loop through all headlines and set them as text
         * return ArrayList<JLabel>;
        */

        ArrayList<JLabel> labels = new ArrayList<>();

        Font font = new Font(null, Font.BOLD, 14);

        for (String headline : headlines) {
            JLabel label = new JLabel();
            label.setText(headline);
            label.setFont(font);
        }
        
        return labels;
    }

    private ArrayList<JLabel> createTransactionLabels(String[]listOfInformation){
        /**Creates a list of labels by looping through the array
         * 
         * @param String[] listOfInformation used to loop through list of transaction information as id, date, method, value
         * return ArrayList<JLabel>;
         */

        ArrayList<JLabel> list = new ArrayList<>();

        for (String information : listOfInformation) {
            JLabel label = new JLabel();
            label.setText(information);
            list.add(label);
        }

        return list;
    }

    private JPanel createPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(450, panelHeight));
        panel.setLayout(new GridLayout(1, 4));
        panel.setMaximumSize(new Dimension(450, panelHeight));
        panel.setAlignmentY(Component.TOP_ALIGNMENT);

        return panel;
    }
    
    // ------------------- VIEWTRANSACTIONS -----------------------
    public void createTranscationPanels(){
        /**Creates the panel for showing transactions
         * 
         */

        // clear list
        if (listOfPanels.size() > 0) {
            listOfPanels.clear();
        }

        // checks if we have any saved transactions
        if (listOfTransactions.size() > 0) {
            
            // String array with headlines
            String[] headlines = {"ID", "DATE", "PAYMENT", "VALUE"};

            // creates panel
            JPanel titlePanel = createPanel();

            // creates a list of lables
            ArrayList<JLabel> titleLables = createTitles(headlines);

            // adds labels to title panel
            for (JLabel label : titleLables) {
                titlePanel.add(label);
            }
            
            // adds titlepanel to list
            listOfPanels.add(titlePanel);

            // loops through every transaction
            for(Transaction transaction : listOfTransactions) {
                
                // creates specific
                JPanel newPanel = createPanel();
                
                // creates a string array with payment id, date, method and value
                String[] transactionInformation = {
                    Integer.toString(transaction.getPaymentId()), // ID
                    transaction.getPaymentDate(),                 // DATE
                    transaction.getPaymentMethod(),               // METHOD
                    NumberFormat.getCurrencyInstance().format(transaction.getTransactionValue())    // VALUE
                };

                // creates list of labels
                ArrayList<JLabel> transactionLables = createTransactionLabels(transactionInformation);

                // adds labels to newPanel
                for (JLabel label : transactionLables) {
                    newPanel.add(label);
                }

                // adds newPanel to list
                listOfPanels.add(newPanel);
            }
        }

        // if no transactions
        else {
            // create panel
            JPanel newPanel = new JPanel();
            newPanel.setPreferredSize(new Dimension(450, panelHeight));
            newPanel.setLayout(new GridLayout(1, 1));
            
            // create message and add to panel
            JLabel errorMessage = new JLabel();
            errorMessage.setText("No transactions registered!");
            errorMessage.setHorizontalAlignment(JLabel.CENTER);
            newPanel.add(errorMessage);

            // add panel to list
            listOfPanels.add(newPanel);
        }
        
        // draw
        drawTransactions();
    }

    // ------------------- DRAW -----------------------
    public void drawTransactions(){
        /**
         * Draws out transactions
         */

        mainPanel.removeAll();

        for (JPanel panel : listOfPanels){

            mainPanel.add(panel);
        }

        mainPanel.add(Box.createVerticalGlue());

        this.revalidate();
        this.repaint();
    }

    public void setListOfTransactions(ArrayList<Transaction> listOfTransactions) {
        this.listOfTransactions = listOfTransactions;
    }
}
