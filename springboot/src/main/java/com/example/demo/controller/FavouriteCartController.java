package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.FavouriteCart;
import com.example.demo.service.impl.FavouriteCartServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/favouriteCart")
public class FavouriteCartController {
    @Resource
    FavouriteCartServiceImpl favouriteCartService;

    @GetMapping("favouriteCartList")
    public Result<?> favouriteCartList(Integer user_id){
        List<FavouriteCart> favouriteCarts = favouriteCartService.favouriteCartList(user_id);
        if(favouriteCarts.isEmpty())
            return Result.error("-1", "用户没有收藏夹");
        else return Result.success(favouriteCarts);
    }

    @GetMapping("addFavouriteCart")
    public Result<?> addFavouriteCart(Integer user_id, String name, String description){
        Integer fav_id = favouriteCartService.addFavouriteCart(user_id, name, description);
        if(fav_id<=0)
            return Result.error("-1", "新增收藏夹失败");
        else return Result.success(fav_id);
    }



}

