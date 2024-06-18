package org.example.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.back.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.back.mapper.SysUserMapper;
import org.example.back.domain.SysUser;

import java.util.HashMap;
import java.util.Map;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public Map<String,Object> login(String userName, String password){
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();// 用于查询
        queryWrapper.eq("user_name",userName); // 构建查询条件 WHERE user_name = userName
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper); // 使用该查询条件来查询数据库
        Map<String,Object> data = new HashMap<>(); // 用于打印查询结果信息
        if(sysUser.getPassword().equals(password)){
            data.put("httpCode",200);
            data.put("data",sysUser);
        }else{
            data.put("httpCode",500);
        }
        return data;
    }
}
