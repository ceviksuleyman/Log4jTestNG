package tests;

import utilities.ExcelUtils;

import static utilities.ExcelUtils.getCellData;
import static utilities.ExcelUtils.getRowCount;

public class ExcelUtilsTest {

    public static void main(String[] args) {

        String excelPath = "src/test/resources/DataDriven.xlsx";
        String sheetName = "Sayfa1";

        ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
        getCellData(1,1);
        getCellData(1,0);
        getRowCount();
    }
}
