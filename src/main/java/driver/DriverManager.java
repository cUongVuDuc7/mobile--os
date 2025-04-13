package driver;

import io.appium.java_client.CommandExecutionHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.SessionId;

public class DriverManager {
    private static final ThreadLocal<IOSDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }
    public static IOSDriver getDriver() {
        return driver.get();
    }
    public static void setDriver(IOSDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void quit() {
        DriverManager.driver.get().quit();
        driver.remove();
    }
    public static void checkSession() {
        System.out.println("Session ID: ");
        if (driver.get() != null) {
            SessionId sessionId = driver.get().getSessionId();
            if (sessionId != null) {
                String sessionInfo = CommandExecutionHelper.execute(driver.get(), "/session/" + sessionId);
                System.out.println("Check session running    ");
                if (sessionInfo != null) {
                    System.out.println("Session is still running.");
                } else {
                    System.out.println("Session is not running.");
                }
            }
        }
    }
}

