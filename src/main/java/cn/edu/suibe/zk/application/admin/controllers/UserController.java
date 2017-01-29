package cn.edu.suibe.zk.application.admin.controllers;

import cn.edu.suibe.zk.application.admin.controllers.forms.UserForm;
import cn.edu.suibe.zk.domain.domains.User;
import cn.edu.suibe.zk.domain.models.UserModel;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "/admin/users/create";
    }

    @RequestMapping(value = "/admin/users/create", method = RequestMethod.POST)
    public String saveCreate(UserForm userForm, RedirectAttributes redirectAttributes) {
        User newUser = beanFactory.getBean("newUser", User.class);

        UserModel model = newUser.getModel();
        model.setUserName(userForm.getUserName());
        model.setPassword(userForm.getPassword());

        try {
            newUser.save();

            redirectAttributes.addFlashAttribute("redirectMessage", String.format("用户[%s]已保存!", userForm.getUserName()));
            return "redirect:/admin/users";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("redirectMessage", ex.getMessage());
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

        return "/admin/users/edit";
    }

    @RequestMapping(value ="/admin/users/{id}/edit", method = RequestMethod.POST)
    public String saveEdit(@PathVariable int id, UserForm userForm) {
        User user = (User)beanFactory.getBean("findUserById", id);
        UserModel model = user.getModel();

        model.setTrueName(userForm.getTrueName());
        model.setEmail(userForm.getEmail());
        model.setTelephone(userForm.getTelephone());

        user.save();

        return String.format("redirect:/admin/users/%d/edit", id);
    }

    @RequestMapping(value = "/admin/users/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable int id, RedirectAttributes redirectAttributes) {
        User user = (User)beanFactory.getBean("findUserById", id);

        user.delete();

        redirectAttributes.addFlashAttribute("redirectMessage", String.format("用户[%s]已删除!", user.getModel().getUserName()));

        return "redirect:/admin/users";
    }
}
