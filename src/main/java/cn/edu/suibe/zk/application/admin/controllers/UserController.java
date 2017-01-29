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
    public String saveCreate(UserForm userForm, Model uiModel) {
        User newUser = beanFactory.getBean("newUser", User.class);

        UserModel model = newUser.getModel();
        model.setUserName(userForm.getUserName());
        model.setPassword(userForm.getPassword());

        newUser.save();

        return "redirect:/admin/users/create";
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
}
