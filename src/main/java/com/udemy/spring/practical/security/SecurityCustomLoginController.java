package com.udemy.spring.practical.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityCustomLoginController {

    @RequestMapping(value = "/security/login", method = RequestMethod.GET)
    public String getSecurityCustomLoginPage() {
        return "security/login";
    }

    @RequestMapping(value = "/security/logon", method = RequestMethod.GET)
    public String getSecurityCustomLogonPage() {
        return "security/logon";
    }
}
