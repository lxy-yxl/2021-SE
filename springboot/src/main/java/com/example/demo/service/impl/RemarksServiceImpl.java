package com.example.demo.service.impl;

import cn.hutool.json.JSONObject;
import com.example.demo.entity.Remarks;
import com.example.demo.mapper.RemarksMapper;
import com.example.demo.service.RemarksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
<<<<<<< Updated upstream
=======
import java.util.List;
>>>>>>> Stashed changes

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

    public List<JSONObject> getRemarkList(int object_id){
        return remarksMapper.getRemarkList(object_id);
    }

    @Resource
    RemarksServiceImpl remarksService;

    @Resource
    RemarksMapper remarksMapper;

    public



}
