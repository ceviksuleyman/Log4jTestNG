package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    static Workbook workbook;
    static Sheet sheet;

    public ExcelUtils(String excelPath, String sheetName) {

        try {

            FileInputStream fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getCellData(int rowNum, int columnNum) {

        DataFormatter formatter = new DataFormatter();
        Object value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(columnNum));
        //String value = sheet.getRow(1).getCell(0).getStringCellValue();
        System.out.println(value);
    }
    public static void getRowCount() {

        int rowCount = sheet.getPhysicalNumberOfRows();
        System.out.println("No of Rows : " + rowCount);
    }
}
