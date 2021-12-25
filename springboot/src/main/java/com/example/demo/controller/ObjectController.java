package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.common.Result;
import com.example.demo.entity.Object;
import com.example.demo.mapper.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/object")
public class ObjectController {
    @Resource
    ObjectMapper objectMapper;

    @UserLoginToken
    @PostMapping
    public Result<?> upload(@RequestBody Object object){
        objectMapper.insert(object);
        return Result.success();
    }

    @UserLoginToken
    @PostMapping("/getList")
    public Result<?> findPage(@RequestParam(defaultValue = "1")Integer pageNum,
                              @RequestParam(defaultValue = "10")Integer pageSize,
                              @RequestParam(defaultValue = "")String search){
        LambdaQueryWrapper<Object> wrapper= Wrappers.<Object>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Object::getName,search);
        }
        Page<Object> userPage=objectMapper.selectPage( new Page<>(pageNum ,pageSize),wrapper );
        return Result.success(userPage);
    }

}

