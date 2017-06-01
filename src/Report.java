import java.util.*;

/**
 * Created by Johan on 2017-05-23.
 */


public class Report {
    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {

        // En början iaf...
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate))
        {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
    public
}

