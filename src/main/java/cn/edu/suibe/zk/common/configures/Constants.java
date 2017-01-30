package cn.edu.suibe.zk.common.configures;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Constants {
    public static final String Release = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    public static final USER_STATE UserState = new USER_STATE();

    public static final class USER_STATE {
        public static final int UNAUDIT = 0;
        public static final int AUDITED = 1;
    }

    public static final USER_ROLE UserRole = new USER_ROLE();

    public static final class USER_ROLE {
        public static final int MEMBER = 0;
        public static final int ADMIN = 1;
    }
}
