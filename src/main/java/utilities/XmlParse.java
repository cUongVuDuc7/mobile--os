package utilities;
import helpers.PropertiesFile;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import static helpers.PathHelper.projectPath;

public class XmlParse {
    private static void removeWhitespaceNodes(Node node) {
        NodeList children = node.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE && child.getTextContent().trim().isEmpty()) {
                node.removeChild(child);
            }
            else if (child.getNodeType() == Node.ELEMENT_NODE) {
                removeWhitespaceNodes(child);
            }
        }
    }
    public static void insertInformDevices(String version, String id) {
        version = PropertiesFile.getPropValue(version);
        id = PropertiesFile.getPropValue(id);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File xmlFile = new File( projectPath + "config" + File.separator + "all-test.xml");
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            int size = doc.getElementsByTagName("parameter").getLength();
            NodeList employeeList = doc.getElementsByTagName("parameter");
            for (int i = 1; i < size; i++) {
                Element employee = (Element) employeeList.item(i);
                if (employee.getAttribute("name").equals("platformVersion")) {
                    employee.setAttribute("value", version);
                }
                else {
                    employee.setAttribute("value", id);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(projectPath + "config" + File.separator + "login-test.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            removeWhitespaceNodes(doc.getDocumentElement());
            transformer.transform(source, result);
            }
            catch (Exception e) {
                e.printStackTrace();
        }
    }
    public static void setThread(String numberThread) {
        System.out.println("number thread " + numberThread);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File xmlFile = new File( projectPath + "config" + File.separator + "all-test.xml");
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList employeeList = doc.getElementsByTagName("suite");
            Element employee = (Element) employeeList.item(0);
            if (employee.getAttribute("name").equals("OnPlus IOS")) {
                employee.setAttribute("thread-count", numberThread);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(projectPath + "config" + File.separator + "all-test.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            removeWhitespaceNodes(doc.getDocumentElement());
            transformer.transform(source, result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

