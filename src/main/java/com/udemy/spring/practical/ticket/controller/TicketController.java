package com.udemy.spring.practical.ticket.controller;

import com.udemy.spring.practical.ticket.service.TicketService;
import com.udemy.spring.practical.ticket.vo.BuyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @ModelAttribute("buy")
    public BuyVO formBacking() {
        return new BuyVO();
    }

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String ticketForm() {
        return "ticket/buy";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String ticket(BuyVO buyVO, Model model) {
        //ticketService.buyTicket(buyVO);
        ticketService.execute(buyVO);

        model.addAttribute("buyVO", buyVO);

        return "ticket/result";
    }
}
