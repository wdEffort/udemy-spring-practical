package com.udemy.spring.practical.excel.controller;

import com.udemy.spring.practical.excel.vo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @RequestMapping(method = RequestMethod.GET)
    public String view() {
        return "excel/view";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView download() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("000001", "자바", "김길동", "2021-01-05", 28000));
        books.add(new Book("000002", "JSP", "박길동", "2021-01-10", 30000));
        books.add(new Book("000003", "오라클", "최길동", "2021-01-15", 35000));
        books.add(new Book("000004", "스프링", "홍길동", "2021-01-20", 31000));

        return new ModelAndView("result", "books", books);
    }
}
