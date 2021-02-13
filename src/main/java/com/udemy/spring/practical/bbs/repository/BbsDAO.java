package com.udemy.spring.practical.bbs.repository;

import com.udemy.spring.practical.bbs.vo.BbsVO;

import java.util.List;

public interface BbsDAO {

    List<BbsVO> list();

    void write(String bbsName, String bbsSbj, String bbsCtt);

    BbsVO view(int id);

    void modify(int bbsId, String bbsName, String bbsSbj, String bbsCtt);

    void delete(int bbsId);

    BbsVO replyForm(int id);

    void reply(int bbsId, String bbsName, String bbsSbj, String bbsCtt, int bbsGroup, int bbsStep, int bbsIndent);
}
