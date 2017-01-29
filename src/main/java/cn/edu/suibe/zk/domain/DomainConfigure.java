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

    @Bean
    @Scope("prototype")
    @Lazy
    User getUser(String userName, String password) {
        return User.authenticate(this.userRepository, userName, password);
    }

    @Bean
    @Scope("prototype")
    @Lazy
    User[] getUsers() {
        return User.findAllUsers(this.userRepository);
    }
}
