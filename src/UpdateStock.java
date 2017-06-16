import com.basho.riak.client.api.commands.kv.UpdateValue;

/**
 * Created by Johan on 2017-06-11.
 * Class UpdateStock
 * To be able to update values with Riaks library method,
 * one has to use an abstract stock updater class.
 */
public class UpdateStock extends UpdateValue.Update<Stock> {

    private int newamount;
    private String newname;

    public UpdateStock(String name, int amount) {
        this.newname = name;
        this.newamount = amount;
        System.out.println("UpdateStock: ");
    }

    @Override
    public Stock apply(Stock newstock) {
        System.out.println("UpdateStock: apply");
        newstock.setAmount(newamount);
        newstock.setName(newname);
        System.out.println(newstock.getName());
        return newstock;
    }
}
