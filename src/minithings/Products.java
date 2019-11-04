package minithings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author k00185406
 */
public class Products {
    
    private int productID;
    private String name;
    private double price;
    private String description;
    private int qtyOnHand;
    private int reorderQty;
    private int qtySold;
    private String image;
    private String abv;

    public Products(String name, double price, String description, int qtyOnHand, int reorderQty, int qtySold, String image, String abv) throws ClassNotFoundException {
        this.productID = getProductIDfromDB()+1 ;
        this.name = name;
        this.price = price;
        this.description = description;
        this.qtyOnHand = qtyOnHand;
        this.reorderQty = reorderQty;
        this.qtySold = qtySold;
        this.image = image;
        this.abv = abv;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", name=" + name + ", price=" + price + ", description=" + description + ", qtyOnHand=" + qtyOnHand + ", reorderQty=" + reorderQty + ", qtySold=" + qtySold + ", image=" + image + ", abv=" + abv + '}';
    }
    public int getProductIDfromDB() throws ClassNotFoundException{
        try {
            Connection c = DatabaseHelperClass.getConnection();
            Statement s = c.createStatement();
            String query = "SELECT ID FROM products ORDER BY ID DESC";
            System.out.println(query);
            ResultSet results =s.executeQuery(query);
            results.next();
            int rowCount = results.getInt(1);
            return rowCount;
        } catch (SQLException ex) {
            Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }
    
    public void  saveToDatabase() throws ClassNotFoundException{
        try {
            Connection c = DatabaseHelperClass.getConnection();
//            Statement s = c.createStatement();
            String template =String.format("INSERT INTO %s VALUES(?,?,?,?,?,?,?,?,?)","products");
            PreparedStatement inserter = c.prepareStatement(template);
            inserter.setInt(1, productID);
            inserter.setString(2,name);
            inserter.setDouble(3,price);
            inserter.setString(4, description);
            inserter.setInt(5, qtyOnHand);
            inserter.setInt(6, reorderQty);
            inserter.setInt(7, qtySold);
            inserter.setString(8, image);
            inserter.setString(9, abv);
            inserter.executeUpdate();
            inserter.close();
//            String query = "INSERT INTO product (ID, Name, Price, Description, QtyOnHand, ReorderQty, QtySold, Image, ABV)" +
//                               "VALUES ('" + this.productID + "','" + this.name + "','" + this.price + "','" + this.description + "','" + this.qtyOnHand +"','" + this.reorderQty +"','"+ this.qtySold +"','"+ this.image +"','" + this.abv + "')";
//            System.out.println(query);
//            s.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the qtyOnHand
     */
    public int getQtyOnHand() {
        return qtyOnHand;
    }

    /**
     * @param qtyOnHand the qtyOnHand to set
     */
    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    /**
     * @return the reorderQty
     */
    public int getReorderQty() {
        return reorderQty;
    }

    /**
     * @param reorderQty the reorderQty to set
     */
    public void setReorderQty(int reorderQty) {
        this.reorderQty = reorderQty;
    }

    /**
     * @return the qtySold
     */
    public int getQtySold() {
        return qtySold;
    }

    /**
     * @param qtySold the qtySold to set
     */
    public void setQtySold(int qtySold) {
        this.qtySold = qtySold;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the abv
     */
    public String getAbv() {
        return abv;
    }

    /**
     * @param abv the abv to set
     */
    public void setAbv(String abv) {
        this.abv = abv;
    }
}
