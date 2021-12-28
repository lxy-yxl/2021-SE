package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Resource
    ObjectServiceImpl objectService;
    @Resource
    OrderMapper orderMapper;

    public int generateOrder(int object_id, int borrower_id, LocalDateTime lentout_time, LocalDateTime return_time, String campus) {
        int lent_days = (int) Math.ceil((float) Duration.between(lentout_time, return_time).getSeconds() / 3600 / 24);
        if (lent_days < 0) {
            return -1;
        } else {
            Order order = new Order();
            order.setBorrowerId(borrower_id);
            order.setLentoutTime(lentout_time);
            order.setReturnTime(return_time);
            order.setObjectId(object_id);
            order.setStatus("待支付");
            order.setFinishedTime(null);
            order.setCreatedTime(LocalDateTime.now());
            order.setCampus(campus);
            int rent_daily = objectService.getBaseMapper().selectById(object_id).getRentDaliy();
            order.setRentTotal(rent_daily * lent_days);
            int count = orderMapper.insert(order);
            if(count<=0)
                return -2;
            else {
                int order_id = order.getOrderId();
                return order_id;
            }
        }
    }

    public int confirm(int order_id){
        Order order = new Order();
        order.setOrderId(order_id);
        order.setStatus("已完成");
        order.setFinishedTime(LocalDateTime.now());
        int count = orderMapper.updateById(order);
        if(count<=0){
            return -1;
        }
        else
            return 1;
    }

    public int cancelOrder(int order_id){
        Order order = orderMapper.selectById(order_id);
        if (order == null){
            return -1;
        }
        else if(order.getStatus().equals("已支付")){
            return -2;
        }
        else {
            int count = orderMapper.deleteById(order_id);
            if(count<=0){
                return -3;
            }
            else {
                return 1;
            }
        }
    }

    public List<Order> getOrderList(int user_id){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("borrower_id", user_id);
        List<Order> order_list = orderMapper.selectList(wrapper);
        return order_list;
    }

}
