
/**
 * Created by Johan on 2017-05-23.
 * Class Stock
 * Keeps track of available quantity for each
 * product.
 */
public class Stock {
    /*
       Declare instance variables.
     */
    private String Name;
    private int Amount;
    /*
       The constructor that takes name and amount as arguments.
     */
    public Stock(String name, int amount) {
        this.Name = name;
        this.Amount = amount;
    }

    public Stock() {

    }
    /*
       This method returns the name.
     */
    public String getName() {
        return Name;
    }
    /*
       Method that sets the name.
     */
    public void setName(String name) {
        Name = name;
    }
    /*
       Method that returns the amount.
     */
    public int getAmount() {
        return Amount;
    }
    /*
       Method that sets the amount.
     */
    public void setAmount(int amount) {
        Amount = amount;
    }


}
