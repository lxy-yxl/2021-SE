package com.example.demo.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.poi.excel.cell.CellSetter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Object;
import com.example.demo.entity.Picture;
import com.example.demo.mapper.ObjectMapper;
import com.example.demo.mapper.PictureMapper;
import com.example.demo.service.ObjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    PictureMapper pictureMapper;

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

    public Page<JSONObject> searchObject(String name, Page<JSONObject> ipage){
        return objectMapper.searchObject(name,ipage);
    }

    public Integer uploadObject(Integer user_id, String name, String description, Integer deposit, String tag, Integer rent_daily, String new_level){
        Object object=new Object();
        object.setUserId(user_id);
        object.setName(name);
        object.setDescription(description);
        object.setDeposit(deposit);
        object.setTag(tag);
        object.setRentDaliy(rent_daily);
        object.setNewLevel(new_level);
        object.setStatus("审核通过");//管理员部分待做
        Integer count = objectMapper.insert(object);
        if(count<=0)
            return -1;
        else return object.getObjectId();
    }

    public Integer modifyObject(Integer object_id, String name, String description, Integer deposit, String tag, Integer rent_daily, String new_level){
        Object object=new Object();
        object.setObjectId(object_id);
        if(name!=null)
            object.setName(name);
        if(description!=null)
            object.setDescription(description);
        if(deposit!=null)
            object.setDeposit(deposit);
        if(tag!=null)
            object.setTag(tag);
        if(rent_daily!=null)
            object.setRentDaliy(rent_daily);
        if(new_level!=null)
            object.setNewLevel(new_level);
        return objectMapper.updateById(object);
    }

    public List<JSONObject> viewOwnObjectList(Integer user_id){
        List<JSONObject> jsonObjects=new ArrayList<>();
        QueryWrapper<Object> wrapper =new QueryWrapper<>();
        wrapper.eq("user_id", user_id);
        List<Object> objects = objectMapper.selectList(wrapper);
        if(objects.isEmpty())
            return null;
        for (Object object:objects) {
            QueryWrapper<Picture> pictureQueryWrapper = new QueryWrapper<>();
            String url;
            pictureQueryWrapper.eq("object_id", object.getObjectId());
            Picture picture= pictureMapper.selectOne(pictureQueryWrapper);
            if(picture!=null)
                url = picture.getPictureUrl();
            else url=null;
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("object", object);
            jsonObject.put("url", url);
            jsonObjects.add(jsonObject);
        }
        return jsonObjects;
    }

}
