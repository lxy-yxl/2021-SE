package com.example.demo.mapper;

import cn.hutool.json.JSONObject;
import com.example.demo.entity.Object;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
public interface ObjectMapper extends BaseMapper<Object> {
    @Select("select * from object where user_id=#{userId}")
    List<Object> selectByUserId(Integer userId);

    JSONObject getObjectDetail(int object_id);

}
