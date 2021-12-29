package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.service.impl.FavServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 关系表 前端控制器
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/fav")
public class FavController {
    @Resource
    FavServiceImpl favService;

    @PostMapping("collect")
    public Result<?> collect(Integer fav_id, Integer object_id){
        Integer count = favService.collect(fav_id, object_id);
        if(count<=0)
            return Result.error("-1", "收藏失败");
        else return Result.success();
    }

    @GetMapping("deleteCollection")
    public Result<?> deleteCollection(Integer fav_id, Integer object_id){
        Integer count = favService.deleteCollection(fav_id, object_id);
        if(count<=0)
            return Result.error("-1", "收藏删除失败");
        else return Result.success();
    }

    @GetMapping("queryObjectInFav")//判断用户是否收藏了该商品
    public Result<?> queryObjectInFav(Integer object_id, Integer user_id){
        Integer fav_id = favService.queryObjectInFav(object_id, user_id);
        if(fav_id==null)
            return Result.error("-1", "用户未收藏该物品");
        else return Result.success(fav_id);
    }
}

