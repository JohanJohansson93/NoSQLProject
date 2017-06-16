import java.util.Date;

/**
 * Created by Johan on 2017-05-23.
 * Class Order
 * An order, containing price, products, date created, employee id
 */
public class Order {

    private double Price;
    private boolean TransactionComplete;
    private String[] products;
    private String date;
    private int EmployeeID;

    /**
     * Creates and order object, representing an order
     * @param price of product
     * @param transactionComplete boolean value, returns either true or false whether the transactions is done
     * @param products ordered
     * @param Date of order
     * @param employeeID id of employee
     */
    public Order(double price, boolean transactionComplete, String[] products, String Date, int employeeID) {
        this.Price = price;
        this.TransactionComplete = transactionComplete;
        this.products = products;
        this.date = Date;
        this.EmployeeID = employeeID;
    }

    /**
     * getPrice
     * @return the price of the product
     */
    public double getPrice() {
        return Price;
    }

    /**
     * setPrice
     * @param price sets the price of the product
     */
    public void setPrice(double price) {
        Price = price;
    }

    /**
     * isTransactionComplete
     * @return true or false
     */
    public boolean isTransactionComplete() {
        return TransactionComplete;
    }

    /**
     * setTransactionComplete
     * @param transactionComplete sets transaction either complete or not
     */
    public void setTransactionComplete(boolean transactionComplete) {
        TransactionComplete = transactionComplete;
    }

    /**
     * getProducts
     * @return all products
     */
    public String[] getProducts() {
        return products;
    }

    /**
     * setProducts
     * @param products sets a value on the product array
     */
    public void setProducts(String[] products) {
        this.products = products;
    }

    /**
     * getDate
     * @return a date of the creation of the product
     */
    public String getDate() {
        return date;
    }

    /**
     * setDate
     * @param Date sets date
     */
    public void setDate(String Date) {
        date = Date;
    }

    /**
     * getEmployeeID
     * @return an employee id
     */
    public int getEmployeeID() {
        return EmployeeID;
    }

    /**
     * setEmployeeID
     * @param employeeID sets an employee id
     */
    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

}
