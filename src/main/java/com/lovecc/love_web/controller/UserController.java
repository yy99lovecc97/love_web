package com.lovecc.love_web.controller;

import com.alibaba.fastjson.JSONObject;
import com.lovecc.love_web.domain.User;
import com.lovecc.love_web.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/user/index")
    public String userIndex(){
        return "/user/index";
    }

    /**
     * 没有专属码进行账号注册
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/registerUI")
    @Transactional
    public String registerUser(@Validated User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "register";
        }
        if (userService.existsUsername(user.getUsername())){
            bindingResult.rejectValue("username","usernameErr",null,"用户已存在");
            //第一个参数指定表单的域 第二个参数为错误码，如果设了国际化资源，则显示资源文件里错误码对应的 第三个参数为占位符 第四个为错误消息
            return "register";
        }
        userService.createUser(user);
        return "login";
    }
    @PostMapping("/registerHasUI")
    @Transactional
    public String registerHasUser(@Validated User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "registerHas";
        }
        if (userService.existsUsername(user.getUsername())){
            bindingResult.rejectValue("username","usernameErr",null,"用户已存在");
            //第一个参数指定表单的域 第二个参数为错误码，如果设了国际化资源，则显示资源文件里错误码对应的 第三个参数为占位符 第四个为错误消息
            return "registerHas";
        }
        if (!userService.existsOnlyId(user.getOnlyId())){
            bindingResult.rejectValue("onlyId","onlyIdErr",null,"专属码不存在");
            return "registerHas";
        }
        userService.createUser(user);
        return "login";
    }

    /**
     * 判断only_id在数据库中是否存在
     * @param only_id
     * @return
     */
    @PostMapping("/checkOnlyId")
    @ResponseBody
    public JSONObject checkOnlyId(@RequestBody JSONObject only_id){
        String username = only_id.getString("onlyId");
        System.out.println("用户名："+username);
        boolean a = userService.existsOnlyId(username);
        JSONObject result = new JSONObject();
        result.put("status",200);
        result.put("message",a);
        return result;
    }
}
