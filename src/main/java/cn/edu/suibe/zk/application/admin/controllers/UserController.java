package cn.edu.suibe.zk.application.admin.controllers;

import cn.edu.suibe.zk.domain.domains.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private BeanFactory beanFactory;

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String index(Model uiModel) {
        User[] users = beanFactory.getBean("findAllUsers", User[].class);
        uiModel.addAttribute("users", users);
        return "/admin/user/index";
    }
}
