package com.dclab.datasystem.controller;

import com.dclab.datasystem.domain.FileInfo;
import com.dclab.datasystem.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class FileController {
    @Autowired
    private FileService fileService;

    public void setFileService(FileService fileService){
        this.fileService = fileService;
    }

    @RequestMapping(value="/uploadFile",method=RequestMethod.POST)
    public Object uploadFile(@RequestParam(value="file")MultipartFile dataFile) throws Exception{
        System.out.println("进入后台");
        return fileService.uploadFile(dataFile);
    }
    @RequestMapping(value = "/downloadFile/{fileID}",method=RequestMethod.GET)
    public void downloadFile(@PathVariable("fileID") int fileID,HttpServletResponse response)throws Exception{
        fileService.downloadFile(fileID,response);
    }
    @RequestMapping(value = "/getFileList",method=RequestMethod.POST)
    public Object getFileList() throws Exception{
        return fileService.getFileList();
    }


}
