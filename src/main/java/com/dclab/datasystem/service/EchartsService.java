package com.dclab.datasystem.service;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EchartsService {
    public static void main(String[]args) throws IOException, InvalidFormatException {
        File file = new File("C:\\Users\\60913\\Desktop\\PowerDataSystem\\target\\classes\\static\\files\\upload\\7# 0.4KV配电室.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        System.out.println(workbook.getSheetName(0));
    }
}
