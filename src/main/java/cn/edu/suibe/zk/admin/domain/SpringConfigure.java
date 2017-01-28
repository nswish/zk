package cn.edu.suibe.zk.admin.domain;

import cn.edu.suibe.zk.admin.domain.domains.User;
import cn.edu.suibe.zk.admin.domain.models.UserModel;
import cn.edu.suibe.zk.admin.domain.repositories.UserRepository;
import cn.edu.suibe.zk.common.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

@SuppressWarnings("ALL")
@Configuration
public class SpringConfigure {

    @Autowired
    private UserRepository userRepository;

    @Bean
    @Scope("prototype")
    @Lazy
    User getUser(String userName, String password) {
        try {
            String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes("UTF-8"));
            UserModel userModel = userRepository.findByUserName(userName);
            if(passwordMD5.equalsIgnoreCase(userModel.getPassword())) {
                return new User(userModel);
            } else {
                return User.LOGIN_ERROR_USER;
            }
        } catch (UnsupportedEncodingException e) {
            throw new DomainException("密码的Encoding不是UTF8", e);
        }
    }
}
