package com.udemy.spring.practical.ticket.service;

import com.udemy.spring.practical.ticket.vo.BuyVO;

public interface TicketService {

    void buyTicket(BuyVO buyVO);

    void execute(BuyVO buyVO);
}
