package com.dclab.datasystem.service;

import com.dclab.datasystem.domain.User;
import com.dclab.datasystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Object login(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)){
                HashMap<String, Object> result=new HashMap<String, Object>();
                result.put("user", user);
                result.put("status","SUCCESS");
                return result;
            }
            else{
                HashMap<String, Object> result=new HashMap<String, Object>();
                result.put("message", "密码或用户名错误");
                result.put("status","FAILED");
                return result;
            }

        } else{
            HashMap<String, Object> result=new HashMap<String, Object>();
            result.put("message", "密码或用户名错误");
            result.put("status","FAILED");
            return result;
        }
    }

    public Object register(User user) {
        if (userMapper.insertUser(user) == 1)
            return user;
        else
            return null;
    }
}
