package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static core.BaseTest.driver;

public class ScreenShot {
    public static final String PATH_TO_IMG = System.getProperty("user.dir") + File.separator + "img" + File.separator;
    public void takeScreenshot(String filename, String name) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String fileName = filename + "-" + now.format(formatter) + ".png";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(PATH_TO_IMG + fileName));
    }
}
