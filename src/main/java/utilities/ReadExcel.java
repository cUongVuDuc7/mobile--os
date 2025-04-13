package utilities;
import java.io.*;
import java.util.HashMap;
import java.util.Set;
import helpers.LogHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;

import static helpers.PathHelper.projectPath;

public class ReadExcel {
    public static final String FILE_PATH = projectPath + "data" + File.separator + "DataTestOnplus.xlsx";
    public static Logger logger = LogHelper.getLogger();

    public static int getRowCount(Sheet sh) {
        return sh.getLastRowNum();
    }
    public static int getColCount(Sheet sh) {
        return sh.getRow(0).getLastCellNum();
    }
    public static Object[][] getExcelData(Sheet sh){
        Object[][] obj = new Object[getRowCount(sh)][1];
        try {
            for (int i = 1; i <= getRowCount(sh); i++) {
                HashMap<String, String> testData = getTestDataInMap(sh, i);
                obj[i - 1][0] = testData;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
    public static Workbook ExcelOperations() {
        Workbook workbook = null;
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            workbook = new XSSFWorkbook(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
    public static Sheet readSheet(Workbook workbook, String sheetName){
        logger.info("Read sheet excel " + sheetName );
        return workbook.getSheet(sheetName);
    }
    public static HashMap<String, String> getTestDataInMap(Sheet sh, int rowNum) {
        HashMap<String, String> hm = new HashMap<>();
        for (int i = 0; i < sh.getRow(0).getLastCellNum(); i++) {
            String value;
            if(sh.getRow(rowNum).getCell(i) != null) {
                sh.getRow(rowNum).getCell(i).setCellType(CellType.STRING);
                value = sh.getRow(rowNum).getCell(i).toString();
            }
            else {
                value = "";
            }
            hm.put(sh.getRow(0).getCell(i).toString(), value);
        }
        Set<String> set = hm.keySet();
        for (String key : set) {
            System.out.println("key " + key + " value " + hm.get(key));
        }
        return hm;
    }
    public static int getIndexRowFromKey(Sheet sh, String key){
        int index = 0;
        System.out.println("Number rows: " + sh.getPhysicalNumberOfRows());
        for (int i = 0; i < sh.getPhysicalNumberOfRows(); i++) {
            System.out.println("Value colum " + sh.getRow(i).getCell(0).getStringCellValue());
            if(sh.getRow(i).getCell(0).getStringCellValue().equals(key)){
                index = i;
                break;
            }
        }
        logger.info("Index row: " + index);
        return index;
    }
    public static int getIndexCellFromKey(Sheet sh, String key){
        int index = 0;
        System.out.println("Number colum: " + sh.getRow(0).getPhysicalNumberOfCells());
        for (int i = 0; i < sh.getRow(0).getPhysicalNumberOfCells(); i++) {
            if(sh.getRow(0).getCell(i).getStringCellValue().equals(key)){
                index = i;
                break;
            }
        }
        logger.info("Index cell: " + index);
        return index;
    }
    public static void setCell(Workbook workbook, Sheet sh, String value, int rowNumber, int cellNumber){
        try {
            logger.info("Set cell: " + value + " On row: " + rowNumber + " On cell: " + cellNumber);
            Row row = sh.getRow(rowNumber);
            Cell cell = row.getCell(cellNumber);
            cell.setCellValue(value);
            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            workbook.write(fileOut);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
//    public int getIndexCell(int rowNumber, String key){
//            Row row = sh.getRow(rowNumber);
//            return 0;
//    }
}
