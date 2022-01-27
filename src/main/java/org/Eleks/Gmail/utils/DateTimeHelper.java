package org.Eleks.Gmail.utils;

import org.Eleks.Gmail.factories.DriverFactory;
import org.Eleks.Gmail.po.MailSendPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateTimeHelper {


    public static String convertDate(String date) {
        //take gmail date in format "EEEE, MMM d, yyyy, HH:mm a" to the "yyyy-MM-dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date(date));
    }

    public static String getEmailDateTime(WebElement element) {
        return new WebDriverWait(DriverFactory.getWebDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(element)).getAttribute("title");
    }

    public static LocalDateTime parseDateTime(String dateTimeFromTitle) {
//parseDateTimeFromTitle
        return LocalDateTime.parse(dateTimeFromTitle,
                DateTimeFormatter.ofPattern("E, MMM d, y, h:mm a", Locale.US));
    }


    public String getLatestEmailTime() {
//        return DateTimeHelper.getEmailDateTime(getEmailDataElement());
        return new MailSendPage().getTimeOfEmailElement();
    }


}
