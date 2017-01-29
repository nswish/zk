package cn.edu.suibe.zk.application.admin.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @RequestMapping(value = "/admin/main", method = RequestMethod.GET)
    public String show(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        return "/admin/main/show";
    }
}
