package cn.edu.suibe.zk.admin.domain.repositories;

import cn.edu.suibe.zk.admin.domain.models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by ns on 2017/1/8.
 */
public interface UserRepository extends CrudRepository<UserModel, Long> {
    UserModel findById(long id);
    UserModel findByUserName(String userName);
}
