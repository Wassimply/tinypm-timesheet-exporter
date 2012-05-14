package pl.softwaremill.timesheet_exporter.transform;

import com.google.code.tinypmclient.User;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import pl.softwaremill.timesheet_exporter.datacollector.ActivityInIteration;

import java.util.Collection;

public class ActivityTransformer {

    private Collection<ActivityInIteration> activities;

    public ActivityTransformer(Collection<ActivityInIteration> activities) {
        this.activities = activities;
    }


    public Multimap<User, DataRow> transform() {

        TreeMultimap<User, DataRow> timesheets = TreeMultimap.create(new UserNameComparator(), new DataRowComparator());


        for (ActivityInIteration activity : activities) {
//            DataRow dataRow = new DataRow(activity.getUser().getName(), )
//            timesheets.put(activity.)
        }

        return timesheets;
    }
}
