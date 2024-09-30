package GUIs.frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
        this.setSize(500,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Transactions");
        this.setListOfTransactions(Transaction.getSavedTransactions());
        
        ImageIcon image = new ImageIcon("TableTabLogo.png");

        this.setIconImage(image.getImage());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(mainPanel);

        this.setVisible(true);
        this.add(scrollPane, BorderLayout.CENTER);

        createTranscationPanels();
        System.out.println("FRAME HAS BEEN CREATED");
    }

    public void createTranscationPanels(){

        if (listOfPanels.size() > 0) {

            listOfPanels.clear();

        }

        if (listOfTransactions.size() > 0) {
            JPanel titlePanel = new JPanel();
            titlePanel.setPreferredSize(new Dimension(450, panelHeight));
            titlePanel.setLayout(new GridLayout(1, 4));
            titlePanel.setMaximumSize(new Dimension(450, panelHeight));
            titlePanel.setAlignmentY(Component.TOP_ALIGNMENT);

            JLabel idTitle = new JLabel();
            idTitle.setText("ID");
            idTitle.setFont(new Font(null, Font.BOLD, 14));
            titlePanel.add(idTitle);
            
            JLabel dateTitle = new JLabel();
            dateTitle.setText("DATE");
            dateTitle.setFont(new Font(null, Font.BOLD, 14));
            titlePanel.add(dateTitle);

            JLabel paymentMethodTitle = new JLabel();
            paymentMethodTitle.setText("PAYMENT");
            paymentMethodTitle.setFont(new Font(null, Font.BOLD, 14));
            titlePanel.add(paymentMethodTitle);

            JLabel valueTitle = new JLabel();
            valueTitle.setText("VALUE");
            valueTitle.setFont(new Font(null, Font.BOLD, 14));
            titlePanel.add(valueTitle);

            listOfPanels.add(titlePanel);
            System.out.println("TITLE HAS BEEN CREATED");

        
            for(Transaction transaction : listOfTransactions) {
                
                JPanel newPanel = new JPanel();
                newPanel.setPreferredSize(new Dimension(450, panelHeight));
                newPanel.setLayout(new GridLayout(1, 4));
                newPanel.setMaximumSize(new Dimension(450, panelHeight));
                newPanel.setAlignmentY(Component.TOP_ALIGNMENT);
                

                JLabel idText = new JLabel();
                idText.setText(Integer.toString(transaction.getPaymentId()));
                newPanel.add(idText);

                JLabel dateText = new JLabel();
                dateText.setText(transaction.getPaymentDate());
                newPanel.add(dateText);

                JLabel paymentMethodText = new JLabel();
                paymentMethodText.setText(transaction.getPaymentMethod());
                newPanel.add(paymentMethodText);

                JLabel valueText = new JLabel();
                valueText.setText(NumberFormat.getCurrencyInstance().format(transaction.getTransactionValue()));
                newPanel.add(valueText);

                System.out.println("TRANSACTION INFORMATION LINES CREATED");
                listOfPanels.add(newPanel);
            }
        }

        else {
            JPanel newPanel = new JPanel();
                newPanel.setPreferredSize(new Dimension(450, panelHeight));
                newPanel.setLayout(new GridLayout(1, 4));
                

                JLabel errorMessage = new JLabel();
                errorMessage.setText("No transactions registered!");
                errorMessage.setHorizontalAlignment(JLabel.CENTER);
                newPanel.add(errorMessage);

                listOfPanels.add(newPanel);
        }
        

        drawTransactions();
    }

    public void drawTransactions(){


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
