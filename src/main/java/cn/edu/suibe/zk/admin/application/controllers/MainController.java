package cn.edu.suibe.zk.admin.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/admin/main", method = RequestMethod.GET)
    public String show() {
        return "admin/main/show";
    }
}
