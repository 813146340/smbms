package cn.kgc.smbms.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public String upload(MultipartFile file, String path);
}
