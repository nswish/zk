package cn.edu.suibe.zk.application.admin.controllers;

import cn.edu.suibe.zk.application.admin.controllers.forms.UserForm;
import cn.edu.suibe.zk.common.configures.Constants;
import cn.edu.suibe.zk.domain.domains.User;
import cn.edu.suibe.zk.domain.models.UserModel;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private BeanFactory beanFactory;

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String index(Model uiModel) {
        User[] users = beanFactory.getBean("findAllUsers", User[].class);

        uiModel.addAttribute("users", users);

        return "/admin/users/index";
    }

    @RequestMapping(value = "/admin/users/create", method = RequestMethod.GET)
    public String showCreate(UserForm userForm) {
        userForm.setState(Constants.USER_STATE.AUDITED);
        userForm.setRoleid(Constants.USER_ROLE.MEMBER);
        return "/admin/users/create";
    }

    @RequestMapping(value = "/admin/users/create", method = RequestMethod.POST)
    public String saveCreate(@Valid UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", bindingResult.getFieldError().getDefaultMessage());
            redirectAttributes.addFlashAttribute("userForm", userForm);
            return "redirect:/admin/users/create";
        }

        if(!userForm.getPassword().equals(userForm.getPassword2())) {
            redirectAttributes.addFlashAttribute("message", "2次输入的密码不一致!");
            redirectAttributes.addFlashAttribute("userForm", userForm);
            return "redirect:/admin/users/create";
        }

        User newUser = beanFactory.getBean("newUser", User.class);

        UserModel model = newUser.getModel();
        model.setUserName(userForm.getUserName());
        model.setPassword(User.convertPassword(userForm.getPassword()));
        model.setTrueName(userForm.getTrueName());
        model.setEmail(userForm.getEmail());
        model.setTelephone(userForm.getTelephone());
        model.setState(userForm.getState());
        model.setRoleid(userForm.getRoleid());

        try {
            newUser.save();

            redirectAttributes.addFlashAttribute("message", String.format("用户[%s]已保存!", userForm.getUserName()));
            return "redirect:/admin/users";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/admin/users/create";
        }
    }

    @RequestMapping(value = "/admin/users/{id}/edit", method = RequestMethod.GET)
    public String showEdit(@PathVariable int id, UserForm userForm) {
        User user = (User)beanFactory.getBean("findUserById", id);
        UserModel model = user.getModel();

        userForm.setId(id);
        userForm.setUserName(model.getUserName());
        userForm.setTrueName(model.getTrueName());
        userForm.setEmail(model.getEmail());
        userForm.setTelephone(model.getTelephone());
        userForm.setState(model.getState());
        userForm.setRoleid(model.getRoleid());

        return "/admin/users/edit";
    }

    @RequestMapping(value ="/admin/users/{id}/edit", method = RequestMethod.POST)
    public String saveEdit(@PathVariable int id, UserForm userForm, RedirectAttributes redirectAttributes) {
        User user = (User)beanFactory.getBean("findUserById", id);
        UserModel model = user.getModel();

        model.setTrueName(userForm.getTrueName());
        model.setEmail(userForm.getEmail());
        model.setTelephone(userForm.getTelephone());
        model.setState(userForm.getState());
        model.setRoleid(userForm.getRoleid());

        user.save();

        redirectAttributes.addFlashAttribute("message", "用户信息已修改!");
        return String.format("redirect:/admin/users/%d/edit", id);
    }

    @RequestMapping(value = "/admin/users/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable int id, RedirectAttributes redirectAttributes) {
        User user = (User)beanFactory.getBean("findUserById", id);

        user.delete();

        redirectAttributes.addFlashAttribute("message", String.format("用户[%s]已删除!", user.getModel().getUserName()));

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/users/{id}/password", method = RequestMethod.GET)
    public String showResetPassword(@PathVariable int id, UserForm userForm) {
        User user = (User)beanFactory.getBean("findUserById", id);
        UserModel model = user.getModel();

        userForm.setUserName(model.getUserName());

        return "/admin/users/password";
    }

    @RequestMapping(value ="/admin/users/{id}/password", method = RequestMethod.POST)
    public String saveResetPassword(@PathVariable int id, @Valid UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", bindingResult.getFieldError().getDefaultMessage());
            redirectAttributes.addFlashAttribute("userForm", userForm);
            return String.format("redirect:/admin/users/%d/password", id);
        }

        if(!userForm.getPassword().equals(userForm.getPassword2())) {
            redirectAttributes.addFlashAttribute("message", "2次输入的密码不一致!");
            redirectAttributes.addFlashAttribute("userForm", userForm);
            return String.format("redirect:/admin/users/%d/password", id);
        }

        User user = (User)beanFactory.getBean("findUserById", id);
        UserModel model = user.getModel();

        model.setPassword(User.convertPassword(userForm.getPassword()));

        user.save();

        redirectAttributes.addFlashAttribute("message", "新密码已设置!");
        return String.format("redirect:/admin/users/%d/password", id);
    }

}
