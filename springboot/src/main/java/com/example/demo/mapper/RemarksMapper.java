package com.example.demo.mapper;

import cn.hutool.json.JSONObject;
import com.example.demo.entity.Remarks;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 评价表
 Mapper 接口
 * </p>
 *
 * @author
 * @since 2021-12-17
 */
public interface RemarksMapper extends BaseMapper<Remarks> {

<<<<<<< Updated upstream

=======
    List<JSONObject> getRemarkList(int object_id);
>>>>>>> Stashed changes

}
