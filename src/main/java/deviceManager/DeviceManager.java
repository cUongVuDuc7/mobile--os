package deviceManager;

import helpers.LogHelper;
import helpers.PropertiesFile;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static core.BaseTest.deviceAndSession;
import static core.BaseTest.listIdDevice;

public class DeviceManager {
    private static Logger logger = LogHelper.getLogger();

    public static List<String> getIdIOSDevices(){
        List<String> deviceList = new ArrayList<>();
        String listDevice = "";
        logger.info("Get id iOS devices");
        try {
            Process process = Runtime.getRuntime().exec("idevice_id -l");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                deviceList.add(line);
                listDevice += line + ",";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        PropertiesFile.setDataPropValue("LIST_ID_DEVICE", listDevice);
        System.out.println(deviceList);
        return deviceList;
    }
    public static String getNameDevice(String udid){
        String deviceName = "";
        try {
            Process process = Runtime.getRuntime().exec("idevicename -u " + udid);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                deviceName = line;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Device name: " + deviceName);
        return deviceName;
    }
    public static String getVersionDevice(String udid){
        String version = "";
        try {
            Process process = Runtime.getRuntime().exec("ideviceinfo -u " + udid + " -k ProductVersion");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                version = line;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Device version: " + version);
        return version;
    }
    public static String getDeviceTesting(){
        System.out.println( "Device và session in device Mng : " + deviceAndSession);
        String device = "";
        Set<String> keyDevice = deviceAndSession.keySet();
        for (String key : keyDevice) {
            if(deviceAndSession.get(key) == null){
                device = key;
                break;
            }
        }
        System.out.println("getDeviceTesting: " + device);
        return device;
    }
}
