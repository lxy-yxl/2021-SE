package com.example.demo.service.impl;

import cn.hutool.json.JSONObject;
import com.example.demo.entity.Object;
import com.example.demo.mapper.ObjectMapper;
import com.example.demo.service.ObjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Service
public class ObjectServiceImpl extends ServiceImpl<ObjectMapper, Object> implements ObjectService {

    @Resource
    ObjectMapper objectMapper;

    public JSONObject getObjectDetail(int object_id){
        JSONObject detail=new JSONObject();
        detail = objectMapper.getObjectDetail(object_id);
        return detail;
    }

}
