package lab5;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Outputs the date or time depending on input.
 * Created by axel on 2016-04-29.
 */
public class TimeServer2 {
    public static void main(String[] args) {

        while (true){
            System.out.print("Enter either 'date' or 'time':");
            Scanner scan = new Scanner(System.in);
            String command = scan.nextLine();
            Date date = new Date();
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
