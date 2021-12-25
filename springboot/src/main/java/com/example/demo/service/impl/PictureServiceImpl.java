package com.example.demo.service.impl;

import com.example.demo.entity.Picture;
import com.example.demo.mapper.ObjectMapper;
import com.example.demo.mapper.PictureMapper;
import com.example.demo.service.PictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author ZLZHAO
 * @since 2021-12-20
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {
    /**
     *
     * @param from
     * fileInfo[0]: toPrefix;
     * fileInfo[1]:toSuffix;
     * @return
     */
    public static String[] getFileInfo(File from){
        String fileName=from.getName();
        int index = fileName.lastIndexOf(".");
        String toPrefix="";
        String toSuffix="";
        if(index==-1){
            toPrefix=fileName;
        }else{
            toPrefix=fileName.substring(0,index);
            toSuffix=fileName.substring(index,fileName.length());
        }
        return new String[]{toPrefix,toSuffix};
    }
    public static String generateSuffix() {
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换为字符串
        String formatDate = format.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        return new StringBuffer().append(formatDate).append(
                random).toString();
    }

    public static File createFileWithCurDate(File from){
        String[] fileInfo = getFileInfo(from);
        String toPrefix=fileInfo[0]+generateSuffix();
        String toSuffix=fileInfo[1];
        return new File(from.getParent(),toPrefix+toSuffix);
    }
    @Override
    public String upload(MultipartFile file, String uploadFilePath) throws Exception {
        if (file.isEmpty())
            return null;
        String uploadInfo="";
        String fileName;
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换为字符串
        String formatDate = format.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        fileName =(new StringBuffer().append(formatDate).append(
                random)) + file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        long fileSize = file.getSize();
        File packageFile = new File(uploadFilePath);
        if (!packageFile.exists()) {
            packageFile.mkdir();
        }
        File targetFile = new File(uploadFilePath + "/" + fileName);
        file.transferTo(targetFile);
        uploadInfo = targetFile.toString();

        //是否使用一个接口还是两个接口仍需讨论
        //尚未决定表的存储方法
//        PictureMapper pictureMapper;
//        Picture picture =new Picture();
//        picture.setPictureUrl(uploadInfo);


        return uploadInfo;
    }

}
