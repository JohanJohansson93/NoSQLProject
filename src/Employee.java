import java.util.Date;

/**
 * Created by Johan on 2017-05-23.
 */
public class Employee {
    private String  Name;
    private Date Start_date;
    private Date    End_date;
    private String Type;


    public Employee(String name,String role){
        this.Name = name;
        this.Type = role;
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

    /*

    public Comment getComment() {
        return Comment;
    }

    public void setComment(Comment comment) {
        Comment = comment;
    }

    */

    public String isType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }


}
