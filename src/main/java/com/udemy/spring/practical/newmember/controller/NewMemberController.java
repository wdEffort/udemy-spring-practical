package com.udemy.spring.practical.newmember.controller;

import com.udemy.spring.practical.newmember.service.NewMemberSerivce;
import com.udemy.spring.practical.newmember.vo.NewMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String insertMember(NewMember newMember) {
        newMemberSerivce.insertMember(newMember);
        return "redirect:/newmember/list";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyMemberForm(@RequestParam("id") String id, Model model) {
        model.addAttribute("newMember", newMemberSerivce.getMember(id));
        return "newmember/modify";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String updateMember(NewMember newMember) {
        newMemberSerivce.updateMember(newMember);
        return "redirect:/newmember/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteMember(@RequestParam("id") String id) {
        newMemberSerivce.deleteMember(id);
        return "redirect:/newmember/list";
    }
}
