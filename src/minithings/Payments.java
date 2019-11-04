/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minithings;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Owner
 */
public class Payments {
    private int customerNumber;
    private String checkNumber;
    private Date paymentsDate;
    private double amount;
    
    public Payments(){
        
    }
    
    public Payments(int customerNumber, String checkNumber, Date paymentsDate, double amount){
        this.customerNumber = customerNumber;
        this.checkNumber = checkNumber;
        this.paymentsDate = paymentsDate;
        this.amount = amount;
    }
    
    public void getUserInput() throws ParseException{
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Customer Number:");
        customerNumber = input.nextInt();
        System.out.println("Enter Check Number:");
        checkNumber = input.next();
        System.out.println("Enter date (dd/MM/yyyy):");
        paymentsDate = new SimpleDateFormat("dd/MM/yyyy").parse(input.next());
        System.out.println("Enter amount:");
        amount = input.nextDouble();
    }
    
    public void updatetUserInput() throws ParseException{
        Scanner input = new Scanner(System.in);

        System.out.println("Enter date (dd/MM/yyyy):");
        paymentsDate = new SimpleDateFormat("dd/MM/yyyy").parse(input.next());
        System.out.println("Enter amount:");
        amount = input.nextDouble();
    }
    
    public Payments getPayment(int customerNumber, String checkNumber) throws SQLException, ClassNotFoundException{
        Connection c = DatabaseHelperClass.getConnection();
        String template = "SELECT * FROM payments WHERE customernumber=" + customerNumber + ", checkNumber=" + checkNumber;
        if(c != null){
            try{
                PreparedStatement inserter = c.prepareStatement(template);
                ResultSet resultSet = inserter.executeQuery();
                this.customerNumber = resultSet.getInt("customerNumber");
                this.checkNumber = resultSet.getString("checkNumber");
                this.paymentsDate = resultSet.getDate("paymentDate");
                this.amount = resultSet.getDouble("amount");
            } catch (SQLException e){
                System.out.println("Error on retrieving payment " + e);
            }
        }
        
        return this;
    }
    
    public ArrayList<Payments> findAllPayments(int option) throws SQLException, ClassNotFoundException {
        ArrayList<Payments> allPayments = new ArrayList<Payments>();

        Connection c = DatabaseHelperClass.getConnection();
        String template = "";
        
        if(option == 1){
            template = "SELECT * FROM payments";
        } else if(option == 2){
            template = "SELECT * FROM payments ORDER BY amount DESC";
        }

        if (c != null) {
            try {
                PreparedStatement inserter = c.prepareStatement(template);
                ResultSet resultSet = inserter.executeQuery();
//                System.out.println("All beers");
                while (resultSet.next()) {
                    Payments b = new Payments();
                    b.customerNumber = resultSet.getInt("customerNumber");
                    b.checkNumber = resultSet.getString("checkNumber");
                    b.paymentsDate = resultSet.getDate("paymentDate");
                    b.amount = resultSet.getDouble("amount");
                    
                    allPayments.add(b);
                }

//                System.out.println(inserter);
                inserter.close();
                c.close();
            } catch (SQLException ex) {
                System.out.println("Error on find all " + ex);
            }
        }

        return allPayments;
    }
    
    public void makePayment() throws SQLException, ClassNotFoundException, ParseException{
        Scanner input = new Scanner(System.in);
        Connection c = DatabaseHelperClass.getConnection();
        
        String template = "INSERT INTO paymentss VALUES(?,?,?,?)";
        
        if (c != null) {
            try {
                getUserInput();

                PreparedStatement inserter = c.prepareStatement(template);
                
                java.sql.Date sqlDate = new java.sql.Date(paymentsDate.getTime());
                
                inserter.setInt(1, this.customerNumber);
                inserter.setString(2, this.checkNumber);
                inserter.setDate(3, sqlDate);
                inserter.setDouble(4, this.amount);
                inserter.executeUpdate();
                inserter.close();
                c.close();
            } catch (SQLException e) {
                System.out.println("Error while adding data " + e);
            }
        }   
    }
    
    public void deletePayments() throws SQLException, ClassNotFoundException {
        Connection c = DatabaseHelperClass.getConnection();

        String template = "DELETE FROM paymentss WHERE customerNumber=?, checkNumber=?";

        if (c != null) {
            try {
                PreparedStatement inserter = c.prepareStatement(template);

                inserter.setInt(1, this.customerNumber);
                inserter.setString(1, this.checkNumber);
                inserter.executeUpdate();
                inserter.close();
                c.close();
            } catch (SQLException e) {
                System.out.println("Error while deleting record " + e);
            }
        }
    }
    
    public void updatePayments() throws SQLException, ClassNotFoundException, ParseException {
        Connection c = DatabaseHelperClass.getConnection();

        String template = "UPDATE paymentss SET paymentsDate=?, amount=? WHERE customerNumber=?, checkNumber=?";

        if (c != null) {
            try {
                PreparedStatement inserter = c.prepareStatement(template);
                
                updatetUserInput();
                java.sql.Date sqlDate = new java.sql.Date(paymentsDate.getTime());
                
                inserter.setDate(1, sqlDate);
                inserter.setDouble(2, this.amount);
                inserter.setInt(3, this.customerNumber);
                inserter.setString(4, this.checkNumber);
                inserter.executeUpdate();
                inserter.close();
                c.close();
            } catch (SQLException e) {
                System.out.println("Error while deleting record " + e);
            }
        }
    }

    /**
     * @return the customerNumber
     */
    public int getCustomerNumber() {
        return customerNumber;
    }

    /**
     * @param customerNumber the customerNumber to set
     */
    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    /**
     * @return the checkNumber
     */
    public String getCheckNumber() {
        return checkNumber;
    }

    /**
     * @param checkNumber the checkNumber to set
     */
    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    /**
     * @return the paymentsDate
     */
    public Date getPaymentsDate() {
        return paymentsDate;
    }

    /**
     * @param paymentsDate the paymentsDate to set
     */
    public void setPaymentsDate(Date paymentsDate) {
        this.paymentsDate = paymentsDate;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
