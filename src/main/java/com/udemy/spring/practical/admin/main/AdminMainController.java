package com.udemy.spring.practical.admin.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminMainController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminMainPage() {
        return "admin/main/index";
    }
}
