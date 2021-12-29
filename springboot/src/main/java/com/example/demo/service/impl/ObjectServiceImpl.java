package com.example.demo.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Object;
import com.example.demo.mapper.ObjectMapper;
import com.example.demo.service.ObjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class ObjectServiceImpl extends ServiceImpl<ObjectMapper, Object> implements ObjectService {

    @Resource
    ObjectMapper objectMapper;

    public JSONObject getObjectDetail(int object_id){
        JSONObject detail;
        detail = objectMapper.getObjectDetail(object_id);
        List<String> urls = objectMapper.getPictureUrl(object_id);
        Integer rent_count = objectMapper.getRentCount(object_id);
        detail.put("urls", urls);
        detail.put("rent_count", rent_count);
        return detail;
    }

    public Page<JSONObject> getObjectListByType(String type, Page<JSONObject> ipage){
        return objectMapper.getObjectListByType(type,ipage);

    }

    public List<JSONObject> recommend(){
        return objectMapper.recommend();

    }

}
