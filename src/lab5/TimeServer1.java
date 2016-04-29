package lab5;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Outputs the date or time depending on input.
 */
public class TimeServer1 {
    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Enter either 'date' or 'time'");
        } else {
            Date date = new Date();
            String command = args[0];
            switch (command){
                case "date":
                    DateFormat df1 = DateFormat.getDateInstance();
                    System.out.println(df1.format(date));
                    break;
                case "time":
                    DateFormat df2 = DateFormat.getTimeInstance();
                    System.out.println(df2.format(date));
                    break;
                default:
                    System.out.println("That is not a valid command.");
                    break;
            }
        }
    }
}
