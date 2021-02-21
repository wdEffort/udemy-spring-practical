package com.udemy.spring.practical.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/spring")
@SessionAttributes({"id", "name"}) // 모델 객체에 "id", "name"라는 key에 값이 설정될 때 세션에 저장하게 된다.(여러 개 설정 가능)
public class SpringSessionController {

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public String springSession(Model model) {
        // 모델 객체에 "admin"이라는 값을 "id"라는 key로 저장한다.
        model.addAttribute("id", "admin");

        // 모델 객체에 "관리자"이라는 값을 "name"라는 key로 저장한다.
        model.addAttribute("name", "관리자");

        // 모델 객체에 현재 클래스 이름을 "className"이라는 key로 저장한다.
        model.addAttribute("className", this.getClass());

        return "session/springSession";
    }
}
