package com.shop.controller;

import com.shop.entity.SeckillUser;
import com.shop.entity.vo.SeckMessageVo;
import com.shop.redis.RedisService;
import com.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    RedisService redisService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/to_login")
    public String toLogin(){
        return "user/login";
    }

    @RequestMapping(value = "/do_login")
    public String doLogin(@RequestParam("name")String name, @RequestParam("password") String pass, HttpSession session) {
        SeckillUser user=userService.login(name,pass);
        if (user==null){
            return null;
        }
        session.setAttribute("user",user);
        return "redirect:/goods/tolist";
    }



}
