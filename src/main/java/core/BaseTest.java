package core;

import appiumServer.AppiumService;
import driver.DriverManager;
import helpers.LogHelper;
import helpers.PropertiesFile;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import keyword.KeywordWeb;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import static deviceManager.DeviceManager.*;
import static helpers.MyListener.*;
import static keyword.KeywordWeb.*;
import static utilities.ReadExcel.ExcelOperations;
import static utilities.ScreenRecorderUtil.saveVideo;

public class BaseTest {
    private static Logger logger = LogHelper.getLogger();
    protected KeywordWeb keyword;
    protected static Workbook workbook = null;
    private static String userName = System.getenv("BROWSERSTACK_USERNAME");
    private static String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    private static String app = System.getenv("BROWSERSTACK_APP");
    public static IOSDriver driver;
    protected AppiumService appiumService;
    public static Map<String, String> deviceAndSession = new HashMap<>();
    public static List<String> listIdDevice = new ArrayList<>();
    public BaseTest() {
        keyword = new KeywordWeb();
        appiumService = new AppiumService();
        workbook = ExcelOperations();
    }
    public void setDeviceAndSession(){
        for (int i = 0; i < listIdDevice.size(); i++) {
            deviceAndSession.put(listIdDevice.get(i), null);
        }
    }
    @BeforeSuite(alwaysRun=true)
    public void setFile(){
        try {
            if (PropertiesFile.getPropValue("OVER_WRITE_REPORT").equals("YES")) {
                FileUtils.deleteDirectory(new File("target" + File.separator + "allure-results"));
                logger.info("Deleted directory allure-results");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        listIdDevice = getIdIOSDevices();
        setDeviceAndSession();
        logger.info("BeforeSuite Device và session: " + deviceAndSession);
    }

    public IOSDriver setUp(String deviceName, String version, String id){
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", deviceName);
        cap.setCapability("platformVersion", version);
        cap.setCapability("udid", id);
        cap.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, rd.nextInt(8100,8300));
        cap.setCapability("automationName", "XCUITest");
        cap.setCapability("platformName", "iOS");
        cap.setCapability("xcodeSigningId", "iPhone Developer");
        cap.setCapability(IOSMobileCapabilityType.USE_PREBUILT_WDA, true);
        cap.setCapability (IOSMobileCapabilityType.USE_NEW_WDA, false);
        cap.setCapability("noReset", false);
        cap.setCapability("bundleId", "com.vtvcab.onsports.dev");
//        driver = new IOSDriver(27.0.0.1:" + port + "/wd/hub"), cap);
        driver = new IOSDriver(appiumService.getUrlServer(), cap);
        return driver;
    }

    public void setUpBrowserStack(String deviceName, String version) throws Exception{
        DesiredCapabilities cap = new DesiredCapabilities();
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("userName", userName);
        browserstackOptions.put("accessKey", accessKey);
        cap.setCapability("app", app);
        cap.setCapability("bstack:options", browserstackOptions);
        cap.setCapability("automationName", "XCUITest");
        cap.setCapability("platformName", "ios");
        cap.setCapability("platformVersion", version);
        cap.setCapability("deviceName", deviceName);
        cap.setCapability("noReset", true);
        URL url = new URL("http://hub.browserstack.com/wd/hub");
        driver = new IOSDriver(url, cap);
    }
    @BeforeTest(alwaysRun = true)
    @Parameters({"platform","deviceName","deviceVersion","deviceId"})
    public void setUpPlatForm(String cloudPlatform, String deviceName, String version, String udid) throws Exception {
        if (cloudPlatform.equals("iOS device")) {
            appiumService.startServer();
            DriverManager.setDriver(setUp(deviceName, version, udid));
        }
        else {
            setUpBrowserStack(deviceName, version);
        }
        driver.startRecordingScreen();
    }
    @AfterMethod
    public void afterMethod(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            saveScreenshotPNG();
            String video = driver.stopRecordingScreen();
            String name = saveVideo(video, testResult);
            attachVideoRecord(name);
        }
    }
    @AfterTest(alwaysRun = true)
    @Parameters({"deviceId"})
    public void quitDriver(String deviceId) {
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
        deviceAndSession.put(deviceId, null);
        appiumService.stopServer();
    }
}
