package com.my.blog.web.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3ImageInitializeService {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    @PostConstruct
    public void init()
    {
        ObjectListing objectListing = amazonS3Client.listObjects(bucket);

        log.info("S3 Bucket Initialize Start ...");
        for (S3ObjectSummary s3ObjectSummary : objectListing.getObjectSummaries())
        {
            amazonS3Client.deleteObject(bucket, s3ObjectSummary.getKey());
        }
        log.info("S3 Bucket Initialize End ...");

    }
}
