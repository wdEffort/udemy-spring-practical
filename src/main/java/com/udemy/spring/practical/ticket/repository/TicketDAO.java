package com.udemy.spring.practical.ticket.repository;

import com.udemy.spring.practical.ticket.vo.BuyVO;

public interface TicketDAO {

    void buyTicket(BuyVO buyVO);
}
