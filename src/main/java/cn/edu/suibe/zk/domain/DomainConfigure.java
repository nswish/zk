package cn.edu.suibe.zk.domain;

import cn.edu.suibe.zk.domain.domains.User;
import cn.edu.suibe.zk.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@SuppressWarnings("ALL")
@Configuration
public class DomainConfigure {

    @Autowired
    private UserRepository userRepository;

    @Bean(name = "authenticate")
    @Scope("prototype")
    @Lazy
    User authenticate(String userName, String password) {
        return User.authenticate(this.userRepository, userName, password);
    }

    @Bean(name = "findAllUsers")
    @Scope("prototype")
    @Lazy
    User[] findAllUsers() {
        return User.findAllUsers(this.userRepository);
    }

    @Bean(name = "newUser")
    @Scope("prototype")
    @Lazy
    User newUser() {
        return User.newUser();
    }

    @Bean(name = "findUserById")
    @Scope("prototype")
    @Lazy
    User findUserById(int id) {
        return User.findUserById(this.userRepository, id);
    }
}
