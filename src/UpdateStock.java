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

    /**
     * Creates a new UpdateStock object that represents
     * an updated stock item
     * @param name the name to be updated
     * @param amount the quantity to be updated
     */
    public UpdateStock(String name, int amount) {
        this.newname = name;
        this.newamount = amount;
    }

    /**
     * apply method, takes an stock parameter
     * and updates the new amount and name
     * @param newstock
     * @return an updated stock item
     */
    @Override
    public Stock apply(Stock newstock) {
        newstock.setAmount(newamount);
        newstock.setName(newname);
        System.out.println(newstock.getName());
        return newstock;
    }
}
