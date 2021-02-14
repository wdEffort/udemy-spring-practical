package com.udemy.spring.practical.ticket.service.impl;

import com.udemy.spring.practical.ticket.repository.TicketDAO;
import com.udemy.spring.practical.ticket.service.TicketService;
import com.udemy.spring.practical.ticket.vo.BuyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private TransactionTemplate transactionTemplate1;

    @Override
    public void buyTicket(BuyVO buyVO) {
        ticketDAO.buyTicket(buyVO);
    }

    /**
     * Transaction 1안에 Transaction 2가 2개 들어있는 형태의 메소드
     *
     * @param buyVO
     */
    @Override
    public void execute(BuyVO buyVO) {
        transactionTemplate1.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                //Transaction 2-1
                buyVO.setAmount(2);
                ticketDAO.buyTicket(buyVO);

                //Transaction 2-2
                buyVO.setAmount(10); // Fail
                ticketDAO.buyTicket(buyVO);
            }
        });
    }
}
