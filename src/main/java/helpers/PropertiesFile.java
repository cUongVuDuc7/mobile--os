package helpers;

import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.Properties;
import static helpers.PathHelper.projectPath;

public class PropertiesFile {
    private static Logger logger = LogHelper.getLogger();
    private static Properties properties;
    private static FileInputStream fileIn;
    private static FileOutputStream fileOut;
    private static String dataPropertiesPath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data.properties";
    public static void setPropertiesFile() {
        LinkedList<String> files = new LinkedList<>();
        files.add(projectPath + dataPropertiesPath);
        properties = new Properties();
        try {
            for (String file : files) {
                Properties tempProp = new Properties();
                fileIn = new FileInputStream(file);
                tempProp.load(fileIn);
                properties.putAll(tempProp);
            }
            fileIn.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
    public static String getPropValue(String key){
        logger.info("get value properties: " + key);
        String value = null;
        try {
            value = properties.getProperty(key);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return value;
    }
    public static void setDataPropValue(String key, String value) {
        logger.info("set value properties: " + value);
        try {
            fileOut = new FileOutputStream(projectPath + dataPropertiesPath);
            properties.setProperty(key, value);
            properties.store(fileOut, "set new value");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
}
