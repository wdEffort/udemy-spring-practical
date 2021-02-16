package com.udemy.spring.practical.member.controller;

import com.udemy.spring.practical.member.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/member")
public class MemberController {

    /**
     * 회원가입 화면 요청
     *
     * @return
     */
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinForm(Member member) {
        return "member/join";
    }

    /**
     * 회원가입 요청
     *
     * @param member 커맨드 객체(검증 대상 객체)
     * @param result   커맨드 객체 검증에 대한 에러 정보를 가지고 있는 객체
     * @return
     */
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(@Valid Member member, BindingResult result) {
        if (result.hasErrors()) {
            return "/member/join";
        } else {
            System.out.println("가입완료 ...");
            return "/member/join";
        }
    }
}
