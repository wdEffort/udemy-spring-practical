package com.udemy.spring.practical.bbs.service.impl;

import com.udemy.spring.practical.bbs.repository.BbsDAO;
import com.udemy.spring.practical.bbs.repository.impl.JdbcTemplateBbsDAO;
import com.udemy.spring.practical.bbs.service.BbsService;
import com.udemy.spring.practical.bbs.vo.BbsVO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class JdbcTemplateBbsServiceImpl implements BbsService {

    /**
     * 게시글 목록 조회
     *
     * @param model
     */
    @Override
    public void list(Model model) {
        BbsDAO bbsDAO = new JdbcTemplateBbsDAO();
        List<BbsVO> bbsList = bbsDAO.list();

        model.addAttribute("bbsList", bbsList);
    }

    /**
     * 게시글 작성
     *
     * @param model
     */
    @Override
    public void write(Model model) {
        Map<String, Object> map = model.asMap(); // Model 객체를 Map 형태로 변환
        HttpServletRequest request = (HttpServletRequest) map.get("request"); // Map 객체에서 request Model에 담긴 request 객체를 꺼내서 요청 파라미터를 사용한다.
        String bbsName = request.getParameter("bbsName");
        String bbsSbj = request.getParameter("bbsSbj");
        String bbsCtt = request.getParameter("bbsCtt");


        BbsDAO bbsDAO = new JdbcTemplateBbsDAO();
        bbsDAO.write(bbsName, bbsSbj, bbsCtt);
    }

    /**
     * 게시글 한건 조회
     *
     * @param model
     * @return
     */
    @Override
    public void view(Model model) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        int bbsId = Integer.parseInt(request.getParameter("bbsId"));

        BbsDAO bbsDAO = new JdbcTemplateBbsDAO();
        BbsVO bbsVO = bbsDAO.view(bbsId);

        model.addAttribute("bbsVO", bbsVO);
    }

    /**
     * 게시글 한건 수정
     *
     * @param model
     */
    @Override
    public void modify(Model model) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        int bbsId = Integer.parseInt(request.getParameter("bbsId"));
        String bbsName = request.getParameter("bbsName");
        String bbsSbj = request.getParameter("bbsSbj");
        String bbsCtt = request.getParameter("bbsCtt");

        BbsDAO bbsDAO = new JdbcTemplateBbsDAO();
        bbsDAO.modify(bbsId, bbsName, bbsSbj, bbsCtt);
    }

    /**
     * 게시글 한건 삭제
     *
     * @param model
     */
    @Override
    public void delete(Model model) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        int bbsId = Integer.parseInt(request.getParameter("bbsId"));

        BbsDAO bbsDAO = new JdbcTemplateBbsDAO();
        bbsDAO.delete(bbsId);
    }

    /**
     * @param model
     */
    @Override
    public void replyForm(Model model) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        int bbsId = Integer.parseInt(request.getParameter("bbsId"));

        BbsDAO bbsDAO = new JdbcTemplateBbsDAO();
        BbsVO bbsVO = bbsDAO.replyForm(bbsId);

        model.addAttribute("bbsVO", bbsVO);
    }

    /**
     * 게시글 답변 달기
     *
     * @param model
     */
    @Override
    public void reply(Model model) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        int bbsId = Integer.parseInt(request.getParameter("bbsId"));
        String bbsName = request.getParameter("bbsName");
        String bbsSbj = request.getParameter("bbsSbj");
        String bbsCtt = request.getParameter("bbsCtt");
        int bbsGroup = Integer.parseInt(request.getParameter("bbsGroup"));
        int bbsStep = Integer.parseInt(request.getParameter("bbsStep"));
        int bbsIndent = Integer.parseInt(request.getParameter("bbsIndent"));

        BbsDAO bbsDAO = new JdbcTemplateBbsDAO();
        bbsDAO.reply(bbsId, bbsName, bbsSbj, bbsCtt, bbsGroup, bbsStep, bbsIndent);
    }
}
