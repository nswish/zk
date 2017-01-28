package cn.edu.suibe.zk.common.configures;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Constants {
    public static final String Release = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
}
