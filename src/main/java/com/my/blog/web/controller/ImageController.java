package com.my.blog.web.controller;

import com.my.blog.web.service.LocalImageUploadService;
import com.my.blog.web.service.S3ImgDeleteService;
import com.my.blog.web.service.S3ImgUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/img")
@Api(tags = {"이미지 컨트롤러"})
@RequiredArgsConstructor
public class ImageController {

    private final S3ImgUploadService s3ImgUploadService;
    private final S3ImgDeleteService s3ImgDeleteService;
    private final LocalImageUploadService localImageUploadService;

    @ApiOperation(value = "post 이미지 등록")
    @PostMapping("/s3/posts/upload")
    public ResponseEntity<String> s3Upload(MultipartFile multipartFile) throws IOException
    {
        String imgUrl = s3ImgUploadService.upload(multipartFile, "posts");
        return ResponseEntity.ok().body(imgUrl);
    }

    @DeleteMapping
    public void delete()
    {
        s3ImgDeleteService.deleteS3AllImage();
    }

    @Deprecated
    @PostMapping("/local/posts/upload")
    public ResponseEntity<String> localUpload(MultipartFile multipartFile) throws IOException
    {
        return ResponseEntity.ok()
                .body(localImageUploadService.upload(multipartFile, "/posts"));
    }


}
