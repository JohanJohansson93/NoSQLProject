/**
 * Created by Johan on 2017-05-23.
 */
public class Employee {
    private String  Name;
    private Date    Start_date;
    private Date    End_date;
    private double  Employment_percent;
    private Comment CommentList; // Most likely,change this
    private boolean Type;
    private int     OrderId;


    public Employee(String name, Date start_date, Date end_date, double employment_percent, Comment comment, boolean type, int orderId){
        this.Name = name;
        this.Start_date = start_date;
        this.End_date = end_date;
        this.Employment_percent = employment_percent;
        this.Comment = comment;
        this.Type = type;
        this.OrderId = orderId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getStart_date() {
        return Start_date;
    }

    public void setStart_date(Date start_date) {
        Start_date = start_date;
    }

    public Date getEnd_date() {
        return End_date;
    }

    public void setEnd_date(Date end_date) {
        End_date = end_date;
    }

    public double getEmployment_percent() {
        return Employment_percent;
    }

    public void setEmployment_percent(double employment_percent) {
        Employment_percent = employment_percent;
    }

    public Comment getComment() {
        return Comment;
    }

    public void setComment(Comment comment) {
        Comment = comment;
    }

    public boolean isType() {
        return Type;
    }

    public void setType(boolean type) {
        Type = type;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }
}
