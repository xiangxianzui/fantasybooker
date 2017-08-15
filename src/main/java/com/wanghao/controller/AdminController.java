package com.wanghao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wanghao on 8/15/17.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView admin() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Admin - Spring Security Hello World");
        model.addObject("message", "This is protected page!");
        model.setViewName("admin/index");
        return model;

    }
}
