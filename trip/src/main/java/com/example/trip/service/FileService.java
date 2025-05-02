package com.example.trip.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService {
    private final Path fileStorageLocation;
    //@Value("${file.upload-dir}")는 application.properties의 변수값 가져오기
    public FileService(@Value("${file.upload-dir}") String uploadDir) throws IOException {
        //매개변수로 전달받은 문자열을 경로로 바꿔주기
        //toAbsolutePath() : 절대경로로 초기화
        this.fileStorageLocation = Paths.get(uploadDir).
                toAbsolutePath().normalize();
        //경로가 없으면 생성
        Files.createDirectories(this.fileStorageLocation);
    }
    //파일 저장하는 메서드
    public String storeFile(MultipartFile file) throws IOException {
        //파일의 원본 파일명을 originalFileName변수에 저장
        String originalFileName = file.getOriginalFilename();
        //파일 확장자 저장하는 변수
        String fileExtension = "";
        //만약 파일명이 null이 아니면 <.확장자> 부분만 떼어서 fileExtension변수에 저장
        if(originalFileName != null && originalFileName.contains(".")){
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        //고유한 파일이름 생성
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
        Path targetLocation = fileStorageLocation.resolve(uniqueFileName);
        //파일 저장
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return "/upload/" + uniqueFileName;
    }

    //기존 이미지 파일 삭제
    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
