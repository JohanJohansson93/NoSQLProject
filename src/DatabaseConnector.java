import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.*;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;
import com.google.gson.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Johan on 2017-04-11.
 */
public class DatabaseConnector {

    private RiakCluster cluster;
    private Location location;
    private FetchValue fetchValue;
    private StoreValue storeValue;
    private RiakClient client;
    private Namespace bucket;


    public DatabaseConnector(){
        try {
            setUpCluster();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.client = new RiakClient(cluster);
    }

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



    public boolean CreateOrder(Order order) throws ExecutionException, InterruptedException {

            boolean processed = false;

            try {

                bucket = new Namespace("maps", "Ordersv5");
                location = new Location(bucket, order.getDate());

                storeValue = new StoreValue.Builder(order)
                        .withLocation(location)
                        .build();

                client.execute(storeValue);


                fetchValue = new FetchValue.Builder(location).build();
                RiakObject fetchedObject = client.execute(fetchValue).getValue(RiakObject.class);
                System.out.println("DB: Object placed in DB: " + fetchedObject.getValue());

                processed = true;
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                processed = false;
            }
        System.out.println(processed);


        return processed;
    }


    public ArrayList<Order> FetchOrders() throws ExecutionException, InterruptedException, UnknownHostException {

        ArrayList<Order> orders = new ArrayList<Order>();

        bucket = new Namespace("maps", "Orders");
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

    public void CreateProducts(Product [] products) throws UnknownHostException, ExecutionException, InterruptedException {
        
        bucket = new Namespace("maps", "Productsv2.2");

        for (Product items: products) {

            location = new Location(bucket, items.getName());
            storeValue = new StoreValue.Builder(items)
                    .withLocation(location)
                    .build();
            client.execute(storeValue);
        }
    }

    public void FillStock(Stock [] stock) throws ExecutionException, InterruptedException {

        bucket = new Namespace("maps", "Stockv2");

        for (Stock items: stock) {

            location = new Location(bucket, items.getName());
            storeValue = new StoreValue.Builder(items)
                    .withLocation(location)
                    .build();
            client.execute(storeValue);
        }
    }

    public ArrayList<Stock> FetchStock() throws ExecutionException, InterruptedException {
        RiakObject obj;
        ArrayList<Stock> ingredients = new ArrayList<Stock>();

        bucket = new Namespace("maps", "Stockv2");
        ListKeys lk = new ListKeys.Builder(bucket).build();
        ListKeys.Response response = client.execute(lk);

        for (Location l: response){
            location = new Location(bucket, l.getKeyAsString());
            fetchValue = new FetchValue.Builder(location)
                    .build();
            FetchValue.Response res = client.execute(fetchValue);

            obj = res.getValue(RiakObject.class);
            System.out.println(obj.getValue());
            JsonObject o = new com.google.gson.JsonParser().parse(obj.getValue().toString()).getAsJsonObject();

            ingredients.add(new Stock(o.get("name").toString(), o.get("amount").getAsInt()));
        }

        return ingredients;

    }
    /*
        Utgå från denna sida: http://docs.basho.com/riak/kv/2.2.3/developing/usage/updating-objects/ för att uppdatera.
     */
    public void UpdateStock(ArrayList<Stock> ingredients) throws ExecutionException, InterruptedException {

        bucket = new Namespace("maps", "Stockv2");


        for (int i = 0; i < ingredients.size(); i++) {
            System.out.println("Ingredient:" + ingredients.get(i));

            location = new Location(bucket, ingredients.get(i).getName());


            int total = ingredients.get(i).getAmount() - 1;

            System.out.println("DB: ingredient changed " + total);

            Stock stock = new Stock(ingredients.get(i).getName(),total);


            UpdateValue updateOp = new UpdateValue.Builder(location)
                    .withUpdate(stock)
                    .build();
            UpdateValue.Response response = client.execute(updateOp);
        }

    }

    public ArrayList<Product> FetchProducts() throws UnknownHostException, ExecutionException, InterruptedException {

        RiakObject obj;
        ArrayList<Product> products = new ArrayList<Product>();

        bucket = new Namespace("maps", "Productsv2.1");
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

    public void CreateEmplyoee(Employee employee) throws ExecutionException, InterruptedException, UnknownHostException {

        bucket = new Namespace("maps", "Employees");
        location = new Location(bucket, employee.getName());

        storeValue = new StoreValue.Builder(employee)
                .withLocation(location)
                .build();

        client.execute(storeValue);
    }

    public void CreateMember(Member member) throws ExecutionException, InterruptedException {

        bucket = new Namespace("maps", "Members");
        location = new Location(bucket, Integer.toString(member.getSSN()));

        storeValue = new StoreValue.Builder(member)
                .withLocation(location)
                .build();

        client.execute(storeValue);
    }

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
            db.FetchStock();
            db.ShutDownCluster();
        }catch (Exception e){
            System.out.print(e);
        }

    }
}
