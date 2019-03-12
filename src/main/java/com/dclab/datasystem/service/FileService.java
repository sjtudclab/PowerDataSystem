package com.dclab.datasystem.service;

import com.dclab.datasystem.domain.FileInfo;
import com.dclab.datasystem.mapper.FileMapper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FileService {


    @Autowired
    private FileMapper fileMapper;

    public void setFileMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }


    public Object uploadFile(MultipartFile file) throws Exception {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) path = new File("");
        System.out.println("path:" + path.getAbsolutePath());
        File upload = new File(path.getAbsolutePath(), "static/files/upload/");
        if (!upload.exists()) upload.mkdirs();
        System.out.println("upload url:" + upload.getAbsolutePath());
        try {
            FileOutputStream fos = new FileOutputStream(upload.getAbsolutePath() + File.separator + file.getOriginalFilename());
            fos.write(file.getBytes());
            fos.flush();
            fos.close();

            Process process = null;
            String FileName = file.getOriginalFilename();

            String command1 = "python " + "excel_to_mysql.py " + '"' + FileName + '"';
            System.out.println("command1:" + command1);
            process = Runtime.getRuntime().exec(command1, null, new File(path.getAbsolutePath()));
            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        FileInfo fi = new FileInfo();
        fi.setFileName(file.getOriginalFilename());
        fi.setLocation(upload.getAbsolutePath() + File.separator + file.getOriginalFilename());
        fileMapper.InsertFile(fi);
        List<FileInfo> fileI = fileMapper.getFileList();
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("files", fileI);
        result.put("status", "SUCCESS");
        return result;
    }

    public void downloadFile(int fileID, HttpServletResponse response) throws IOException {
        FileInfo f = fileMapper.getFileByID(fileID);
        File file = new File(f.getLocation());
        String filename = f.getFileName();
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/force-download");
        response.setHeader("content-disposition", "attachment;filename="
                + URLEncoder.encode(filename, "UTF-8"));

        response.setContentLength((int) file.length());
        InputStream iStream = new FileInputStream(file);
        OutputStream oStream = response.getOutputStream();
        IOUtils.copy(iStream, oStream);
        response.flushBuffer();

    }

    public Object getFileList() {
        List<FileInfo> fileI = fileMapper.getFileList();
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("files", fileI);
        result.put("status", "SUCCESS");
        return result;
    }

    //    public Object getTableTitle(int fileID){
//        FileInfo f = fileMapper.getFileByID(fileID);
//        File file = new File(f.getLocation());
//
//    }
    public Object getBookTitle(int fileID) throws IOException, InvalidFormatException {
        FileInfo fileInfo = fileMapper.getFileByID(fileID);
        String location = fileInfo.getLocation();
        File file = new File(location);
        FileInputStream stream = new FileInputStream(file);
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        XSSFWorkbook workbook = new XSSFWorkbook(stream);
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            int colMaxNum = workbook.getSheetAt(i).getRow(workbook.getSheetAt(i).getFirstRowNum()).getLastCellNum();
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < colMaxNum; j++) {
                temp.add(workbook.getSheetAt(i).getRow(0).getCell(j).getStringCellValue());
            }
            result.put(workbook.getSheetName(i), temp);
        }
        return result;
    }

    public Object getSheet(int fileID,String sheetName) throws IOException {
        FileInfo fileInfo = fileMapper.getFileByID(fileID);
        String location = fileInfo.getLocation();
        File file = new File(location);
        FileInputStream stream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(stream);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        HashMap<String,Object> result = new HashMap<>();
        ArrayList<String> time = new ArrayList<>();
        result.put("time",time);
        return null;
    }
}
