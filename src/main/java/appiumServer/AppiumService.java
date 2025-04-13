package appiumServer;
import helpers.LogHelper;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.slf4j.Logger;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.URL;

public class AppiumService {
    protected AppiumDriverLocalService service;
    private static Logger logger = LogHelper.getLogger();
    public AppiumService() {
    }
    //    public static void startServer() {
//        AppiumServiceBuilder builder = new AppiumServiceBuilder();
//        builder.withIPAddress("127.0.0.1")
////                .usingAnyFreePort()
//                .usingPort(getPort())
//                .withAppiumJS (
//                new File ("/Users/cuongvu/.nvm/versions/node/v22.11.0/lib/node_modules/appium/build/lib/main.js"))
//                .usingDriverExecutable (new File("/Users/cuongvu/.nvm/versions/node/v22.11.0/bin/node"))
//        .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//        .withArgument(GeneralServerFlag.LOG_LEVEL,"error");
//        service = builder.build();
//        service.start();
//        System.out.println("url start appium server: " + service.getUrl());
//    }
    public void startServer() {
        logger.info("start server");
        this.service = new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingPort(getPort()).build();
        this.service.start();
        System.out.println("url start appium " + this.service.getUrl());
    }
    public void stopServer() {
        logger.info("stop server");
        this.service.stop();
    }
    public URL getUrlServer() {
        return this.service.getUrl();
    }
    public int getPort(){
        int port = 0;
        try {
            ServerSocket socket = new ServerSocket(0);
            socket.setReuseAddress(true);
            port = socket.getLocalPort();
            socket.close();
        }
        catch (Exception e) {

        }
        return port;
    }

    public boolean checkIfServerIsRunning(Integer port) {
        boolean check = true;
        if(port != null) {
            String appiumServerUrl = "http://127.0.0.1:" + port + "/status";
            try {
                URL url = new URL(appiumServerUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    check = true;
                } else {
                    System.out.println("Failed to connect to Appium server. HTTP response code: " + responseCode);
                }
            }
            catch (Exception e) {
                check = false;
                e.printStackTrace();
                System.out.println("Error checking Appium server status.");
            }
        }
        return check;
    }
}

