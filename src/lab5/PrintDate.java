package lab5;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Prints the current date and time in french.
 */
public class PrintDate {
    public static void main(String[] args) {
        Date date = new Date();
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.FRANCE);
        System.out.println(df.format(date));
    }
}
