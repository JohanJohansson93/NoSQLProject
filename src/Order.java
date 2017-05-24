import java.util.Date;

/**
 * Created by Johan on 2017-05-23.
 */
public class Order {

    private double Price;
    private boolean TransactionComplete;
    private Product[] products;
    private Date Date;
    private int orderID;

    public Order(double price, boolean transactionComplete, Product[] products, Date date, int orderID){
        this.Price = price;
        this.TransactionComplete = transactionComplete;
        this.products = products;
        this.Date = date;
        this.orderID = orderID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    /*public boolean isCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(boolean customerType) {
        CustomerType = customerType;
    }
*/
    public boolean isTransactionComplete() {
        return TransactionComplete;
    }

    public void setTransactionComplete(boolean transactionComplete) {
        TransactionComplete = transactionComplete;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setProductID(int orderID) {
        this.orderID = orderID;
    }
}
