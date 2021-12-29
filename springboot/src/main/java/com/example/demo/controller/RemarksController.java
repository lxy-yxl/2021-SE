package com.example.demo.controller;


import cn.hutool.json.JSONObject;
import com.example.demo.common.Result;
import com.example.demo.service.impl.RemarksServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 评价表
 前端控制器
 * </p>
 *
 * @author
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/remarks")
public class RemarksController {
    @Resource
    RemarksServiceImpl remarksService;

    @GetMapping("getRemarkList")
    public Result<?> getRemarkList(int object_id){
        List<JSONObject> remarks = remarksService.getRemarkList(object_id);
        if(remarks.isEmpty()){
            return Result.error("-1", "暂无评论");
        }
        else return Result.success(remarks);
    }


}

