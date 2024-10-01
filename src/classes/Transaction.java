package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Transaction {
    
    private static ArrayList<Transaction> savedTransactions = new ArrayList<>();
    private Payment activePayment;
    private String paymentMethod;
    private String paymentDate;
    private int paymentId;
    private float transactionValue;
    private static String filepath = "src\\files\\transactions.txt";

    private Transaction(String paymentDate, String paymentMethod, float transactionValue, int paymentId){
        /**Used to construct payments for savedTransactions*/
        setPaymentDate(paymentDate);
        setPaymentMethod(paymentMethod);
        setTransactionValue(transactionValue);
        setPaymentId(paymentId);
    }

    public Transaction(){
        /**Used to create the list of savedTransactions
         * reads our saved file transactions.txt
         */

        readSavedTransactions();

        // if our list size is bigger than 0 - increase the tabcounter to avoid ID duplicates
        if (savedTransactions.size() > 0) OpenTab.setTabCounter(savedTransactions.get(savedTransactions.size() - 1).getPaymentId());
    }

    public Transaction(Payment payment){
        /**Creates new transaction for savedTransactions
         * 
         * @param Payment payment a finished payment
         */

        savedTransactions.add(new Transaction(payment.getTodaysDate(), payment.getPaymentMethod(), payment.getActiveTab().getTabTotal(), payment.getPaymentId()));
        writeSavedTransactions();
        
        // writes a success message
        JOptionPane.showMessageDialog(null, "Payment has been received", "Payment message", JOptionPane.PLAIN_MESSAGE);
    }

    // ------------------ METHODS ----------------------
    public static void readSavedTransactions(){
        /**Reads saved transactions from filepath and adds them to savedTransactions */
        
        try {
            File transactionFile = new File(filepath);

            Scanner scanner = new Scanner(transactionFile);

            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] listStrings = data.split(",");

                String date = listStrings[0];
                String method = listStrings[1];
                float amount = Float.parseFloat(listStrings[2]);
                int id = Integer.parseInt(listStrings[3]);

                savedTransactions.add(new Transaction(date, method, amount, id));
            }
            scanner.close();
        }

        catch (FileNotFoundException e){
            System.out.println("Theres no file named transactions.txt");
        }
    }

    public static void writeSavedTransactions(){
        /**Writes all savedTransactions in to filepath */

        if (savedTransactions.size() > 0) {
            try {
                FileWriter writer = new FileWriter(filepath);

                for (Transaction transaction : savedTransactions) {
                    writer.write(transaction.getPaymentDate() + "," + transaction.getPaymentMethod() + "," + transaction.getTransactionValue() + "," + transaction.getPaymentId() + ",\n");
                }
                writer.close();
            }

            catch (IOException e) {
                System.out.println("An error occured");
            }  
        }
    }

    // ----------------- GETTERS -----------------
    public Payment getActivePayment() {
        return activePayment;
    }

    public static ArrayList<Transaction> getSavedTransactions() {
        return savedTransactions;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public float getTransactionValue() {
        return transactionValue;
    }
    
    public String getFilepath() {
        return filepath;
    }

    public int getPaymentId() {
        return paymentId;
    }

    // ----------------- SETTERS -----------------
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setTransactionValue(float transactionValue) {
        this.transactionValue = transactionValue;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    
    public void setActivePayment(Payment activePayment) {
        this.activePayment = activePayment;
    }
}
