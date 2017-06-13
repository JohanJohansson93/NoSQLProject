import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;

import java.util.*;

/**
 * Created by Johan on 2017-05-23.
 */


public class Report {
    private Date StartDate;
    private Date EndDate;

    public Report (Date startDate, Date endDate) {
        this.StartDate = startDate;
        this.EndDate = endDate;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }
}

