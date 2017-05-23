/**
 * Created by Johan on 2017-05-23.
 */
public class Stock {

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
}
