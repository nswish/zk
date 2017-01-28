package cn.edu.suibe.zk.domain.repositories;

import cn.edu.suibe.zk.domain.models.UserModel;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ns on 2017/1/8.
 */
public interface UserRepository extends CrudRepository<UserModel, Long> {
    UserModel findById(long id);
    UserModel findByUserName(String userName);
}
