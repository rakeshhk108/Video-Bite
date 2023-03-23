package com.rakesh.youtube_clone_backend.services;




import com.amazonaws.services.s3.AmazonS3;;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class S3Service implements FileService {


    public static final String BUCKET_NAME = "youtubestoragedrd";
    private final AmazonS3 amazonS3;



    @Override
    public String uploadfile(MultipartFile file){

        //prepare a key
        //get the extention of the file
        //var is a dects the variable and determines the data type
        // using stingUtils.getFilenameExtension to gie the file extention and save it
        var filenameExtention = StringUtils.getFilenameExtension( file.getOriginalFilename());
        //creating a unique, will be used to upload file in the s3
        var key = UUID.randomUUID().toString() + "." + filenameExtention;
        //extractin the meta data
        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        //uploding the file in the aws s3
        //handling the exception in case any exception occurs
        try{
            amazonS3.putObject(BUCKET_NAME, key , file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Exception occured while uploding the file");
        }

        //giving the acess as public
        amazonS3.setObjectAcl(BUCKET_NAME, key , CannedAccessControlList.PublicRead);
        //retriving the url and returing the url
        return amazonS3.getUrl(BUCKET_NAME, key).toString();
    }
}
