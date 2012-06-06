package pl.softwaremill.timesheet_exporter.printer;

import com.google.code.tinypmclient.User;
import com.google.common.collect.Multimap;
import pl.softwaremill.timesheet_exporter.settings.ExporterSettings;
import pl.softwaremill.timesheet_exporter.transform.DataRow;

import java.util.Set;

public class ConsoleReportPrinter implements IReportPrinter {

    private ExporterSettings settings;

    public ConsoleReportPrinter(ExporterSettings settings) {
        this.settings = settings;
    }

    @Override
    public void printReport(Multimap<User, DataRow> reportData) {

        System.out.println(ToCsv.getColumns(settings.getFields()));

        Set<User> users = reportData.keySet();

        float totalSum = 0f;

        for (User user : users) {

            for (DataRow dataRow : reportData.get(user)) {
                System.out.println(ToCsv.toCSV(dataRow, settings.getFields()));
                totalSum += dataRow.getTimeSpent();
            }
        }

        if (settings.getSum()) {
            System.out.println("Sum = " + totalSum);
        }
    }
}
