package org.Eleks.Gmail.po;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateTimeHelper extends  MailSendPage {


    //todo change to local date time
    static String convertDate(String date) {
        //take gmail date in format "EEEE, MMM d, yyyy, HH:mm a" to the "yyyy-MM-dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date(date));
    }
    static String getEmailDateTime(WebElement element) {
        return element.getAttribute("title");
    }
    public static LocalDateTime parseDateTime(String dateTimeFromTitle) {
//parseDateTimeFromTitle
        return LocalDateTime.parse(dateTimeFromTitle,
                DateTimeFormatter.ofPattern("E, MMM d, y, h:mm a", Locale.US));
    }
    public String getDeleteEmailTime() {
        return DateTimeHelper.getEmailDateTime(getEmailForDelete());
    }

    public String getLatestEmailTime() {
        return DateTimeHelper.getEmailDateTime(getEmailDataElement());
    }



}
