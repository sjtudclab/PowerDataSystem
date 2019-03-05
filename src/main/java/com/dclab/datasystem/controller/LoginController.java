package com.dclab.datasystem.controller;


import com.dclab.datasystem.domain.User;
import com.dclab.datasystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class LoginController {
    @Autowired
    private LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

//    @RequestMapping(value="/login",method=RequestMethod.POST)
//    public Object login(@RequestParam(value="username")String username,@RequestParam(value="password")String password){
//        return loginService.login(username, password);
//    }
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public Object login(@RequestBody User user){
        return loginService.login(user.getUsername(), user.getPassword());
    }

    @RequestMapping(value="/register",method=RequestMethod.POST)
    public Object register(User user){
        //System.out.println("phone"+user.getPhone());
        return loginService.register(user);
    }
}
