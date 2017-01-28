package cn.edu.suibe.zk.admin.application.controllers;

import cn.edu.suibe.zk.admin.application.contracts.LoginContract;
import cn.edu.suibe.zk.admin.application.contracts.LogoutContract;
import cn.edu.suibe.zk.admin.application.controllers.forms.LoginForm;
import cn.edu.suibe.zk.admin.domain.domains.User;
import cn.edu.suibe.zk.admin.domain.models.UserModel;
import cn.edu.suibe.zk.admin.domain.repositories.UserRepository;
import cn.edu.suibe.zk.common.exceptions.BaseException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

            if(user == User.LOGIN_ERROR_USER) {
                redirectAttributes.addFlashAttribute("errorMessage", "用户名或者密码错误!");
                return "redirect:/admin";
            }

            UserModel userModel = user.getModel();
            HttpSession session = request.getSession();

            session.setAttribute("uid", userModel.getId());
            session.setAttribute("username", userModel.getUserName());
            session.setAttribute("trueName", userModel.getTrueName());

            return "redirect:/admin/main";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/admin";
        }
    }

    @RequestMapping("/admin/logout")
    @ResponseBody
    public LogoutContract logout(HttpServletRequest request) {
        LogoutContract logoutContract = new LogoutContract();
        request.getSession().invalidate();
        return logoutContract;
    }
}
