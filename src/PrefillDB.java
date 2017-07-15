import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Johan on 2017-07-15.
 * This class is used to prefill the database.
 */
public class PrefillDB {

    public static void main(String [] args){
        Controller controller = new Controller();
        try {
            controller.Fillproducts();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        } catch (UnknownHostException e3) {
            e3.printStackTrace();
        }
    }
}
