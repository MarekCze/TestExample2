/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minithings;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Owner
 */
public class MiniThings {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
        ArrayList<Payments> paymentsShop = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        int IDSearch = 0;
        int option = 0;
        do {
            System.out.println("1. Add payments");
            System.out.println("2. Delete payments");
            System.out.println("3. Sell payments");
            System.out.println("4. Display paymentss by sales");
            System.out.println("5. Stock order");
            System.out.println("6. Exit");
            option = s.nextInt();
            switch (option) {
                case 1:
                    Payments b = new Payments();
                    b.makePayment();
                    break;

                case 2:
                    System.out.println("Enter customer Number:");
                    int customerNumber = s.nextInt();
                    System.out.println("Enter check number:");
                    String checkNumber = s.next();
                    b = new Payments();
                    b = b.getPayment(customerNumber, checkNumber);
                    b.deletePayments();
                    break;

                case 3:
                    System.out.println("Enter customer Number:");
                    customerNumber = s.nextInt();
                    System.out.println("Enter check number:");
                    checkNumber = s.next();
                    b = new Payments();
                    b = b.getPayment(customerNumber, checkNumber);
                    b.updatePayments();
                    break;

                case 4:
                    displayPayments();
                    break;

                case 5:
                    b = new Payments();
                    paymentsShop = b.findAllPaymentss(1);
                    stockOrder(paymentsShop);
                    break;

            }
        } while (option != 6);
    }

    public static boolean updatePayments(ArrayList<Payments> list, Payments b) {
        boolean done = false;
        try {
            b.getUserInput();
            done=true;
        } catch (Exception ex) {
            done = false;
        }
        return done;
    }

    public static boolean deletePayments(ArrayList<Payments> list, Payments b) {
        boolean done = false;
        Iterator i = list.iterator();
        while (i.hasNext()) {
            i.remove();
            done = true;
        }
        return done;
    }

    public static void displayPayments() throws SQLException, ClassNotFoundException {
        Payments b = new Payments();
        ArrayList<Payments> paymentss = new ArrayList<Payments>(b.findAllPayments(2));
        
        for (Payments Payments : paymentss) {
            Payments.print();
        }
    }

    public static Payments findPayments(ArrayList<Payments> list, int idSearch) {

        for (Payments b : list) {

            if (b.getID() == idSearch) {

                return b;
            }
        }
        return null;
    }
    
}
