package com.rakesh.youtube_clone_backend.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadfile(MultipartFile multipartFile);
}
