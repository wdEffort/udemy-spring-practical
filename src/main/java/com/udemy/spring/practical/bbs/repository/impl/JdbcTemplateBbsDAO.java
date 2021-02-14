package com.udemy.spring.practical.bbs.repository.impl;

import com.udemy.spring.practical.bbs.repository.BbsDAO;
import com.udemy.spring.practical.bbs.vo.BbsVO;
import com.udemy.spring.practical.template.StaticJdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplateBbsDAO implements BbsDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateBbsDAO() {
        this.jdbcTemplate = StaticJdbcTemplate.jdbcTemplate;
    }

    /**
     * 데이터베이스에 접속하여 게시글 목록 조회
     *
     * @return
     */
    @Override
    public List<BbsVO> list() {
        String sql = "SELECT BBS_ID, BBS_NAME, BBS_SBJ, BBS_CTT, BBS_DATE, BBS_HIT, BBS_GROUP, BBS_STEP, BBS_INDENT FROM BBS ORDER BY BBS_GROUP DESC, BBS_STEP ASC";

        return (List<BbsVO>) this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<BbsVO>(BbsVO.class));
    }


    /**
     * 데이터베이스에 접속하여 게시글 작성
     *
     * @param bbsName
     * @param bbsSbj
     * @param bbsCtt
     * @return
     */
    @Override
    public void write(String bbsName, String bbsSbj, String bbsCtt) {
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql = "INSERT INTO BBS (BBS_ID, BBS_NAME, BBS_SBJ, BBS_CTT, BBS_HIT, BBS_GROUP, BBS_STEP, BBS_INDENT) VALUES (SEQ_BBS.NEXTVAL, ?, ?, ?, 0, SEQ_BBS.CURRVAL, 0, 0)";

                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, bbsName);
                pstmt.setString(2, bbsSbj);
                pstmt.setString(3, bbsCtt);

                return pstmt;
            }
        });
    }

    /**
     * 데이터베이스에 접속하여 게시글 한건 조회
     * 게시글 조회수 증가 처리를 포함한다.
     *
     * @param id
     * @return
     */
    @Override
    public BbsVO view(int id) {
        addHit(id);

        String sql = "SELECT BBS_ID, BBS_NAME, BBS_SBJ, BBS_CTT, BBS_DATE, BBS_HIT, BBS_GROUP, BBS_STEP, BBS_INDENT FROM BBS WHERE BBS_ID = " + id;

        return (BbsVO) this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<BbsVO>(BbsVO.class));
    }

    /**
     * 데이터베이스에 접속하여 게시글 조회수 증가
     *
     * @param bbsId
     */
    private void addHit(int bbsId) {
        String sql = "UPDATE BBS SET BBS_HIT = BBS_HIT + 1 WHERE BBS_ID = ?";

        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, bbsId);
            }
        });
    }

    /**
     * 데이터베이스에 접속하여 게시글 수정
     *
     * @param bbsId
     * @param bbsName
     * @param bbsSbj
     * @param bbsCtt
     */
    @Override
    public void modify(int bbsId, String bbsName, String bbsSbj, String bbsCtt) {
        String sql = "UPDATE BBS SET BBS_NAME = ?, BBS_SBJ = ?, BBS_CTT = ? WHERE BBS_ID = ?";

        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, bbsName);
                pstmt.setString(2, bbsSbj);
                pstmt.setString(3, bbsCtt);
                pstmt.setInt(4, bbsId);
            }
        });
    }

    /**
     * 데이터베이스에 접속하여 게시글 삭제
     *
     * @param bbsId
     */
    @Override
    public void delete(int bbsId) {
        String sql = "DELETE FROM BBS WHERE BBS_ID = ?";

        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, bbsId);
            }
        });
    }

    /**
     * 데이터베이스에 접속하여 부모 게시글 조회
     *
     * @param id
     * @return
     */
    @Override
    public BbsVO replyForm(int id) {
        String sql = "SELECT BBS_ID, BBS_NAME, BBS_SBJ, BBS_CTT, BBS_DATE, BBS_HIT, BBS_GROUP, BBS_STEP, BBS_INDENT FROM BBS WHERE BBS_ID = " + id;

        return (BbsVO) this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<BbsVO>(BbsVO.class));
    }

    /**
     * 데이터베이스에 접속하여 부모 게시글에 대한 답변 게시글 작성
     *
     * @param bbsId
     * @param bbsName
     * @param bbsSbj
     * @param bbsCtt
     * @param bbsGroup
     * @param bbsStep
     * @param bbsIndent
     */
    @Override
    public void reply(int bbsId, String bbsName, String bbsSbj, String bbsCtt, int bbsGroup, int bbsStep, int bbsIndent) {

        replySet(bbsGroup, bbsStep);

        String sql = "INSERT INTO BBS (BBS_ID, BBS_NAME, BBS_SBJ, BBS_CTT, BBS_GROUP, BBS_STEP, BBS_INDENT) VALUES (SEQ_BBS.NEXTVAL, ?, ?, ?, ?, ?, ?)";

        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, bbsName);
                pstmt.setString(2, bbsSbj);
                pstmt.setString(3, bbsCtt);
                pstmt.setInt(4, bbsGroup);
                pstmt.setInt(5, bbsStep + 1);
                pstmt.setInt(6, bbsIndent + 1);
            }
        });
    }

    /**
     * 데이터베이스에 접속하여 게시글 그룹에 대한 정보 수정
     * 답변 관련 설정
     *
     * @param group
     * @param step
     */
    private void replySet(int group, int step) {
        String sql = "UPDATE BBS SET BBS_STEP = BBS_STEP + 1 WHERE BBS_GROUP = ? AND BBS_STEP > ?";

        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, group);
                pstmt.setInt(2, step);
            }
        });
    }
}
