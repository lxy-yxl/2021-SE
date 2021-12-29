package com.example.demo.service.impl;

import cn.hutool.json.JSONObject;
import com.example.demo.entity.Remarks;
import com.example.demo.mapper.RemarksMapper;
import com.example.demo.service.RemarksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 评价表
 服务实现类
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Service
public class RemarksServiceImpl extends ServiceImpl<RemarksMapper, Remarks> implements RemarksService {
    @Resource
    RemarksMapper remarksMapper;

    public JSONObject getRemarkList(int object_id){
        JSONObject jsonObject=new JSONObject();

        List<JSONObject> remark_list = remarksMapper.getRemarkList(object_id);
        jsonObject.put("remark_list",remark_list);
        int count = remark_list.size();
        jsonObject.put("commentNum",count);
        return jsonObject;
    }







}
