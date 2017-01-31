package cn.edu.suibe.zk.application.admin.controllers;

import cn.edu.suibe.zk.application.admin.controllers.forms.CategoryForm;
import cn.edu.suibe.zk.domain.domains.Category;
import cn.edu.suibe.zk.domain.models.CategoryModel;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {

    @Autowired
    private BeanFactory beanFactory;

    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    public String index() {
        Category[] categories = beanFactory.getBean("findFirstLevelCategories", Category[].class);
        return String.format("redirect:/admin/categories/%d/select", categories[0].getModel().getId());
    }

    @RequestMapping(value = "/admin/categories/{parentId}/select", method = RequestMethod.GET)
    public String indexCategorySelected(@PathVariable int parentId, Model uiModel) {
        Category[] firstLevelCategories = beanFactory.getBean("findFirstLevelCategories", Category[].class);
        Category[] secondLevelCategories = (Category[])beanFactory.getBean("findSecondLevelCategories", parentId);

        uiModel.addAttribute("selectedCategoryId", parentId);
        uiModel.addAttribute("firstLevelCategories", firstLevelCategories);
        uiModel.addAttribute("secondLevelCategories", secondLevelCategories);

        return "/admin/categories/index";
    }

    @RequestMapping(value = "/admin/categories/{parentId}/create", method = RequestMethod.GET)
    public String showCreate(@PathVariable int parentId, Model uiModel, CategoryForm categoryForm) {
        uiModel.addAttribute("parentId", parentId);
        return "/admin/categories/create";
    }

    @RequestMapping(value = "/admin/categories/{parentId}/create", method = RequestMethod.POST)
    public String saveCreate(@PathVariable int parentId, CategoryForm categoryForm, RedirectAttributes redirectAttributes) {
        try {
            Category newCategory = beanFactory.getBean("newCategory", Category.class);
            CategoryModel model = newCategory.getModel();

            model.setTitle(categoryForm.getTitle());
            model.setParentId(parentId);

            newCategory.save();

            redirectAttributes.addFlashAttribute("message", String.format("二级分类[%s]已保存!", categoryForm.getTitle()));
            return String.format("redirect:/admin/categories/%d/select", parentId);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("categoryForm", categoryForm);
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return String.format("redirect:/admin/categories/%d/create", parentId);
        }
    }
}
