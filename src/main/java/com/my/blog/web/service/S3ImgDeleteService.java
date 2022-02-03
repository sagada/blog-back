package com.my.blog.web.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3ImgDeleteService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    public void deleteS3AllImage()
    {
        for (S3ObjectSummary s3Obj : amazonS3Client.listObjects(bucket).getObjectSummaries())
        {
            amazonS3Client.deleteObject(bucket, s3Obj.getKey());
        }
    }
}
