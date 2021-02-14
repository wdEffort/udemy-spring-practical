package com.udemy.spring.practical.upload.view;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

public class FileDownloadView extends AbstractView {

    /**
     * 클래스에서 사용할 컨텐츠 타입을 지정
     */
    public FileDownloadView() {
        setContentType("application/download; charset=utf-8");
    }


    /**
     * 파일 다운로드 메소드
     *
     * @param map
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        System.out.println("FileDownloadService.renderMergedOutputModel() called ...");

        String uploadPath = "/Users/yoman/Workspace/Java/intelliJ/personal/udemy/spring-practical/upload"; // 파일 업로드 경로
        String savedFileName = "test.txt";
        File file = new File(uploadPath + "/" + savedFileName); // 파일 경로에서 파일을 가져옴

        response.setContentType(getContentType()); // 컨텐츠 타입(MIME 타입 지정, 캐릭터 인코딩 지정 등)
        response.setContentLength(100); // 파일의 크기

        String userAgent = request.getHeader("User-Agent"); // 헤더 정보에서 접속 기기 정보를 가져옴
        String fileName = null;

        // 한글 파일명에 대한 깨짐 현상 처리
        if (userAgent.indexOf("MSIE") > -1) { // 이용하는 브랑우저가 IE인 경우
            System.out.println();
            fileName = URLEncoder.encode(file.getName(), "UTF-8");
        } else {
            fileName = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        OutputStream out = response.getOutputStream();
        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
            FileCopyUtils.copy(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("IOException : " + e.toString());
                }
            }
        }

        out.flush();
    }
}
