package com.eatmans.shiro710.controller;

import com.eatmans.shiro710.dto.UserLoginDTO;
import com.eatmans.shiro710.restful.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/user")
public class UserController {

//    @PostMapping({"/login"})
//    @ResponseBody
//    public String login(@RequestBody String json) {
//        User user = new User("z0fsec", "12345");
//        if (json != null) {
//            JSONObject jsonObject = JSON.parseObject(json);
//            String username = jsonObject.getString("username");
//            String password = jsonObject.getString("password");
//            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
//                return "success";
//            }
//            return "false";
//        }
//        throw new NullPointerException("can't be null");
//    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public JsonResult<Void> login(@RequestBody UserLoginDTO loginDTO) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getUsername(), loginDTO.getPassword(), loginDTO.getRememberMe());
        try {
            currentUser.login(token);
            System.out.println("登录成功!");
            return JsonResult.success();
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败!");
            return JsonResult.error("登录失败！");
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        SecurityUtils.getSubject().logout();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public JsonResult<Void> delete() {
        return JsonResult.success("删除成功");
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public JsonResult<Void> update() {
        return JsonResult.success("修改成功");
    }

}