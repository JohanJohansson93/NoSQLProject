
/**
 * Created by Johan on 2017-05-23.
 * Class Stock
 * Keeps track of available quantity for each
 * ingredient.
 */
public class Stock {

    private String Name;
    private int Amount;

    /**
     * Creates a new Stock object that represents an ingredient
     * @param name the name of the ingredient
     * @param amount the quantity of the ingredient
     */
    public Stock(String name, int amount) {
        this.Name = name;
        this.Amount = amount;
    }

    public Stock() {

    }

    /**
     * getName
     * @return the name
     */
    public String getName() {
        return Name;
    }

    /**
     * setName
     * @param name sets the name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * getAmount
     * @return the amount
     */
    public int getAmount() {
        return Amount;
    }

    /**
     * setAmount
     * @param amount sets the amount
     */
    public void setAmount(int amount) {
        Amount = amount;
    }


}
