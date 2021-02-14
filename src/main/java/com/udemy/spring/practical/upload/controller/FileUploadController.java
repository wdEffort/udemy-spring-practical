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
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView uploadForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("upload/form");


        return mv;
    }

    /**
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

        mv.setViewName("upload/result");

        return mv;
    }

}
