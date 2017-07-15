
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


    /**
     * Creates a new Employee object, representing an employee
     * @param name of employee
     * @param type of work
     * @param Sdate start date
     * @param Edate end date
     * @param comment about employee
     * @param worktime of employee

     */
    public Employee(String name, String type, String Sdate, String Edate, String comment, int worktime) {
        this.Name = name;
        this.Type = type;
        this.Start_date = Sdate;
        this.End_date = Edate;
        this.Comment = comment;
        this.Worktime = worktime;
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
     * @param name sets a name
     */
    public void setName(String name) {
        Name = name;
    }


    /**
     * getStart_date
     * @return a start date
     */
    public String getStart_date() {
        return Start_date;
    }


    /**
     * setStart_date
     * @param start_date sets startdate
     */
    public void setStart_date(String start_date) {
        Start_date = start_date;
    }


    /**
     * getEnd_date
     * @return an end date
     */
    public String getEnd_date() {
        return End_date;
    }


    /**
     * setEnd_date
     * @param end_date sets an end date
     */
    public void setEnd_date(String end_date) {
        End_date = end_date;
    }


    /**
     * getType
     * @return a type of work
     */
    public String getType() {
        return Type;
    }


    /**
     * setType
     * @param type sets a type of work
     */
    public void setType(String type) {
        Type = type;
    }


    /**
     * getComment
     * @return a comment
     */
    public String getComment() {
        return Comment;
    }


    /**
     * setComment
     * @param comment sets a comment
     */
    public void setComment(String comment) {
        Comment = comment;
    }


    /**
     * getWorktime
     * @return workingtime for employee
     */
    public int getWorktime() {
        return Worktime;
    }


    /**
     * setWorktime
     * @param worktime sets workingtime for employee
     */
    public void setWorktime(int worktime) {
        Worktime = worktime;
    }
}
