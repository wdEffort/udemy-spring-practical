package com.udemy.spring.practical.ticket.service.impl;

import com.udemy.spring.practical.ticket.repository.TicketDAO;
import com.udemy.spring.practical.ticket.service.TicketService;
import com.udemy.spring.practical.ticket.vo.BuyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public void buyTicket(BuyVO buyVO) {
        ticketDAO.buyTicket(buyVO);
    }
}
