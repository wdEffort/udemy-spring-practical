package com.udemy.spring.practical.bbs.controller;

import com.udemy.spring.practical.bbs.service.BbsService;
import com.udemy.spring.practical.bbs.service.impl.JdbcTemplateBbsServiceImpl;
import com.udemy.spring.practical.bbs.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/bbs")
public class BbsController {

    @Autowired
    private BbsService bbsService;

    /**
     * BbsVO 커맨드 객체 생성 메소드
     * 해당 컨트롤러 모든 핸들러 메소드 및 View에서 사용 가능하다.
     *
     * @return
     */
    @ModelAttribute("bbs")
    public BbsVO formBacking() {
        return new BbsVO();
    }

    /**
     * 게시글 목록 페이지 요청
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        this.bbsService.list(model);

        return "bbs/list";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeForm(Model model) {
        return "bbs/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(HttpServletRequest request, Model model) {
        model.addAttribute("request", request);

        this.bbsService.write(model);

        return "redirect:/bbs/list";
    }

    /*
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request, Model model) {
        model.addAttribute("request", request);

        this.bbsService = new JdbcTemplateBbsServiceImpl();
        this.bbsService.view(model);

        return "bbs/view";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(HttpServletRequest request, Model model) {
        model.addAttribute("request", request);

        this.bbsService = new JdbcTemplateBbsServiceImpl();
        this.bbsService.modify(model);

        return "redirect:/bbs/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, Model model) {
        model.addAttribute("request", request);

        this.bbsService = new JdbcTemplateBbsServiceImpl();
        this.bbsService.delete(model);

        return "redirect:/bbs/list";
    }

    @RequestMapping(value = "/reply", method = RequestMethod.GET)
    public String replyForm(HttpServletRequest request, Model model) {
        model.addAttribute("request", request);

        this.bbsService = new JdbcTemplateBbsServiceImpl();
        this.bbsService.replyForm(model);

        return "bbs/reply";
    }

    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    public String reply(HttpServletRequest request, Model model) {
        model.addAttribute("request", request);

        this.bbsService = new JdbcTemplateBbsServiceImpl();
        this.bbsService.reply(model);

        return "redirect:/bbs/list";
    }
    */
}
