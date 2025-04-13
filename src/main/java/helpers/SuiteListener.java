package helpers;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.util.List;
import static deviceManager.DeviceManager.*;

public class SuiteListener implements IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite>  suites) {
        PropertiesFile.setPropertiesFile();
        int numberDevice = getIdIOSDevices().size();
        PropertiesFile.setDataPropValue("NUMBER_DEVICE", String.valueOf(numberDevice));
        XmlSuite suite = suites.get(0);
        suite.setThreadCount(numberDevice);
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        PropertiesFile.setDataPropValue("NUMBER_TEST_IN_SUITE", String.valueOf(suite.getTests().size()));



//        XmlTest xmlTest = suite.getTests().get(0);
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("browser", "chrome");
//        System.out.println(xmlTest.getAllParameters());
//        System.out.println(xmlTest.getName());
//        System.out.println(xmlTest.getParameter("browser"));
//        xmlTest.setParameters(parameters);
    }

}

