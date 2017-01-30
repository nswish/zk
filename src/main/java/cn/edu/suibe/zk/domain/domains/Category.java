package cn.edu.suibe.zk.domain.domains;

import cn.edu.suibe.zk.domain.models.CategoryModel;
import cn.edu.suibe.zk.domain.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.StreamSupport;

public class Category {

    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryModel model;

    /**
     * 获取所有一级分类的信息(已按orderId排序)
     *
     * @param categoryRepository
     * @return
     */
    public static Category[] findFirstLevelCategories(CategoryRepository categoryRepository) {
        return StreamSupport.stream(categoryRepository.findByParentId(0).spliterator(), false)
                .sorted((model1, model2)->model1.getOrderId() - model2.getOrderId())
                .map(Category::new)
                .toArray(Category[]::new);
    }

    /**
     * 获取一级分类下所有二级分类的信息(已按orderId排序)
     *
     * @param categoryRepository
     * @param firstLevelCategoryId
     * @return
     */
    public static Category[] findSecondLevelCategories(CategoryRepository categoryRepository, int firstLevelCategoryId) {
        return StreamSupport.stream(categoryRepository.findByParentId(firstLevelCategoryId).spliterator(), false)
                .sorted((model1, model2)->model1.getOrderId() - model2.getOrderId())
                .map(Category::new)
                .toArray(Category[]::new);
    }

    public Category(CategoryModel model) {
        this.model = model;
    }

    public CategoryModel getModel() {
        return model;
    }
}
