import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by Johan on 2017-05-23.
 * The controller that acts as an intermediator between the GUI and the database.
 */
public class Controller {

    /*
        Declare instance variables.
     */
    private GUI gui;
    private DatabaseConnector db;
    private Order order;
    private Member member;
    private Employee employee;
    private Stock[] stock;
    private Product product;
    private Product [] listofProducts;
    private String [] espresso, Latte, cappucino, chocolate, coffee;

    /*
        The constructor for the controller.
        It instantiate the databaseconnector.
     */
    public Controller(GUI GUI){
        this.gui = GUI;
        db = new DatabaseConnector();
    }
    /*
        A second constructor that instantiate the databaseconnector.
     */
    public Controller(){
        db = new DatabaseConnector();
    }

    /*
        This method takes several arguments as parameters to create a specific order.
        It calls FetchProducts in the databaseConnector to fetch the products and then updates the stock.
        At last it calls the method CreateOrder in databaseConnector and retrieves true or false depending on the status of the order.
     */
    public boolean CreateOrder(double price, boolean transactionComplete, String [] products, String date, int employeeID) throws ExecutionException, InterruptedException {

        ArrayList<Product> prods = new ArrayList<>();
        ArrayList<String> stock1 = new ArrayList<>();
        ArrayList<String> ingredients = new ArrayList<>();

        try {
            prods = db.FetchProducts();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < prods.size(); i++) {
            for (int j = 0; j < products.length; j++) {
                if (prods.get(i).getName().equals(products[j])) {
                    for (int k = 0; k < prods.get(i).getStock().length; k++) {
                        stock1.add(prods.get(i).getStock()[k]);
                    }
                }
            }
        }

        String [] parts;
        for (int i = 0; i < stock1.size(); i++) {
            parts = stock1.get(i).split(",");
            for (int j = 0; j < parts.length; j++) {
                    String ingredientString = parts[j].replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"","").replaceAll("\"","");
                    System.out.println("Item: " + ingredientString);
                    ingredients.add(ingredientString);
            }
        }

        db.UpdateStock(ingredients);

        boolean Orderprocessed = false;

        transactionComplete = true;

        order = new Order(price, transactionComplete, products, date, employeeID);

        if (db.CreateOrder(order)){
            Orderprocessed = true;
        }
        System.out.println(Orderprocessed);
        return Orderprocessed;

    }
    /*
        A method that fetches the current orders from the database.
     */
    public void FetchOrders() throws ExecutionException, InterruptedException, UnknownHostException {
        db.FetchOrders();
    }
    /*
        A method that places the products into the database.
     */
    public void CreateProducts(Product [] products) throws InterruptedException, ExecutionException, UnknownHostException {
        db.CreateProducts(products);
    }
    /*
        This method fills the database with products and ingridients.
     */
    public void Fillproducts() throws InterruptedException, ExecutionException, UnknownHostException {

        stock = new Stock[5];

        stock[0] = new Stock("Espresso Roast", 1000);
        stock[1] = new Stock("Milk", 1000);
        stock[2] = new Stock("Cacao", 1000);
        stock[3] = new Stock("Whipped Cream",1000);
        stock[4] = new Stock("Whole Bean French Roast", 1000);


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

        db.FillStock(stock);
        System.out.println("Ctrl: Stock filled");
    }
    /*
        This method fetches all products from the database and returns a list of products.
     */
    public Product [] FetchProducts() throws InterruptedException, ExecutionException, UnknownHostException {
        ArrayList<Product> productsAL = db.FetchProducts();
        listofProducts = new Product[productsAL.size()];

        for (int i = 0; i < productsAL.size(); i++) {
            listofProducts[i] = productsAL.get(i);
        }

        return listofProducts;
    }
    /*
        This method fetches specific orders based on a specific timestamp.
        If orders exists in the given timestamp it returns the orders.
     */
    public ArrayList<Order> createReport(Date startDate, Date endDate) throws InterruptedException, ExecutionException, UnknownHostException, ParseException {

        DateFormat df = new SimpleDateFormat("MMM d HH:mm:ss yyyy");
        ArrayList<Order> orderSize = db.FetchOrders();
        ArrayList<Order> orders = new ArrayList<Order>();

        for(int i = 0; i < orderSize.size(); i++) {
            String sales = orderSize.get(i).getDate().replaceAll("\"","").replaceAll("\"","");
            Date result =  df.parse(sales);

            if(result.after(startDate)){
                System.out.println("CTRL: " + startDate.toString());
                if(result.before(endDate)){
                    System.out.println("CTRL: " + endDate.toString());
                    orders.add(orderSize.get(i));

                }
            }
        }

        return orders;
    }
    /*
        This method fetches orders based on an employeeID.
     */
    public ArrayList<Order> createEmployeeReport(int id, Date startDate, Date endDate) throws InterruptedException, ExecutionException, UnknownHostException, ParseException{

        ArrayList<Order> orders;
        ArrayList<Order> empList = new ArrayList<>();
        orders = createReport(startDate, endDate);

        for(int i = 0; i < orders.size(); i++) {
            int employeeId = orders.get(i).getEmployeeID();
            if ( employeeId == id) {
              empList.add(orders.get(i));
              System.out.println(employeeId);
            }
        }
        return empList;
    }
    /*
        This method takes the given arguments and stores an employee's data in the database.
     */
    public void CreateEmployee(String name,String type, String Sdate, String Edate, String comments, int worktime ) throws InterruptedException, ExecutionException, UnknownHostException {
        Employee employee = new Employee(name,type, Sdate, Edate,comments, worktime);
        db.CreateEmplyoee(employee);
        System.out.println("Ctrl: CreateEmployee method called");
    }
    /*
        This method takes the members data and stores it in the database.
     */
    public void CreateMember(String SSN, String address, String occupation) throws ExecutionException, InterruptedException {
            Member member = new Member(Integer.parseInt(SSN), address, occupation);
            db.CreateMember(member);
            System.out.println("Ctrl: CreateMember method called");
    }

}
