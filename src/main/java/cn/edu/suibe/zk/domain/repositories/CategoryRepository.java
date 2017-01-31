package cn.edu.suibe.zk.domain.repositories;

import cn.edu.suibe.zk.domain.models.CategoryModel;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryModel, Integer> {
    Iterable<CategoryModel> findByParentId(int parentId);
    int countByParentId(int parentId);
}
