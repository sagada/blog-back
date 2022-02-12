package com.my.blog.web.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3ImgUploadService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    public String upload(MultipartFile multipartFile, String domainName) throws IOException
    {
        System.out.println("#!@##!@#@!#!#!#!!#");
        File uploadFile = createUploadFileOnLocal(multipartFile);

        if (uploadFile == null)
            throw new RemoteException("파일 생성 에러");

        log.info("uploadFile.getName() {}", uploadFile.getName());
        String uploadFileName = domainName
                .concat("/")
                .concat(UUID.randomUUID().toString())
                .concat("/")
                .concat(uploadFile.getName());

        String newImgUrl = uploadImageOnS3(uploadFile, uploadFileName);
        removeUploadFile(uploadFile);

        log.info("newImgUrl : {}", newImgUrl);

        return newImgUrl;
    }

    private File createUploadFileOnLocal(MultipartFile file) throws IOException
    {
        if (file.getOriginalFilename() == null
                || file.getOriginalFilename().contains("%2F%2F")
                || file.getOriginalFilename().contains("//")
                || file.getOriginalFilename().contains(" "))
        {
            throw new RemoteException("File 명 에러입니다.");
        }
        File localFile = new File(Objects.requireNonNull(file.getOriginalFilename()));

        if (localFile.createNewFile())
        {
            try (FileOutputStream fos = new FileOutputStream(localFile))
            {
                fos.write(file.getBytes());
            }

            return localFile;
        }

        return null;
    }


    private String uploadImageOnS3(File uploadFile, String uploadFileName)
    {
        amazonS3Client.putObject(
                new PutObjectRequest(bucket, uploadFileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead)
        );

        // TODO 인코딩 문제 해결 해야됨 %2F%2F => //
        return amazonS3Client.getUrl(bucket, uploadFileName).toString().replace("%2F%2F","/");
    }

    private void removeUploadFile(File targetFile)
    {
        if (targetFile.delete())
        {
            log.info("Delete File {}", targetFile);
            return;
        }

        log.info("Delete File Fail {}", targetFile);
    }

    public List<String> uploads(List<MultipartFile> multipartFileList, String posts) throws IOException
    {
        List<String> imageList = new ArrayList<>();
        for (MultipartFile file : multipartFileList)
        {
            imageList.add(upload(file, posts));
        }

        return imageList;
    }
}
