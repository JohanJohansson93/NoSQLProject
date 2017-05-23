/**
 * Created by Johan on 2017-05-23.
 */
public class Product {

    private String Name;
    private double pounds, dollars, crowns;
    private int stockID;

    public Product(String name, double PriceinPounds, double PriceinDollars, double PriceinCrowns, int ID){
        this.Name = name;
        this.pounds = PriceinPounds;
        this.dollars = PriceinDollars;
        this.crowns = PriceinCrowns;
        this.stockID = ID;
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

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }
}
