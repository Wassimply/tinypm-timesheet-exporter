package pl.softwaremill.timesheet_exporter.settings;

import com.beust.jcommander.ParameterException;
import org.testng.annotations.Test;
import pl.softwaremill.timesheet_exporter.transform.DateUtil;

public class ExporterSettingsShould {

    @Test
    public void passValidationWithMonthSpecified() {

        // given
        ExporterSettings settings = new ExporterSettings();
        settings.setMonth(10);

        // when
        settings.customValidation();
    }

    @Test
    public void passValidationWithTwoDatesSpecified() {

        // given
        ExporterSettings settings = new ExporterSettings();
        settings.setDateFrom(DateUtil.createDate(2012, 01, 10));
        settings.setDateTo(DateUtil.createDate(2012, 01, 27));

        // when
        settings.customValidation();
    }

    @Test(expectedExceptions = ParameterException.class,
            expectedExceptionsMessageRegExp = "Either -month or pair -dateFrom and -dateTo must be provided")
    public void throwExceptionWhenMonthAndDatesProvided() {

        // given
        ExporterSettings settings = new ExporterSettings();
        settings.setMonth(1);
        settings.setDateFrom(DateUtil.createDate(2012, 01, 10));
        settings.setDateTo(DateUtil.createDate(2012, 01, 27));

        // when
        settings.customValidation();
    }

    @Test(expectedExceptions = ParameterException.class,
            expectedExceptionsMessageRegExp = "Either -month or pair -dateFrom and -dateTo must be provided")
    public void throwExceptionWhenNoDateSettingsProvided() {

        // given
        ExporterSettings settings = new ExporterSettings();

        // when
        settings.customValidation();
    }

    @Test(expectedExceptions = ParameterException.class,
            expectedExceptionsMessageRegExp = "Value of -dateTo can not be before -dateFrom")
    public void throwExceptionWhenOnlyOneDateIsProvided() {

        // given
        ExporterSettings settings = new ExporterSettings();
        settings.setDateFrom(DateUtil.createDate(2012, 01, 28));
        settings.setDateTo(DateUtil.createDate(2012, 01, 27));

        // when
        settings.customValidation();
    }


}
