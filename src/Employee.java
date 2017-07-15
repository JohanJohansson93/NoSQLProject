
/**
 * Created by Johan on 2017-05-23.
 * Class Employee
 * An employee with the properties of name, profession,
 * start and end date, comment and worktime.
 */
public class Employee {

    /*
        Declare instance variables.
     */
    private String Name, Start_date, End_date, Type, Comment;
    private int Worktime;
    /*
        This constructor takes several arguments which is the data for the employee.
     */
    public Employee(String name, String type, String Sdate, String Edate, String comment, int worktime) {
        this.Name = name;
        this.Type = type;
        this.Start_date = Sdate;
        this.End_date = Edate;
        this.Comment = comment;
        this.Worktime = worktime;
    }
    /*
        Method that returns the Name.
     */
    public String getName() {
        return Name;
    }
    /*
        Method that sets the Name.
     */
    public void setName(String name) {
        Name = name;
    }
    /*
       This method returns the Start_date.
     */
    public String getStart_date() {
        return Start_date;
    }
    /*
       This method sets the Start_date.
     */
    public void setStart_date(String start_date) {
        Start_date = start_date;
    }
    /*
       This method returns the End_date.
     */
    public String getEnd_date() {
        return End_date;
    }
    /*
       Method that sets the End_date.
     */
    public void setEnd_date(String end_date) {
        End_date = end_date;
    }
    /*
       Method that returns the type.
     */
    public String getType() {
        return Type;
    }
    /*
       Method that sets the type.
     */
    public void setType(String type) {
        Type = type;
    }
    /*
       Method that returns the comment.
     */
    public String getComment() {
        return Comment;
    }
    /*
        Method that sets the comment.
     */
    public void setComment(String comment) {
        Comment = comment;
    }
    /*
       Method that returns worktime.
     */
    public int getWorktime() {
        return Worktime;
    }
    /*
       This method returns the worktime.
     */
    public void setWorktime(int worktime) {
        Worktime = worktime;
    }
}
