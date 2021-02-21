package com.udemy.spring.practical.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/http")
public class HttpSessionController {

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public String httpSession(HttpSession session, Model model) {
        // 세션 객체에 "admin"이라는 값을 "id"라는 key로 저장한다.
        session.setAttribute("id", "admin");

        // 모델 객체에 현재 클래스 이름을 "className"이라는 key로 저장한다.
        model.addAttribute("className", this.getClass());

        return "session/httpSession";
    }
}
