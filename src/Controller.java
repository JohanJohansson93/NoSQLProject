import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by Johan on 2017-05-23.
 */
public class Controller {

    private GUI gui;
    private DatabaseConnector db;
    private Order order;
    private Member member;
    private Employee employee;
    private Stock[] stock;
    private Product product;
    private Product [] listofProducts;
    private String [] espresso, Latte, cappucino, chocolate, coffee;


    public Controller(GUI GUI){
        this.gui = GUI;
        db = new DatabaseConnector();
        try {
            //Fillproducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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

    public void FetchOrders() throws ExecutionException, InterruptedException, UnknownHostException {
        db.FetchOrders();
    }
    /*
    public void DeleteOrder(Order order) throws ExecutionException, InterruptedException {
        db.DeleteOrder(order);
    }
    */
    public void CreateProducts(Product [] products) throws InterruptedException, ExecutionException, UnknownHostException {
        db.CreateProducts(products);
    }

    public void Fillproducts() throws InterruptedException, ExecutionException, UnknownHostException {

        stock = new Stock[5];

        stock[0] = new Stock("Espresso Roast", 1000);
        stock[1] = new Stock("Milk", 1000);
        stock[2] = new Stock("Cacao", 1000);
        stock[3] = new Stock("Whipped Cream",1000);
        stock[4] = new Stock("Whole Bean French Roast", 1000);

        /*
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
        */
        db.FillStock(stock);
        System.out.println("Ctrl: Stock filled");
    }

    public Product [] FetchProducts() throws InterruptedException, ExecutionException, UnknownHostException {
        ArrayList<Product> productsAL = db.FetchProducts();
        listofProducts = new Product[productsAL.size()];

        for (int i = 0; i < productsAL.size(); i++) {
            listofProducts[i] = productsAL.get(i);
        }

        return listofProducts;
    }

    public Report createReport(Date startDate, Date endDate) throws InterruptedException, ExecutionException, UnknownHostException, ParseException {
        DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
        int orderSize = db.FetchOrders().size();

        for(int i = 0; i < orderSize; i++) {


            Order sales = db.FetchOrders().get(i);
            Date result =  df.parse(sales.getDate());
            System.out.println(db.FetchOrders().get(i));
            if(startDate.equals(result) && endDate.equals())
        }

        List<Date> dates = new ArrayList<Date>(25);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        while (cal.getTime().before(endDate)) { // if or while?
            cal.add(Calendar.DATE, 1);
            dates.add(cal.getTime());
            System.out.println("Dates between span: " + " " + dates);
        }


        return dates;

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
