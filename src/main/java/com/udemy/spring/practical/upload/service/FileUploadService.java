package com.udemy.spring.practical.upload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Service
public class FileUploadService {

    /**
     * 파일 업로드 메소드
     *
     * @param multipartHttpServletRequest
     * @return
     */
    public boolean fileUpload(MultipartHttpServletRequest multipartHttpServletRequest) {
        boolean isUpload = false;
        String uploadPath = "/Users/yoman/Workspace/Java/intelliJ/personal/udemy/spring-practical/upload"; // 파일 업로드 경로
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames(); // 요청으로 들어온 첨부파일 목록을 가져옴

        // 첨부파일 목록 수만큼 반복
        while (iterator.hasNext()) {
            String uploadFileName = iterator.next(); // 첨부된 파일 이름
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(uploadFileName); // 실제 업로드된 (임시)파일을 가져옴
            String originFileName = multipartFile.getOriginalFilename(); // 실제 파일명
            String saveFileName = originFileName; // 저장할 때의 파일명

            // 저장할 때의 파일명이 있는 경우
            if (saveFileName != null && !saveFileName.equals("")) {
                // 이미 존재하는 파일인 경우
                if (new File(uploadPath + "/" + saveFileName).exists()) {
                    saveFileName = saveFileName + "_" + System.currentTimeMillis();
                }

                try {
                    multipartFile.transferTo(new File(uploadPath + "/" + saveFileName)); // 파일 저장
                    isUpload = true;
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    isUpload = false;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    isUpload = false;
                }
            }
        }

        return isUpload;
    }
}
