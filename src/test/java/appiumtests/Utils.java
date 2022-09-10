package appiumtests;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.util.POILogger;
//import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;


public class Utils {
    public static XSSFWorkbook wb;
    public static XSSFSheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static FileInputStream fis;

    public static void main(String[] args) throws IOException {
        fis = new FileInputStream("/Users/architkamat/Desktop/ABHIJEET/GFitData.xlsx");
        getTotalRows("/Users/architkamat/Desktop/ABHIJEET/GFitData.xlsx");
        getCellData(2,1,"/Users/architkamat/Desktop/ABHIJEET/GFitData.xlsx");
    }

    public static int getTotalRows(String fileName) {
        try {
//            fis = new FileInputStream("/Users/architkamat/Desktop/ABHIJEET/GFitData.xlsx");
            fis = new FileInputStream(fileName);
            wb = new XSSFWorkbook(fis);
            sheet = wb.getSheet("Sheet1");
            int rowTotal = sheet.getLastRowNum();
            return rowTotal;
        } catch (Exception e) {
            System.out.println("In the Get total rows Catch Block:" + e);
            return 0;
        }
    }

    public static int getTotalCols(String fileName) {
        try {
            fis = new FileInputStream(fileName);
            wb = new XSSFWorkbook(fis);
            sheet = wb.getSheet("Sheet1");
            row = sheet.getRow(0);
            int colTotal = row.getLastCellNum();

            return colTotal;
        } catch (Exception e) {
            System.out.println("In the Catch Block:" + e);
            return 0;
        }
    }

    public static String getCellData(int rownum, int col, String fileName) {
        try {
            fis = new FileInputStream("/Users/architkamat/Desktop/ABHIJEET/GFitData.xlsx");
            wb = new XSSFWorkbook(fis);
            sheet = wb.getSheet("Sheet1");
            row = sheet.getRow(rownum);
            cell = row.getCell(col);
            DataFormatter formatter = new DataFormatter();
            String val = formatter.formatCellValue(sheet.getRow(rownum).getCell(col));
            if (cell == null) {
                return "";
            }
            return val;

        } catch (Exception e) {
            System.out.println("In the Catch Block:" + e);
            return "Exception Occured";
        }

    }
}