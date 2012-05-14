package pl.softwaremill.timesheet_exporter.transform;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static String formatDate(Date date) {

        if (date == null) {
            return "";
        }
        return formatter.format(date);
    }
}
