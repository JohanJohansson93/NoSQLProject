import java.net.UnknownHostException;
import java.util.Date;

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

    public void FetchOrders(){

    }

    public void DeleteOrder(){

    }

    public void CreateProducts(){

    }

    public void FetchProducts(){

    }

    public void CreateEmployee(Employee employee){

    }

    public void CreateMember(Member member){

    }

    public void ShutdownDB() throws UnknownHostException {
        db.ShutDownCluster();
    }


}
