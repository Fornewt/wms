package org.example.back.controller;
import org.example.back.domain.SysUser;
import org.example.back.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController // 表示这个类是控制器
public class SysUserController {
    @Autowired // 自动注入依赖
    private SysUserService sysUserService;
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody SysUser data){// 从http请求体中读取json文件并转换成SysUser对象
        String userName = data.getUserName();
        String password = data.getPassword();
        System.out.println("nihao");
        return sysUserService.login(userName,password);


}
}
