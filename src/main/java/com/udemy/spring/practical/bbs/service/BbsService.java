package com.udemy.spring.practical.bbs.service;

import org.springframework.ui.Model;

public interface BbsService {

    void list(Model model);

    void write(Model model);

    void view(Model model);

    void modify(Model model);

    void delete(Model model);

    void replyForm(Model model);

    void reply(Model model);
}
