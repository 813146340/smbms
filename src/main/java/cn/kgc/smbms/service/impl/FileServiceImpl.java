package cn.kgc.smbms.service.impl;

import cn.kgc.smbms.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/10
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Override
    public String upload(MultipartFile file, String path){
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件后缀
        String suffixName = originalFilename.substring(originalFilename.indexOf('.') + 1);
        //TODO 可以判断文件格式是否是你想要的，或者是符合要求的

        //组装新的文件名  保证用户上传的文件名不冲突
       String newFileName = UUID.randomUUID().toString()+"."+suffixName;


       //判断文件上传路径是否存在  若不存在则创建一个
        File pathFile = new File(path);
        if (!pathFile.exists()){
            //赋予系统的写入权限
            pathFile.setWritable(true);
            //创建
            pathFile.mkdirs();
        }


        File fileUpload = new File(path,newFileName);
        try {
            //文件上传
            file.transferTo(fileUpload);
            //TODO 连接文件服务器 将文件上传到文件服务器上

            //TODO 删除本地服务器上的文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("上传完成了...."+fileUpload.getPath());
        return newFileName;
    }
}
