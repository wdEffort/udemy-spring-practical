package com.udemy.spring.practical.ticket.repository.impl;

import com.udemy.spring.practical.ticket.repository.TicketDAO;
import com.udemy.spring.practical.ticket.vo.BuyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class TicketDAOImpl implements TicketDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * 카드 결제 및 티켓 구매 정보 저장
     *
     * @param buyVO
     */
    @Override
    public void buyTicket(BuyVO buyVO) {
        TransactionDefinition td = new DefaultTransactionDefinition(); // 트랜잭션을 구성하기 위한 기본 설정 객체
        TransactionStatus ts = transactionManager.getTransaction(td); // 트랜잭션 상태 저장 객체(Commit, Rollback 때 사용)

        try {
            this.jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    String sql = "INSERT INTO CARD (USER_NAME, AMOUNT) VALUES (?, ?)";

                    PreparedStatement pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, buyVO.getUserName());
                    pstmt.setInt(2, buyVO.getAmount());

                    return pstmt;
                }
            });

            String sql = "INSERT INTO TICKET (USER_NAME, CNT) VALUES (?, ?)";

            this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement pstmt) throws SQLException {
                    pstmt.setString(1, buyVO.getUserName());
                    pstmt.setInt(2, buyVO.getAmount());
                }
            });

            transactionManager.commit(ts);
        } catch (Exception e) {
            e.printStackTrace();

            transactionManager.rollback(ts);
        }
    }
}
