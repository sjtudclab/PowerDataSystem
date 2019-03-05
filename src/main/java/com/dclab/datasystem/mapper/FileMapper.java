package com.dclab.datasystem.mapper;


import com.dclab.datasystem.domain.FileInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {
    @Insert("INSERT INTO `file`(fileName,location) VALUES (#{fileName},#{location})")
    public int InsertFile(FileInfo file);

    @Select("SELECT * FROM `file`")
    public List<FileInfo> getFileList();

    @Select("SELECT * from `file` where fileID = #{fileID}")
    public FileInfo getFileByID(int fileID);
}
