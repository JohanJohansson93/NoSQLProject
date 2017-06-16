
/**
 * Created by Johan on 2017-05-23.
 */
public class Employee {

    private String  Name, Start_date, End_date, Type, Comment;
    private int Worktime;

    public Employee(String name, String type, String Sdate, String Edate, String comment, int worktime){
        this.Name = name;
        this.Type = type;
        this.Start_date = Sdate;
        this.End_date = Edate;
        this.Comment = comment;
        this.Worktime = worktime;
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

    public String getComment() {
        return Comment;
    }
    public void setComment(String comment) {
        Comment = comment;
    }

    public int getWorktime() {
        return Worktime;
    }

    public void setWorktime(int worktime) {
        Worktime = worktime;
    }
}
