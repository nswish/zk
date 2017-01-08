package cn.edu.suibe.zk.admin.controllers;

import cn.edu.suibe.zk.admin.contracts.LoginContract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ns on 2017/1/8.
 */
@Controller
public class LoginController {

    @RequestMapping("/admin")
    public String show(Model uiModel) {
        uiModel.addAttribute("message", "hello zks 中文");
        return "admin/login/show";
    }

    @RequestMapping("/admin/login")
    @ResponseBody
    public LoginContract login() {
        LoginContract a = new LoginContract();
        return a;
    }
}
