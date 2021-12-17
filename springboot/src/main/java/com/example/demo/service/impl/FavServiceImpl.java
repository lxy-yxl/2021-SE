package com.example.demo.service.impl;

import com.example.demo.entity.Fav;
import com.example.demo.mapper.FavMapper;
import com.example.demo.service.FavService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 关系表 服务实现类
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Service
public class FavServiceImpl extends ServiceImpl<FavMapper, Fav> implements FavService {

}
