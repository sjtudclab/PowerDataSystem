package com.dclab.datasystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import com.dclab.datasystem.domain.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM `user` WHERE username=#{username}")
    public User getUserByUsername(String username);

    @Insert("INSERT INTO `user` VALUES (#{username},#{password})")
    public int insertUser(User user);
}
