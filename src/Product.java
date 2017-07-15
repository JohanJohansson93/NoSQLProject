
/**
 * Created by Johan on 2017-05-23.
 * Class Product
 * A class for a product.
 * Regarding the currencies, priceInPounds is the only one in use.
 */

public class Product {
    /*
        Declare instance variables.
    */
    private String Name;
    private double pounds, dollars, crowns;
    private String[] string;
    /*
       Constructor that takes several arguments for the Product Object.
     */
    public Product(String name, double PriceinPounds, double PriceinDollars, double PriceinCrowns, String[] string) {
        this.Name = name;
        this.pounds = PriceinPounds;
        this.dollars = PriceinDollars;
        this.crowns = PriceinCrowns;
        this.string = string;
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
       This method returns the pounds.
     */
    public double getPounds() {
        return pounds;
    }
    /*
       Method that sets the pounds.
     */
    public void setPounds(double pounds) {
        this.pounds = pounds;
    }
    /*
       Method that returns the dollars.
     */
    public double getDollars() {
        return dollars;
    }
    /*
       Method that sets the dollars.
     */
    public void setDollars(double dollars) {
        this.dollars = dollars;
    }
    /*
       This method returns the crowns.
     */
    public double getCrowns() {
        return crowns;
    }
    /*
       Method sets the crowns.
     */
    public void setCrowns(double crowns) {
        this.crowns = crowns;
    }
    /*
       Method that returns the stock array.
     */
    public String[] getStock() {
        return string;
    }
    /*
        This method sets the array.
     */
    public void setString(String[] string) {
        this.string = string;
    }
}
