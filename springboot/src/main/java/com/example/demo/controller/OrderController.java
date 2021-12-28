package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.common.Result;
import com.example.demo.entity.Order;
import com.example.demo.service.impl.OrderServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    OrderServiceImpl orderService;

//    @GetMapping("getOrderList")
//    public Result<?> getOrderList(){
//        return
//    }


    @GetMapping("generateOrder")
    public Result<?> generateOrder(int object_id, int borrower_id, String lentout_time, String return_time, String campus) {
        Order order = new Order();
        LocalDateTime parse_lentout_time = LocalDateTime.parse(lentout_time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime parse_return_time = LocalDateTime.parse(return_time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int order_id = orderService.generateOrder(object_id, borrower_id, parse_lentout_time, parse_return_time, campus);
        if (order_id == -1){
            return Result.error("-1", "租借时间错误");
        }
        else if(object_id == -2){
            return Result.error("-2", "订单生成失败");
        }
        else {
            return Result.success(order_id);
        }
    }

    @GetMapping("confirm")
    public Result<?> confirm(int order_id){
        int flag = orderService.confirm(order_id);
        if(flag == -1)
            return Result.error("-1", "确认收货失败");
        else return Result.success();
    }

    @GetMapping("cancel_order")
    public Result<?> cancel_order(int order_id){
        int flag = orderService.cancel_order(order_id);
        if(flag == -1)
            return Result.error("-1", "未找到相应订单");
        else if(flag == -2)
            return Result.error("-2", "订单已支付");
        else if(flag==-3)
            return Result.error("-3", "订单取消失败");
        else return Result.success();
    }

}

