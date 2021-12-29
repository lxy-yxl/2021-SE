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
import java.util.List;


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
    public Result<?> upload(@RequestBody Object object) {
        objectMapper.insert(object);
        return Result.success();
    }

    @UserLoginToken
    @PostMapping("/getList")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Object> wrapper = Wrappers.<Object>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Object::getName, search);
        }
        Page<Object> userPage = objectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }


    @GetMapping("getObjectDetail")

    public Result<?> getObjectDetail(int object_id){
        JSONObject jsonObject;
        jsonObject = objectService.getObjectDetail(object_id);
        return Result.success(jsonObject);
    }

    @GetMapping("getObjectListByType")
    public Result<?> getObjectListByType(String type, Integer page){
        Page<JSONObject> iPage = new Page<JSONObject>(page, 6);
        iPage=objectService.getObjectListByType(type,iPage);
        return Result.success(iPage);

    }

    @GetMapping("recommend")
    public Result<?> recommend(){
        List<JSONObject> jsonObjects;
        jsonObjects = objectService.recommend();
        if(jsonObjects.isEmpty()) {
            return Result.error("-1", "暂无商品信息");
        }
        else return Result.success(jsonObjects);
    }

    @GetMapping("searchObject")
    public Result<?> searchObject(String name, Integer page){
        Page<JSONObject> iPage = new Page<JSONObject>(page, 6);
        iPage=objectService.searchObject(name,iPage);
        if(iPage.getTotal()==0)
            return Result.error("-1", "暂无商品信息");
        else return Result.success(iPage);
    }

    @PostMapping("uploadObject")
    public Result<?> uploadObject(Integer user_id,String name, String description, Integer deposit, String tag, Integer rent_daily, String new_level){
        Integer object_id = objectService.uploadObject(user_id, name, description, deposit, tag, rent_daily, new_level);//上传图片没做
        if(object_id==-1)
            return Result.error("-1", "上传失败");
        else return Result.success(object_id);
    }

    @PostMapping("modifyObject")
    public Result<?> modifyObject(Integer object_id, String name, String description, Integer deposit, String tag, Integer rent_daily, String new_level){

        Integer count = objectService.modifyObject(object_id, name, description, deposit, tag, rent_daily, new_level);
        if(count<=0)
            return Result.error("-1", "修改失败");
        else return Result.success();
    }

    @GetMapping("viewOwnObjectList")
    public Result<?> viewOwnObjectList(Integer user_id){
        List<JSONObject> jsonObjects=objectService.viewOwnObjectList(user_id);
        if(jsonObjects.isEmpty())
            return Result.error("-1", "当前用户没有物品列表");
        else return Result.success(jsonObjects);

    }

}

