package cn.edu.suibe.zk.application.admin.controllers;

import cn.edu.suibe.zk.application.admin.controllers.forms.LoginForm;
import cn.edu.suibe.zk.domain.domains.User;
import cn.edu.suibe.zk.domain.repositories.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ns on 2017/1/8.
 */
@Controller
@Scope("session")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BeanFactory beanFactory;

    @RequestMapping("/admin")
    public String show(LoginForm loginForm, HttpServletRequest request) {
        return "admin/login/show";
    }

    @RequestMapping(value="/admin/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, LoginForm loginForm, RedirectAttributes redirectAttributes) {
        try {
            User user = beanFactory.getBean(User.class, loginForm.getUsername(), loginForm.getPassword());

            if(user == User.AUTHENTICATE_FAILED_USER) {
                redirectAttributes.addFlashAttribute("errorMessage", "用户名或者密码错误!");
                return "redirect:/admin";
            }

            request.getSession().setAttribute("user", user);

            return "redirect:/admin/main";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/admin/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/admin";
    }
}
