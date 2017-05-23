import java.net.UnknownHostException;

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


    public void CreatePost(){

    }

    public void UpdatePost(){

    }

    public void DeletePost(){

    }

    public void ShutdownDB() throws UnknownHostException {
        db.ShutDownCluster();
    }


}
