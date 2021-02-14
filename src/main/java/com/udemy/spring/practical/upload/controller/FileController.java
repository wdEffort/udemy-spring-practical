package com.udemy.spring.practical.upload.controller;

import com.udemy.spring.practical.upload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 파일 업로드 화면 요청
     *
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView uploadForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("file/form");


        return mv;
    }

    /**
     * 파일 업로드 요청
     *
     * @param multipartHttpServletRequest
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(MultipartHttpServletRequest multipartHttpServletRequest) {
        ModelAndView mv = new ModelAndView();

        if (fileUploadService.fileUpload(multipartHttpServletRequest)) {
            mv.addObject("message", "파일 업로드 성공");
        } else {
            mv.addObject("message", "파일 업로드 실패");
        }

        mv.setViewName("file/result");

        return mv;
    }

    /**
     * 파일 다운로드 요청
     *
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView download() {
        ModelAndView mv = new ModelAndView("fileDownloadView");

        return mv;
    }

}
