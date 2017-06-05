import com.basho.riak.client.api.commands.kv.UpdateValue;

/**
 * Created by Johan on 2017-05-23.
 */
public class Stock extends UpdateValue.Update<Object> {

    private String Name;
    private int Amount;

    public Stock(String name, int amount){
        this.Name = name;
        this.Amount = amount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }


    @Override
    public Object apply(Object o) {
        return null;
    }
}
