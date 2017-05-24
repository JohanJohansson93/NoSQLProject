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

    public Controller(GUI GUI){
        this.gui = GUI;
        db = new DatabaseConnector();
    }


    public void CreateOrder(Order order){
        Date date;

    }

    public void FetchOrders() throws ExecutionException, InterruptedException {
        db.FetchOrders();
    }

    public void DeleteOrder(){

    }

    public void CreateProducts(Product [] products){

    }

    public void FetchProducts() throws InterruptedException, ExecutionException, UnknownHostException {
        db.FetchProducts();

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
