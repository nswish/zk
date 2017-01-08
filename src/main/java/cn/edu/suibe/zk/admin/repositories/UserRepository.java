package cn.edu.suibe.zk.admin.repositories;

import cn.edu.suibe.zk.admin.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ns on 2017/1/8.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findById(long id);
}
