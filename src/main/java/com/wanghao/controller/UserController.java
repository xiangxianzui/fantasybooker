package com.wanghao.controller;

import com.wanghao.common.Constant;
import com.wanghao.db.model.UserInfoModel;
import com.wanghao.service.enums.LoginMsg;
import com.wanghao.service.enums.LogoutMsg;
import com.wanghao.service.enums.RegisterMsg;
import com.wanghao.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("user/register").addObject(Constant.REGISTER_USER, new UserInfoModel());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //@Validated在服务器端验证post过来的User对象
    public String register(ModelMap model, @Validated @ModelAttribute(Constant.REGISTER_USER) UserInfoModel registerUser, BindingResult br) {//验证结果一定要紧跟@Validated参数后面写
        if (br.hasErrors()) {
            return "user/register";
        } else {
            String result = userService.register(registerUser);
            if (result.equals(RegisterMsg.SUCCESS.extValue())) {
                return "redirect:login";
            }
            if (result.equals(RegisterMsg.FAIL_NICK.extValue())) {
                model.addAttribute("result", result);
                return "user/register";
            }
            if (result.equals(RegisterMsg.FAIL_EMAIL.extValue())) {
                model.addAttribute("result", result);
                return "user/register";
            }
            if (result.equals(RegisterMsg.FAIL_DB.extValue())) {
                model.addAttribute("result", result);
                return "user/register";
            }
            model.addAttribute("result", result);
            return "user/register";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        UserInfoModel userInfoModel = new UserInfoModel();
        ModelAndView mav = new ModelAndView("user/login");
        mav.addObject(Constant.LOGIN_USER, userInfoModel);
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest req, ModelMap model,
                        @Validated @ModelAttribute(Constant.LOGIN_USER) UserInfoModel loginUser,
                        BindingResult br) {//验证结果一定要紧跟@Validated参数后面写
        if (br.hasErrors()) {
            return "user/login";
        } else {
            String result = userService.login(loginUser, req);
            if (result.equals(LoginMsg.SUCCESS_NICKNAME.extValue())) {
                return "redirect:../index";
            }
            if (result.equals(LoginMsg.SUCCESS_EMAIL.extValue())) {
                return "redirect:../index";
            }
            if (result.equals(LoginMsg.FAIL_NO_USER.extValue())) {
                model.addAttribute("result", result);
                return "user/login";
            }
            if (result.equals(LoginMsg.FAIL_PASSWORD.extValue())) {
                model.addAttribute("result", result);
                return "user/login";
            }
            if (result.equals(LoginMsg.FAIL_DB.extValue())) {
                model.addAttribute("result", result);
                return "user/login";
            }
            model.addAttribute("result", result);
            return "user/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        String result = userService.logout(session);
        if(result.equals(LogoutMsg.SUCCESS.extValue())){
            return "redirect:../index";
        }
        if(result.equals(LogoutMsg.FAIL.extValue())){
            return "redirect:../index";
        }
        return "redirect:../index";
    }

    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public String activate(@RequestParam(value="name",required=true) String nickName, @RequestParam(value="code",required=true) String userCode){
        String result = userService.activate(nickName, userCode);
        return "index";
    }

    @RequestMapping(value = "/password/find", method = RequestMethod.GET)
    public ModelAndView findPsw(){
        return new ModelAndView("user/findPsw").addObject(Constant.FIND_PSW_USER, new UserInfoModel());
    }

    @RequestMapping(value = "/password/find", method = RequestMethod.POST)
    public String findPsw(ModelMap model, @ModelAttribute(Constant.FIND_PSW_USER) UserInfoModel findPswUser) {
        String result = userService.findPsw(findPswUser);
        model.addAttribute("result", result);
        return "user/findPsw";
    }

    @RequestMapping(value = "/password/reset", method = RequestMethod.GET)
    public ModelAndView resetPsw(@RequestParam(value="name",required=true) String nickName, @RequestParam(value="code",required=true) String userCode){
        UserInfoModel resetPswUser = new UserInfoModel();
        resetPswUser.setNickname(nickName);
        resetPswUser.setUserCode(userCode);
        return new ModelAndView("user/resetPsw").addObject(Constant.RESET_PSW_USER, resetPswUser);
    }

    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
    public String resetPsw(ModelMap model, @ModelAttribute(Constant.RESET_PSW_USER) UserInfoModel resetPswUser) {
        String result = userService.resetPsw(resetPswUser);
        model.addAttribute("result", result);
        return "user/resetPsw";
    }

    @RequestMapping(value = "/{userCode}", method = RequestMethod.GET)
    public ModelAndView viewUserDetail(@PathVariable String userCode){
        UserInfoModel loginUser = userService.viewUserDetail(userCode);
        if(loginUser != null){
            ModelAndView mav = new ModelAndView("user/detail");
            mav.addObject(Constant.LOGIN_USER, loginUser);
            return mav;
        }
        else{
            ModelAndView mav = new ModelAndView("error/other");
            return mav;
        }
    }

    @RequestMapping(value = "/{userCode}", method = RequestMethod.POST)
    public String updateUserDetail(ModelMap model,
                                   @PathVariable String userCode,
                                   @ModelAttribute(Constant.LOGIN_USER) UserInfoModel loginUser){
        String result = userService.updateUserDetail(loginUser);
        model.addAttribute("result", result);
        return "user/detail";
    }
}