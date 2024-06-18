package org.example.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.back.domain.SysUser;

import java.util.Map;

public interface SysUserService extends IService<SysUser> {
    Map<String,Object> login(String userName, String password);
}
