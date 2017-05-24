import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.ListKeys;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.mapreduce.BucketMapReduce;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;


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
        location = new Location(bucket, "10");
        DeleteValue deleteOp = new DeleteValue.Builder(location)
                .build();
        client.execute(deleteOp);
        System.out.println("Post Deleted");
    }

    public void CreateProducts(ArrayList products) throws UnknownHostException, ExecutionException, InterruptedException {


        bucket = new Namespace("maps", "products");
        location = new Location(bucket, "ProductID");


        for (Object items: products) {
            storeValue = new StoreValue.Builder(items)
                    .withLocation(location)
                    .build();
        }

        client.execute(storeValue);
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

    public void FetchProducts() throws UnknownHostException, ExecutionException, InterruptedException {

        bucket = new Namespace("maps", "Employees");
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

    public void CreateEmplyoee(Employee emp) throws ExecutionException, InterruptedException, UnknownHostException {


        int key = GetKeys("Employees");

        bucket = new Namespace("maps", "Employees");
        location = new Location(bucket, Integer.toString(key));

        storeValue = new StoreValue.Builder(emp)
                .withLocation(location)
                .build();

        client.execute(storeValue);
    }

    public void CreateMember(Member mem) throws ExecutionException, InterruptedException {

        int key = GetKeys("Members");

        bucket = new Namespace("maps", "Members");
        location = new Location(bucket, Integer.toString(key));

        storeValue = new StoreValue.Builder(mem)
                .withLocation(location)
                .build();

        client.execute(storeValue);
    }




    public void ShutDownCluster() throws UnknownHostException {
        cluster.shutdown();
        System.out.println("Cluster shutdown succesfully");
    }

    public static void main(String [] args){
        try{
            DatabaseConnector db = new DatabaseConnector();
           // db.GetKeys("Employees");
           db.FetchProducts();
           // db.DeleteOrder();
           // db.CreateEmplyoee();
            db.ShutDownCluster();
        }catch (Exception e){
            System.out.print(e);
        }

    }
}
