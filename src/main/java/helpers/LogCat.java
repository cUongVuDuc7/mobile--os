package helpers;
import java.io.*;
import static helpers.PathHelper.projectPath;

public class LogCat {

    public static void removeLog() {
        String cmd = "adb logcat -c";
        try {
            Runtime.getRuntime().exec(cmd);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getLog() {
        try {
            File filename = new File(projectPath + "mylog.txt");
            FileWriter fileWriter = new FileWriter(filename);
            String cmd = "adb logcat -d >" + "mylog.txt";
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                fileWriter.write(line + "\n");
                fileWriter.flush();
                System.out.println(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
