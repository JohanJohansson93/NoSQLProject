import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.ListKeys;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.mapreduce.BucketMapReduce;
import com.basho.riak.client.api.convert.JSONConverter;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.*;

import javax.xml.datatype.DatatypeConstants;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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



    public void CreateOrder(Order order) throws ExecutionException, InterruptedException {

            int key = GetKeys("Employees");

            bucket = new Namespace("maps", "Orders");
            location = new Location(bucket, Integer.toString(key));

            storeValue = new StoreValue.Builder(order)
                    .withLocation(location)
                    .build();

            client.execute(storeValue);
    }


    public void FetchOrders() throws ExecutionException, InterruptedException {

        bucket = new Namespace("maps", "Orders");
        ListKeys lk = new ListKeys.Builder(bucket).build();
        ListKeys.Response response = client.execute(lk);

        for (Location l: response
                ) {
            location = new Location(bucket, l.getKeyAsString());
            fetchValue = new FetchValue.Builder(location)
                    .build();
            RiakObject productObject = client.execute(fetchValue).getValue(RiakObject.class);
            System.out.println(productObject.getValue());
        }

    }

    public void DeleteOrder() throws ExecutionException, InterruptedException {
        bucket = new Namespace("maps", "Employees");
        location = new Location(bucket, "4");
        DeleteValue deleteOp = new DeleteValue.Builder(location)
                .build();
        client.execute(deleteOp);
        System.out.println("Post Deleted");
    }

    public void CreateProducts(Product [] products) throws UnknownHostException, ExecutionException, InterruptedException {
        
        bucket = new Namespace("maps", "Productsv2.0");

        for (Product items: products) {
            System.out.println(items.getName());

            location = new Location(bucket, items.getName());
            storeValue = new StoreValue.Builder(items)
                    .withLocation(location)
                    .build();
            client.execute(storeValue);

        }

    }

    private int GetKeys(String buckettype) throws ExecutionException, InterruptedException {

        int key = 0;

        bucket = new Namespace("maps", buckettype);
        ListKeys lk = new ListKeys.Builder(bucket).build();
        ListKeys.Response response = client.execute(lk);

        for (Location l: response
             ) {
            key = Integer.parseInt(l.getKeyAsString()) + 1;
        }
        System.out.println(key);
        return key;
    }

    public ArrayList<Product> FetchProducts() throws UnknownHostException, ExecutionException, InterruptedException {

        RiakObject obj;
        ArrayList<Product> products = new ArrayList<Product>();

        bucket = new Namespace("maps", "Productsv2.0");
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
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).getName());
        }

        return products;

    }

    public void CreateEmplyoee(Employee employee) throws ExecutionException, InterruptedException, UnknownHostException {


        int key = GetKeys("Employees");

        bucket = new Namespace("maps", "Employees");
        location = new Location(bucket, Integer.toString(key));

        storeValue = new StoreValue.Builder(employee)
                .withLocation(location)
                .build();

        client.execute(storeValue);
    }

    public void CreateMember(Member member) throws ExecutionException, InterruptedException {

        int key = GetKeys("Members");

        bucket = new Namespace("maps", "Members");
        location = new Location(bucket, Integer.toString(key));

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
           db.FetchProducts();
          //  db.DeleteOrder();
           // db.CreateEmplyoee();
           // db.ShutDownCluster();
        }catch (Exception e){
            System.out.print(e);
        }

    }
}
