import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Johan on 2017-05-23.
 */
public class Controller {

    private GUI gui;
    private DatabaseConnector db;
    private Order order;
    private Member member;
    private Stock stock;
    private Employee employee;
    private Product product;
    private Product [] listofProducts;
    private String [] espresso, Latte, cappucino, chocolate, coffee;


    public Controller(GUI GUI){
        this.gui = GUI;
        db = new DatabaseConnector();
        try {
            Fillproducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void CreateOrder(Order order) throws ExecutionException, InterruptedException {
        Date date;
        db.CreateOrder(order);

    }

    public void FetchOrders() throws ExecutionException, InterruptedException {
        db.FetchOrders();
    }

    public void DeleteOrder() throws ExecutionException, InterruptedException {
        db.DeleteOrder();
    }

    public void CreateProducts(Product [] products) throws InterruptedException, ExecutionException, UnknownHostException {
        db.CreateProducts(products);
    }

    public void Fillproducts() throws InterruptedException, ExecutionException, UnknownHostException {

        espresso = new String[1];
        Latte = new String[2];
        cappucino = new String[2];
        chocolate = new String[2];
        coffee = new String[1];

        espresso [0] = new String("Espresso Roast");
        Latte [0] = new String("Espresso Roast");
        Latte [1] = new String("Milk");
        cappucino [0] = new String("Espresso Roast");
        cappucino [1] = new String("Milk");
        chocolate [0] = new String("Cacao");
        chocolate [1] = new String("Whipped Cream");
        coffee [0] = new String("Whole Bean French Roast");

        Product [] listofProducts = new Product[5];

        listofProducts[0] = new Product("Espresso", 3, 6, 18, espresso);
        listofProducts[1] = new Product("Latte", 2, 6, 12, Latte);
        listofProducts[2] = new Product("Cappucino", 6, 12, 18, cappucino);
        listofProducts[3] = new Product("Hot Chocolate", 6, 12, 18, chocolate);
        listofProducts[4] = new Product("Brewed Coffee", 6, 12, 18, coffee);



        db.CreateProducts(listofProducts);
    }

    public Product [] FetchProducts() throws InterruptedException, ExecutionException, UnknownHostException {
        listofProducts = new Product[db.FetchProducts().size()];
        listofProducts = (Product[]) db.FetchProducts().toArray();
        db.FetchProducts();

        return listofProducts;
    }

    public void CreateEmployee(Employee employee) throws InterruptedException, ExecutionException, UnknownHostException {
            db.CreateEmplyoee(employee);
    }

    public void CreateMember(Member member) throws ExecutionException, InterruptedException {
            db.CreateMember(member);
    }

    public void ShutdownDB() throws UnknownHostException {
        db.ShutDownCluster();
    }


}
