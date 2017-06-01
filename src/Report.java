import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;

import java.util.*;

/**
 * Created by Johan on 2017-05-23.
 */


public class Report {
    public List<Date> getDatesBetweenSpan(Date startDate,Date endDate) {


        List<Date> dates = new ArrayList<Date>(25);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        while (cal.getTime().before(endDate)) { // if or while?
            cal.add(Calendar.DATE, 1);
            dates.add(cal.getTime());
            System.out.println("Dates between span: " + " " + dates);
        }

        return dates;
    }

}

