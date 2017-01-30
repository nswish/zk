package cn.edu.suibe.zk.application.admin.controllers;

import cn.edu.suibe.zk.domain.domains.Category;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {

    @Autowired
    private BeanFactory beanFactory;

    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    public String index() {
        Category[] categories = beanFactory.getBean("findFirstLevelCategories", Category[].class);
        return String.format("redirect:/admin/categories/%d/select", categories[0].getModel().getId());
    }

    @RequestMapping(value = "/admin/categories/{id}/select", method = RequestMethod.GET)
    public String indexCategorySelected(@PathVariable int id, Model uiModel) {
        Category[] firstLevelCategories = beanFactory.getBean("findFirstLevelCategories", Category[].class);
        Category[] secondLevelCategories = (Category[])beanFactory.getBean("findSecondLevelCategories", id);

        uiModel.addAttribute("selectedCategoryId", id);
        uiModel.addAttribute("firstLevelCategories", firstLevelCategories);
        uiModel.addAttribute("secondLevelCategories", secondLevelCategories);

        return "/admin/categories/index";
    }
}
