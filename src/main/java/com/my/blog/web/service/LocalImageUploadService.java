package com.my.blog.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalImageUploadService {

    private final ResourceLoader resourceLoader;

    public String upload(MultipartFile multipartFile, String domainName) throws IOException
    {
        File uploadFile = createUploadFileOnLocal(multipartFile, domainName);

        if (uploadFile == null)
        {
            throw new RemoteException("파일 생성 에러");
        }

        log.info("uploadFile.getName() {}", uploadFile.getName());

        return uploadFile.getName();
    }

    private File createUploadFileOnLocal(MultipartFile file, String domainName) throws IOException
    {
        if (file.getOriginalFilename() == null
                || file.getOriginalFilename().contains("//")
                || file.getOriginalFilename().contains(" "))
        {
            throw new RemoteException("File 이름 에러입니다.");
        }

        String uploadFileName = domainName
                .concat("/")
                .concat(UUID.randomUUID().toString())
                .concat(".img");

        File localFile = new File("절대경로입니다...." + uploadFileName);

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


}
