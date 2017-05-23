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



    public DatabaseConnector(){
        try {
            setUpCluster();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
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

    public RiakClient RiakClientObject() throws UnknownHostException {

        RiakClient client = new RiakClient(cluster);
        System.out.println("Client object successfully created");

        return client;
    }

    public void CreateOrder(Order order){

    }

    public void UpdateOrder(){



    }

    public void DeleteOrder(){

    }

    public void CreateProducts(ArrayList products) throws UnknownHostException, ExecutionException, InterruptedException {


        Namespace productsBucket = new Namespace("maps", "products");
        location = new Location(productsBucket, "ProductID");


        for (Object items: products) {
            storeValue = new StoreValue.Builder(items)
                    .withLocation(location)
                    .build();
        }

        RiakClient client = RiakClientObject();
        client.execute(storeValue);
    }

    public void FetchProducts() throws UnknownHostException, ExecutionException, InterruptedException {
        RiakClient client = RiakClientObject();
        Namespace productsBucket = new Namespace("maps", "Employees");
        location = new Location(productsBucket, "6");
        fetchValue = new FetchValue.Builder(location)
                .build();
        RiakObject productObject = client.execute(fetchValue).getValue(RiakObject.class);
        System.out.println(productObject.getValue());

    }

    public void CreateEmplyoee(Employee emp) throws ExecutionException, InterruptedException, UnknownHostException {

        Namespace emplyoeeBucket = new Namespace("maps", "Employees");
        location = new Location(emplyoeeBucket, "6");

        storeValue = new StoreValue.Builder(emp)
                .withLocation(location)
                .build();

        RiakClient client = RiakClientObject();
        client.execute(storeValue);
    }


    public void ShutDownCluster() throws UnknownHostException {
        cluster.shutdown();
    }

    public static void main(String [] args){
        try{
            DatabaseConnector db = new DatabaseConnector();
           //db.CreateEmplyoee();
           // db.FetchProducts();
        }catch (Exception e){
            System.out.print(e);
        }

    }
}
