package com.rakesh.videoBite.services;

import org.springframework.web.multipart.MultipartFile;

//will be implement by the S3 service
public interface FileService {
    String uploadfile(MultipartFile multipartFile);
}
