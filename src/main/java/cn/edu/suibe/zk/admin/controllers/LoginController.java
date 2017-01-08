package cn.edu.suibe.zk.admin.controllers;

import cn.edu.suibe.zk.admin.contracts.LoginContract;
import cn.edu.suibe.zk.admin.contracts.LogoutContract;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ns on 2017/1/8.
 */
@Controller
@Scope("session")
public class LoginController {

    @RequestMapping("/admin")
    public String show(Model uiModel, HttpServletRequest request) {
        uiModel.addAttribute("message", "hello zks 中文" + request.getSession().getId() +","+request.getSession().getAttribute("uid"));
        return "admin/login/show";
    }

    @RequestMapping("/admin/login")
    @ResponseBody
    public LoginContract login(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        LoginContract loginContract = new LoginContract();
        request.getSession().setAttribute("uid", "1");
        return loginContract;
    }

    @RequestMapping("/admin/logout")
    @ResponseBody
    public LogoutContract logout(HttpServletRequest request) {
        LogoutContract logoutContract = new LogoutContract();
        request.getSession().invalidate();
        return logoutContract;
    }
}
