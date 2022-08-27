package com.nowcoder.community.controller;


import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class LoginController {
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegister(){
        return "/site/register";
    }

    @Autowired
    UserService userService;
    @RequestMapping(path="/register",method = RequestMethod.POST)
    public String register(Model model, User user){
            Map<String,Object> map = userService.register(user);
            if(map==null || map.isEmpty()){
                model.addAttribute("msg","Your registration was success, please check your email to activate!");
                model.addAttribute("target","/index");
                return "/site/operate-result";
            }
            else {
                model.addAttribute("usernameMsg",map.get("usernameMsg"));
                model.addAttribute("passwordMsg",map.get("passwordeMsg"));
                model.addAttribute("EmailMsg",map.get("EmailMsg"));
                return "/site/register";
            }
    }
    @RequestMapping(path="/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
       return "this is test!";
    }
}