package com.ls.dao;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Upload {
    public String uploadGoods(MultipartFile picture) throws IOException{
        String filePath="H:/IDEA文件/Shopping/src/main/webapp/resources/Img/goods/";
        //防止中文乱码
        String originalName=new String(picture.getOriginalFilename().getBytes("ISO-8859-1"),"UTF-8");
        //得到图片的名称(不含后缀)
        String[] fileName=originalName.split("\\.");
        String pictureName=fileName[0];

        File file=new File(filePath + pictureName+".jpg");
        picture.transferTo(file);
        return pictureName;
    }

    public String uploadUser(MultipartFile picture) throws IOException{
        String filePath="H:/IDEA文件/Shopping/src/main/webapp/resources/Img/user/";
        //防止中文乱码
        String originalName=new String(picture.getOriginalFilename().getBytes("ISO-8859-1"),"UTF-8");
        //得到图片的名称(不含后缀)
        String[] fileName=originalName.split("\\.");
        String pictureName=fileName[0];

        File file=new File(filePath + pictureName+".jpg");
        picture.transferTo(file);
        return pictureName;
    }
}
