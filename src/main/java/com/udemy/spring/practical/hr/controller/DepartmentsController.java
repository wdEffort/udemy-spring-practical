package com.udemy.spring.practical.hr.controller;

import com.udemy.spring.practical.hr.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/departments")
public class DepartmentsController {

    private final String PAGE = "hr/departments";

    @Autowired
    private DepartmentsService departmentsService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getDepartments(HttpServletRequest request) {
        request.setAttribute("list", this.departmentsService.getDepartments());

        return String.format("%s/%s", PAGE, "list");
    }
}
