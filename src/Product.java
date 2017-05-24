/**
 * Created by Johan on 2017-05-23.
 */
public class Product {

    private String Name;
    private double pounds, dollars, crowns;
    private Stock [] stock;

    public Product(String name, double PriceinPounds, double PriceinDollars, double PriceinCrowns, Stock [] Stock){
        this.Name = name;
        this.pounds = PriceinPounds;
        this.dollars = PriceinDollars;
        this.crowns = PriceinCrowns;
        this.stock = Stock;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPounds() {
        return pounds;
    }

    public void setPounds(double pounds) {
        this.pounds = pounds;
    }

    public double getDollars() {
        return dollars;
    }

    public void setDollars(double dollars) {
        this.dollars = dollars;
    }

    public double getCrowns() {
        return crowns;
    }

    public void setCrowns(double crowns) {
        this.crowns = crowns;
    }

    public Stock[] getStock() {
        return stock;
    }

    public void setStock(Stock[] stock) {
        this.stock = stock;
    }
}
