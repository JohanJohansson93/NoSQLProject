import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Johan on 2017-05-23.
 */
public class Order {

    private double Price;
    private boolean CustomerType, TransactionComplete;
    private LinkedList Products;
    private Date Date;
    private int ProductID;

    public Order(double price, boolean customerType, boolean transactionComplete, LinkedList products, Date date, int productID){
        this.Price = price;
        this.CustomerType = customerType;
        this.TransactionComplete = transactionComplete;
        this.Products = products;
        this.Date = date;
        this.ProductID = productID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(boolean customerType) {
        CustomerType = customerType;
    }

    public boolean isTransactionComplete() {
        return TransactionComplete;
    }

    public void setTransactionComplete(boolean transactionComplete) {
        TransactionComplete = transactionComplete;
    }

    public LinkedList getProducts() {
        return Products;
    }

    public void setProducts(LinkedList products) {
        this.Products = products;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }
}
