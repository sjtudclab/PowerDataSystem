package com.dclab.datasystem;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class test {
    public static void main(String[]args) throws IOException, InvalidFormatException {
        File file = new File("C:\\Users\\60913\\Desktop\\PowerDataSystem\\target\\classes\\static\\files\\upload\\7# 0.4KV配电室.xlsx");
        FileInputStream stream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(stream);
        int sheetNumber = workbook.getNumberOfSheets();
        for(int i = 0;i<sheetNumber;i++){
            System.out.println(workbook.getSheetName(i));
        }
        XSSFSheet firstSheet = workbook.getSheetAt(0);
        System.out.println(firstSheet.getRow(0).getCell(0));
        System.out.println(firstSheet.getFirstRowNum());
        System.out.println(firstSheet.getLastRowNum());
        System.out.println(firstSheet.getRow(0).getFirstCellNum());
        System.out.println(firstSheet.getRow(0).getLastCellNum());
    }
}
