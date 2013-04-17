package pl.softwaremill.timesheet_exporter.transform;

import com.google.code.tinypmclient.User;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import pl.softwaremill.timesheet_exporter.datacollector.ActivityInIteration;

import java.util.Collection;

public class DataTransfomer {

    private Collection<ActivityInIteration> activities;
    private String dateFormat;

    public DataTransfomer(Collection<ActivityInIteration> activities, String dateFormat) {
        this.activities = activities;
        this.dateFormat = dateFormat;
    }

    public Multimap<User, DataRow> transform() {

        TreeMultimap<User, DataRow> timesheets = TreeMultimap.create(new UserNameComparator(), new DataRowComparator());

        for (ActivityInIteration activity : activities) {
            DataRow dataRow = new DataRow(activity.getUser().getName(), activity.getIteration().getProject().getName(),
                    activity.getUserStory().getName(), activity.getTask().getName(), activity.getDate(), activity.getTimeSpent(),
                    activity.getUserStory().getEstimatedEffort(), activity.getTask().getEstimatedEffort(), dateFormat);

            timesheets.put(activity.getUser(), dataRow);
        }

        return timesheets;
    }
}
