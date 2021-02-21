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
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/newmember")
@SessionAttributes("newMember")
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
        // 커맨드 객체의 여러 프로퍼티에 대한 오류를 체크한다.
        // 만약, "id"라는 하나의 프로퍼티에 대한 오류가 있는지 확인하려면
        // 'if (result.getFieldErrorCount(newMember.getId()) > 0) { // code ... }'과 같이 사용하면 된다.
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

        return "redirect:/newmember/modify/" + newMember.getId();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteMember(@PathVariable("id") String id) {
        newMemberSerivce.deleteMember(id);

        return "redirect:/newmember/list";
    }
}
