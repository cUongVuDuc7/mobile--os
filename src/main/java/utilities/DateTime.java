package utilities;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTime {
    public static String getCurrentDate() {
        Date date = new Date();
        return date.toString().replace(":", "_").replace(" ", "_");
    }
    public static String getCurrentDateTime(String formatTime) {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(formatTime);
        return formatter.format(now);
    }
    public static int getSeconds(String timePlay) {
        int totalSeconds = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.parse("00:" + timePlay, formatter);
        System.out.println("Converted time: " + time); // Output: 15:30
        totalSeconds += time.getSecond();
        totalSeconds += (time.getMinute() * 60);
        return totalSeconds;
    }

}
