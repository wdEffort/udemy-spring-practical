package com.udemy.spring.practical.bbs.service.impl;

import com.udemy.spring.practical.bbs.repository.BbsDAO;
import com.udemy.spring.practical.bbs.service.BbsService;
import com.udemy.spring.practical.bbs.vo.BbsVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class MyBatisBbsServiceImpl implements BbsService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void list(Model model) {
        BbsDAO bbsDAO = sqlSession.getMapper(BbsDAO.class);

        List<BbsVO> bbsList = bbsDAO.list();

        model.addAttribute("bbsList", bbsList);
    }

    @Override
    public void write(Model model) {
        Map<String, Object> map = model.asMap(); // Model 객체를 Map 형태로 변환
        HttpServletRequest request = (HttpServletRequest) map.get("request"); // Map 객체에서 request Model에 담긴 request 객체를 꺼내서 요청 파라미터를 사용한다.
        String bbsName = request.getParameter("bbsName");
        String bbsSbj = request.getParameter("bbsSbj");
        String bbsCtt = request.getParameter("bbsCtt");

        //this.bbsDAO.write(bbsName, bbsSbj, bbsCtt);
    }

    @Override
    public void view(Model model) {

    }

    @Override
    public void modify(Model model) {

    }

    @Override
    public void delete(Model model) {

    }

    @Override
    public void replyForm(Model model) {

    }

    @Override
    public void reply(Model model) {

    }
}
