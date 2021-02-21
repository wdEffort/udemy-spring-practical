package com.udemy.spring.practical.newmember.controller;

import com.udemy.spring.practical.newmember.service.NewMemberSerivce;
import com.udemy.spring.practical.newmember.vo.NewMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/newmember")
public class NewMemberController {

    @Autowired
    private NewMemberSerivce newMemberSerivce;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getAllMembers(Model model) {
        model.addAttribute("newMemberList", newMemberSerivce.getAllMembers());

        return "newmember/list";
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinMemberForm(Model model) {
        model.addAttribute("newMember", new NewMember());

        return "newmember/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String insertMember(@Valid NewMember newMember, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("회원가입 처리중 오류가 발생하였습니다.");
            return "newmember/join";
        }

        newMemberSerivce.insertMember(newMember);

        return "redirect:/newmember/list";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modifyMemberForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("newMember", newMemberSerivce.getMember(id));
        return "newmember/modify";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String updateMember(@Valid NewMember newMember, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("회원정보 수정 처리중 오류가 발생하였습니다.");
            return "newmember/modify";
        }

        newMemberSerivce.updateMember(newMember);

        return "redirect:/newmember/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteMember(@PathVariable("id") String id) {
        newMemberSerivce.deleteMember(id);

        return "redirect:/newmember/list";
    }
}
