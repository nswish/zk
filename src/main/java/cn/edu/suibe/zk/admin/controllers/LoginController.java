package cn.edu.suibe.zk.admin.controllers;

import cn.edu.suibe.zk.admin.contracts.LoginContract;
import cn.edu.suibe.zk.admin.contracts.LogoutContract;
import cn.edu.suibe.zk.admin.models.User;
import cn.edu.suibe.zk.admin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ns on 2017/1/8.
 */
@Controller
@Scope("session")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/admin")
    public String show(Model uiModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        uiModel.addAttribute("message", "hello zks 中文" + session.getId() +","+ session.getAttribute("uid")+"/"+session.getAttribute("username"));
        return "admin/login/show";
    }

    @RequestMapping("/admin/login")
    @ResponseBody
    public LoginContract login(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        LoginContract loginContract = new LoginContract();
        User user = userRepository.findById(3);
        request.getSession().setAttribute("uid", user.getId());
        request.getSession().setAttribute("username", user.getUserName());
        request.getSession().setAttribute("truename", user.getTrueName());
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
