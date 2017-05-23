import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
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
    private Namespace Bucket;


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

            Bucket = new Namespace("maps", "Orders");
            location = new Location(Bucket, "OrderID");

            storeValue = new StoreValue.Builder(order)
                    .withLocation(location)
                    .build();

            client.execute(storeValue);
    }


    public void UpdateOrder(){



    }

    public void DeleteOrder(){

    }

    public void CreateProducts(ArrayList products) throws UnknownHostException, ExecutionException, InterruptedException {


        Bucket = new Namespace("maps", "products");
        location = new Location(Bucket, "ProductID");


        for (Object items: products) {
            storeValue = new StoreValue.Builder(items)
                    .withLocation(location)
                    .build();
        }

        client.execute(storeValue);
    }

    public void FetchProducts() throws UnknownHostException, ExecutionException, InterruptedException {

        Bucket = new Namespace("maps", "Employees");
        location = new Location(Bucket, "9");
        fetchValue = new FetchValue.Builder(location)
                .build();
        RiakObject productObject = client.execute(fetchValue).getValue(RiakObject.class);
        System.out.println(productObject.getValue());

    }

    public void CreateEmplyoee(Employee emp) throws ExecutionException, InterruptedException, UnknownHostException {


        Bucket = new Namespace("maps", "Employees");
        location = new Location(Bucket, "9");

        storeValue = new StoreValue.Builder(emp)
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
            //db.CreateEmplyoee();
            db.FetchProducts();
            db.ShutDownCluster();
        }catch (Exception e){
            System.out.print(e);
        }

    }
}
