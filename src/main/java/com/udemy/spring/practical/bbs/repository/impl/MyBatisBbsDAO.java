package com.udemy.spring.practical.bbs.repository.impl;

import com.udemy.spring.practical.bbs.repository.BbsDAO;
import com.udemy.spring.practical.bbs.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MyBatis에서 DAO 인터페이스를 이용하므로 사용하지 않게 됨.
 */
@Repository
public class MyBatisBbsDAO implements BbsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BbsVO> list() {
        return null;
    }

    @Override
    public void write(String bbsName, String bbsSbj, String bbsCtt) {

    }

    @Override
    public BbsVO view(int id) {
        return null;
    }

    @Override
    public void modify(int bbsId, String bbsName, String bbsSbj, String bbsCtt) {

    }

    @Override
    public void delete(int bbsId) {

    }

    @Override
    public BbsVO replyForm(int id) {
        return null;
    }

    @Override
    public void reply(int bbsId, String bbsName, String bbsSbj, String bbsCtt, int bbsGroup, int bbsStep, int bbsIndent) {

    }
}
