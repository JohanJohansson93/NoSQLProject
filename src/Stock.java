
/**
 * Created by Johan on 2017-05-23.
 * Class Stock
 * Keeps track of available quantity for each
 * product.
 */
public class Stock {

    private String Name;
    private int Amount;

    public Stock(String name, int amount) {
        this.Name = name;
        this.Amount = amount;
    }

    public Stock() {

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


}
