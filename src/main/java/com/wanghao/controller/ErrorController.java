package com.wanghao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wanghao on 8/7/17.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {
    @RequestMapping(method = RequestMethod.GET)
    public String _other() {
        return "error/other";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String _404(){
        return "error/404";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String _403(){
        return "error/403";
    }
}
