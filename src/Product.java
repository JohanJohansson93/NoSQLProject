
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


    /**
     * Creates a new product object, representing a product
     * @param name of product
     * @param PriceinPounds
     * @param PriceinDollars
     * @param PriceinCrowns
     * @param string array with stock names
     */
    public Product(String name, double PriceinPounds, double PriceinDollars, double PriceinCrowns, String[] string) {
        this.Name = name;
        this.pounds = PriceinPounds;
        this.dollars = PriceinDollars;
        this.crowns = PriceinCrowns;
        this.string = string;
    }


    /**
     * getName
     * @return a name
     */
    public String getName() {
        return Name;
    }


    /**
     * setName
     * @param name sets a product name
     */
    public void setName(String name) {
        Name = name;
    }


    /**
     * getPounds
     * @return pound currency
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


    /**
     * getStock
     * @return stock names
     */
    public String[] getStock() {
        return string;
    }


    /**
     * setString
     * @param string sets stock name
     */
    public void setString(String[] string) {
        this.string = string;
    }
}
