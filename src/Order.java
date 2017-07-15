import java.util.Date;

/**
 * Created by Johan on 2017-05-23.
 * Class Order
 * An order, containing price, products, date created, employee id
 */
public class Order {
    /*
       Declare instance variables.
     */
    private double Price;
    private boolean TransactionComplete;
    private String[] products;
    private String date;
    private int EmployeeID;

    /*
       Constructor that takes several arguments that acts as data for the Order object.
     */
    public Order(double price, boolean transactionComplete, String[] products, String Date, int employeeID) {
        this.Price = price;
        this.TransactionComplete = transactionComplete;
        this.products = products;
        this.date = Date;
        this.EmployeeID = employeeID;

    }
    /*
       Method that returns the price.
     */
    public double getPrice() {
        return Price;
    }
    /*
       Method that sets the price.
     */
    public void setPrice(double price) {
        Price = price;
    }
    /*
       Method that returns a boolean.
     */
    public boolean isTransactionComplete() {
        return TransactionComplete;
    }
    /*
       Method that sets the boolean.
     */
    public void setTransactionComplete(boolean transactionComplete) {
        TransactionComplete = transactionComplete;
    }
    /*
       Method that returns an array with products.
     */
    public String[] getProducts() {
        return products;
    }
    /*
       Method that sets the array with products.
     */
    public void setProducts(String[] products) {
        this.products = products;
    }
    /*
       Method that returns the date.
     */
    public String getDate() {
        return date;
    }
    /*
       Method that sets the date.
     */
    public void setDate(String Date) {
        date = Date;
    }
    /*
       Method that returns the employeeID.
     */
    public int getEmployeeID() {
        return EmployeeID;
    }
    /*
       Method that sets the ID.
     */
    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

}
