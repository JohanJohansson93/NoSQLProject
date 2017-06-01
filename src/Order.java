import java.util.Date;

/**
 * Created by Johan on 2017-05-23.
 */
public class Order {

    private double Price;
    private boolean TransactionComplete;
    private String[] products;
    private Date Date;
    private int EmployeeID;


    public Order(double price, boolean transactionComplete, String[] products, Date date, int employeeID){
        this.Price = price;
        this.TransactionComplete = transactionComplete;
        this.products = products;
        this.Date = date;
        this.EmployeeID = employeeID;

    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isTransactionComplete() {
        return TransactionComplete;
    }

    public void setTransactionComplete(boolean transactionComplete) {
        TransactionComplete = transactionComplete;
    }

    public String[] getProducts() {
        return products;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

}
