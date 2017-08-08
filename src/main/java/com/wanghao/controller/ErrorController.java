package com.wanghao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wanghao on 8/7/17.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {
    @RequestMapping(method = RequestMethod.GET)
    public String handleErrorCode(ModelMap model) {
        //model.addAttribute("errorCode", "Hello world!");
        return "error";
    }
}
