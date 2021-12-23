package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author cj
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1")Integer pageNum,
                              @RequestParam(defaultValue = "10")Integer pageSize,
                              @RequestParam(defaultValue = "")String search){
        LambdaQueryWrapper<User> wrapper= Wrappers.<User>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(User::getNickName,search);
        }
        Page<User> userPage=userMapper.selectPage( new Page<>(pageNum ,pageSize),wrapper );
        return Result.success(userPage);
    }

}

