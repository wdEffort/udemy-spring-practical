package com.udemy.spring.practical.bbs.repository.impl;

import com.udemy.spring.practical.bbs.repository.BbsDAO;
import com.udemy.spring.practical.bbs.vo.BbsVO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandBbsDAO implements BbsDAO {

    private DataSource dataSource;

    public CommandBbsDAO() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g"); // Context에서 DataSource 얻어옴
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 데이터베이스에 접속하여 게시글 목록 조회
     *
     * @return
     */
    @Override
    public List<BbsVO> list() {
        List<BbsVO> bbsList = new ArrayList<BbsVO>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();

            String sql = "SELECT BBS_ID, BBS_NAME, BBS_SBJ, BBS_CTT, " +
                    "BBS_DATE, BBS_HIT, BBS_GROUP, BBS_STEP, BBS_INDENT " +
                    "FROM BBS " +
                    "ORDER BY BBS_GROUP DESC, BBS_STEP ASC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int bbsId = rs.getInt("BBS_ID");
                String bbsName = rs.getString("BBS_NAME");
                String bbsSbj = rs.getString("BBS_SBJ");
                String bbsCtt = rs.getString("BBS_CTT");
                Timestamp bbsDate = rs.getTimestamp("BBS_DATE");
                int bbsHit = rs.getInt("BBS_HIT");
                int bbsGroup = rs.getInt("BBS_GROUP");
                int bbsStep = rs.getInt("BBS_STEP");
                int bbsIndent = rs.getInt("BBS_INDENT");

                BbsVO bbsVO = new BbsVO(bbsId, bbsName, bbsSbj, bbsCtt, bbsDate, bbsHit, bbsGroup, bbsStep, bbsIndent);

                bbsList.add(bbsVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 리소스 반환
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bbsList;
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
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();

            String sql = "INSERT INTO BBS " +
                    "(BBS_ID, BBS_NAME, BBS_SBJ, BBS_CTT, BBS_HIT, BBS_GROUP, BBS_STEP, BBS_INDENT) " +
                    "VALUES (SEQ_BBS.NEXTVAL, ?, ?, ?, 0, SEQ_BBS.CURRVAL, 0, 0)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bbsName);
            pstmt.setString(2, bbsSbj);
            pstmt.setString(3, bbsCtt);

            int num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 리소스 반환
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

        BbsVO bbsVO = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();

            String sql = "SELECT BBS_ID, BBS_NAME, BBS_SBJ, BBS_CTT, " +
                    "BBS_DATE, BBS_HIT, BBS_GROUP, BBS_STEP, BBS_INDENT " +
                    "FROM BBS " +
                    "WHERE BBS_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int bbsId = rs.getInt("BBS_ID");
                String bbsName = rs.getString("BBS_NAME");
                String bbsSbj = rs.getString("BBS_SBJ");
                String bbsCtt = rs.getString("BBS_CTT");
                Timestamp bbsDate = rs.getTimestamp("BBS_DATE");
                int bbsHit = rs.getInt("BBS_HIT");
                int bbsGroup = rs.getInt("BBS_GROUP");
                int bbsStep = rs.getInt("BBS_STEP");
                int bbsIndent = rs.getInt("BBS_INDENT");

                bbsVO = new BbsVO(bbsId, bbsName, bbsSbj, bbsCtt, bbsDate, bbsHit, bbsGroup, bbsStep, bbsIndent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 리소스 반환
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bbsVO;
    }

    /**
     * 데이터베이스에 접속하여 게시글 조회수 증가
     *
     * @param bbsId
     */
    private void addHit(int bbsId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();

            String sql = "UPDATE BBS SET BBS_HIT = BBS_HIT + 1 WHERE BBS_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bbsId);

            int num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 리소스 반환
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();

            String sql = "UPDATE BBS SET BBS_NAME = ?, BBS_SBJ = ?, BBS_CTT = ? WHERE BBS_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bbsName);
            pstmt.setString(2, bbsSbj);
            pstmt.setString(3, bbsCtt);
            pstmt.setInt(4, bbsId);

            int num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 리소스 반환
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 데이터베이스에 접속하여 게시글 삭제
     *
     * @param bbsId
     */
    @Override
    public void delete(int bbsId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();

            String sql = "DELETE FROM BBS WHERE BBS_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bbsId);

            int num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 리소스 반환
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 데이터베이스에 접속하여 부모 게시글 조회
     *
     * @param id
     * @return
     */
    @Override
    public BbsVO replyForm(int id) {
        BbsVO bbsVO = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();

            String sql = "SELECT BBS_ID, BBS_NAME, BBS_SBJ, BBS_CTT, " +
                    "BBS_DATE, BBS_HIT, BBS_GROUP, BBS_STEP, BBS_INDENT " +
                    "FROM BBS " +
                    "WHERE BBS_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int bbsId = rs.getInt("BBS_ID");
                String bbsName = rs.getString("BBS_NAME");
                String bbsSbj = rs.getString("BBS_SBJ");
                String bbsCtt = rs.getString("BBS_CTT");
                Timestamp bbsDate = rs.getTimestamp("BBS_DATE");
                int bbsHit = rs.getInt("BBS_HIT");
                int bbsGroup = rs.getInt("BBS_GROUP");
                int bbsStep = rs.getInt("BBS_STEP");
                int bbsIndent = rs.getInt("BBS_INDENT");

                bbsVO = new BbsVO(bbsId, bbsName, bbsSbj, bbsCtt, bbsDate, bbsHit, bbsGroup, bbsStep, bbsIndent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 리소스 반환
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bbsVO;
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

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();

            String sql = "INSERT INTO BBS " +
                    "(BBS_ID, BBS_NAME, BBS_SBJ, BBS_CTT, BBS_GROUP, BBS_STEP, BBS_INDENT) " +
                    "VALUES (SEQ_BBS.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bbsName);
            pstmt.setString(2, bbsSbj);
            pstmt.setString(3, bbsCtt);
            pstmt.setInt(4, bbsGroup);
            pstmt.setInt(5, (bbsStep + 1));
            pstmt.setInt(6, (bbsIndent + 1));

            int num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 데이터베이스에 접속하여 게시글 그룹에 대한 정보 수정
     * 답변 관련 설정
     *
     * @param group
     * @param step
     */
    private void replySet(int group, int step) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();

            String sql = "UPDATE BBS SET BBS_STEP = BBS_STEP + 1 WHERE BBS_GROUP = ? AND BBS_STEP > ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, group);
            pstmt.setInt(2, step);

            int num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
