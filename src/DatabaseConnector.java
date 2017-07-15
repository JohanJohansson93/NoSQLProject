import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.*;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.google.gson.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Johan on 2017-04-11.
 * The Database class that handles all operations to the Riak Database.
 */
public class DatabaseConnector {
    /*
        Declare instance variables.
     */
    private RiakCluster cluster;
    private Location location;
    private FetchValue fetchValue;
    private StoreValue storeValue;
    private RiakClient client;
    private Namespace bucket;

    /**
     * The constructor for the database.
     * This calls for the setUpCluster method and creates a new RiakClient instance.
     */
    public DatabaseConnector(){
        try {
            setUpCluster();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.client = new RiakClient(cluster);
    }

    /**
     * This method sets up the connection to the RiakNode.
     * @throws UnknownHostException
     */
    private void setUpCluster() throws UnknownHostException{
        RiakNode node = new RiakNode.Builder()
                .withRemoteAddress("127.0.0.1")
                .withRemotePort(8087)
                .build();

        cluster = new RiakCluster.Builder(node)
                .build();

        cluster.start();

        System.out.println("Cluster Started");
    }


    /**
     * This method takes an order object as argument and passes the object to the database.
     * @param order
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public boolean CreateOrder(Order order) throws ExecutionException, InterruptedException {

            boolean processed = false;

            try {

                bucket = new Namespace("maps", "OrderS");
                location = new Location(bucket, order.getDate());

                storeValue = new StoreValue.Builder(order)
                        .withLocation(location)
                        .build();

                client.execute(storeValue);


                fetchValue = new FetchValue.Builder(location).build();
                RiakObject fetchedObject = client.execute(fetchValue).getValue(RiakObject.class);

                processed = true;
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                processed = false;
            }


        return processed;
    }

    /**
     * A method that fetches all the orders from the database.
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws UnknownHostException
     */
    public ArrayList<Order> FetchOrders() throws ExecutionException, InterruptedException, UnknownHostException {

        ArrayList<Order> orders = new ArrayList<Order>();

        bucket = new Namespace("maps", "OrderS");
        ListKeys lk = new ListKeys.Builder(bucket).build();
        ListKeys.Response response = client.execute(lk);

        for (Location l: response) {
            location = new Location(bucket, l.getKeyAsString());
            fetchValue = new FetchValue.Builder(location)
                    .build();
            RiakObject orderObjects = client.execute(fetchValue).getValue(RiakObject.class);

            JsonObject o = new com.google.gson.JsonParser().parse(orderObjects.getValue().toString()).getAsJsonObject();
            String [] products = new String[1];
            products[0] = new String(o.get("products").toString());

            orders.add(new Order(o.get("price").getAsDouble(), o.get("transactionComplete").getAsBoolean(), products,(o.get("date").toString()),o.get("employeeID").getAsInt()));
        }
        return orders;
    }

    /**
     * This method stores all the products in the database.
     * @param products
     * @throws UnknownHostException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void CreateProducts(Product [] products) throws UnknownHostException, ExecutionException, InterruptedException {
        
        bucket = new Namespace("maps", "ProductS");

        for (Product items: products) {

            location = new Location(bucket, items.getName());
            storeValue = new StoreValue.Builder(items)
                    .withLocation(location)
                    .build();
            client.execute(storeValue);
        }
    }

    /**
     * A method that fills the specified bucket with Stock objects.
     * @param stock
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void FillStock(Stock [] stock) throws ExecutionException, InterruptedException {

        bucket = new Namespace("maps", "StockObjectS");

        for (Stock items: stock) {
            System.out.println("DB FillStock: " + items.getName());
            location = new Location(bucket, items.getName());
            storeValue = new StoreValue.Builder(items)
                    .withLocation(location)
                    .build();
            client.execute(storeValue);
        }
    }

    /**
     * A method that returns all stock objects from the database.
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public ArrayList<Stock> FetchStock() throws ExecutionException, InterruptedException {
        RiakObject obj;
        ArrayList<Stock> ingredients = new ArrayList<Stock>();

        bucket = new Namespace("maps", "StockObjectS");
        ListKeys lk = new ListKeys.Builder(bucket).build();
        ListKeys.Response response = client.execute(lk);

        for (Location l: response){
            location = new Location(bucket, l.getKeyAsString());
            fetchValue = new FetchValue.Builder(location)
                    .build();
            FetchValue.Response res = client.execute(fetchValue);

            obj = res.getValue(RiakObject.class);
            JsonObject o = new com.google.gson.JsonParser().parse(obj.getValue().toString()).getAsJsonObject();
            System.out.println("DB FetchStock: " + o);

            ingredients.add(new Stock(o.get("name").toString(), o.get("amount").getAsInt()));
        }

        return ingredients;

    }

    /**
     * A method that updates one or more Stock objects in the database.
     * @param ingredients
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void UpdateStock(ArrayList<String> ingredients) throws ExecutionException, InterruptedException {

        RiakObject obj;

        bucket = new Namespace("maps", "StockObjectS");

        for (int i = 0; i < ingredients.size(); i++) {

            System.out.println("DB: UpdateStock " + ingredients.get(i));

            location = new Location(bucket,ingredients.get(i));

            fetchValue = new FetchValue.Builder(location)
                    .build();
            FetchValue.Response res = client.execute(fetchValue);

            obj = res.getValue(RiakObject.class);

            JsonObject o = new com.google.gson.JsonParser().parse(obj.getValue().toString()).getAsJsonObject();

            int total = o.get("amount").getAsInt() - 1;


            UpdateValue updateOp = new UpdateValue.Builder(location)
                    .withFetchOption(FetchValue.Option.DELETED_VCLOCK, true)
                    .withUpdate(new UpdateStock(o.get("name").toString(),total))
                    .build();
            UpdateValue.Response response = client.execute(updateOp);

            System.out.println("DB: ingredient changed " + total);

        }

    }

    /**
     * A method that returns all products in the database.
     * @return
     * @throws UnknownHostException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public ArrayList<Product> FetchProducts() throws UnknownHostException, ExecutionException, InterruptedException {

        RiakObject obj;
        ArrayList<Product> products = new ArrayList<Product>();

        bucket = new Namespace("maps", "ProductS");
        ListKeys lk = new ListKeys.Builder(bucket).build();
        ListKeys.Response response = client.execute(lk);

        for (Location l: response
             ) {
            location = new Location(bucket, l.getKeyAsString());
            fetchValue = new FetchValue.Builder(location)
                    .build();
            FetchValue.Response res = client.execute(fetchValue);

            obj = res.getValue(RiakObject.class);

            JsonObject o = new com.google.gson.JsonParser().parse(obj.getValue().toString()).getAsJsonObject();

                String [] str = new String[1];
                str[0] = new String(o.get("stock").toString());

                products.add(new Product(o.get("name").getAsString(), o.get("pounds").getAsDouble(), o.get("dollars").getAsDouble(),
                        o.get("crowns").getAsDouble(), str));
        }
        return products;
    }

    /**
     * Method that takes an employee object as argument and passes it to the database.
     * @param employee
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws UnknownHostException
     */
    public void CreateEmplyoee(Employee employee) throws ExecutionException, InterruptedException, UnknownHostException {

        bucket = new Namespace("maps", "EmployeesTestS");
        location = new Location(bucket, employee.getName());

        storeValue = new StoreValue.Builder(employee)
                .withLocation(location)
                .build();

        client.execute(storeValue);
    }

    /**
     * Method that takes a member object as argument and passes it to the database.
     * @param member
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void CreateMember(Member member) throws ExecutionException, InterruptedException {

        bucket = new Namespace("maps", "MemberS");
        location = new Location(bucket, Integer.toString(member.getSSN()));

        storeValue = new StoreValue.Builder(member)
                .withLocation(location)
                .build();

        client.execute(storeValue);
        System.out.println("DB: Member stored");
    }

    /**
     * Method that shutdown the connection to the database.
     * @throws UnknownHostException
     */
    public void ShutDownCluster() throws UnknownHostException {
        cluster.shutdown();
        System.out.println("Cluster shutdown");
    }

    public static void main(String [] args){
        try{
            DatabaseConnector db = new DatabaseConnector();
           // db.GetKeys("Employees");
          // db.FetchProducts();
          //  db.DeleteOrder();
           // db.CreateEmplyoee();
           // db.FetchStock();
            db.FetchOrders();
            db.ShutDownCluster();
        }catch (Exception e){
            System.out.print(e);
        }

    }
}
