package com.udemy.spring.practical.ticket.repository.impl;

import com.udemy.spring.practical.ticket.repository.TicketDAO;
import com.udemy.spring.practical.ticket.vo.BuyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class TicketDAOImpl implements TicketDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TransactionTemplate transactionTemplate2;

    /**
     * 카드 결제 및 티켓 구매 정보 저장
     *
     * @param buyVO
     */
    @Override
    public void buyTicket(BuyVO buyVO) {
        System.out.println("TicketDAO.buyTicket() called ...");

        transactionTemplate2.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    jdbcTemplate.update(new PreparedStatementCreator() {
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

                    jdbcTemplate.update(sql, new PreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, buyVO.getUserName());
                            pstmt.setInt(2, buyVO.getAmount());
                        }
                    });
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly(); // try-catch로 묶는 경우 사용. 안그러면 정상적으로 롤백 처리가 되지 않음.
                    System.out.println("ROLLBACK ...");
                    e.printStackTrace();
                }
            }
        });
    }
}
