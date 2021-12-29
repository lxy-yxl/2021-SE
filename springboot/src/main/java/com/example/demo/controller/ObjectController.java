package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.common.Result;
import com.example.demo.entity.Object;
import com.example.demo.mapper.ObjectMapper;
import com.example.demo.service.impl.ObjectServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/object")
public class ObjectController {
    @Resource
    ObjectMapper objectMapper;

    @Resource
    ObjectServiceImpl objectService;

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


    @GetMapping("getObjectDetail")
    public Result<?> getObjectDetail(int object_id){
        JSONObject jsonObject=new JSONObject();
        jsonObject = objectService.getObjectDetail(object_id);

        return Result.success(jsonObject);

    }

