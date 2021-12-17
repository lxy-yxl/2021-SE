package com.example.demo.service.impl;

import com.example.demo.entity.Picture;
import com.example.demo.mapper.PictureMapper;
import com.example.demo.service.PictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {

}
