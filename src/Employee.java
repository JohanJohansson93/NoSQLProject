
/**
 * Created by Johan on 2017-05-23.
 */
public class Employee {

    private String  Name, Start_date, End_date, Type;
    private int [] OrderID;


    public Employee(String name,String type, String Sdate, String Edate, int [] orderID){
        this.Name = name;
        this.Type = type;
        this.Start_date = Sdate;
        this.End_date = Edate;
        this.OrderID = orderID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStart_date() {
        return Start_date;
    }

    public void setStart_date(String start_date) {
        Start_date = start_date;
    }

    public String getEnd_date() {
        return End_date;
    }

    public void setEnd_date(String end_date) {
        End_date = end_date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int[] getOrderID() {
        return OrderID;
    }

    public void setOrderID(int[] orderID) {
        OrderID = orderID;
    }
}
