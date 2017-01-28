package cn.edu.suibe.zk.application;

import cn.edu.suibe.zk.common.configures.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigure {

    @Bean(name = "Constants")
    public Constants getConstants() {
        return new Constants();
    }
}
