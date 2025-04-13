package helpers;
import org.slf4j.Logger;

import java.io.File;

public class PathHelper {
    public static String projectPath = System.getProperty("user.dir") + File.separator;
    private static Logger logger = LogHelper.getLogger();
    public static String getPathFile(String fileName){
        File file = new File(fileName);
        return file.getAbsolutePath();
    }
    public static String getFileName(String nameFolder){
        File folder = new File(projectPath + nameFolder);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles[0].getName();
    }
    public static String getNameMethod(){
        String nameofCurrMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
        logger.info("Name method: " + nameofCurrMethod);
        return nameofCurrMethod;
    }
}
