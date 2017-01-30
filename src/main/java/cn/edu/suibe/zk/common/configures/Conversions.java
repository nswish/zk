package cn.edu.suibe.zk.common.configures;

import org.springframework.stereotype.Component;

@Component
public class Conversions {
    public String userStateDesc(int userState) {
        switch (userState) {
            case Constants.USER_STATE.UNAUDIT:
                return "未审核";
            case Constants.USER_STATE.AUDITED:
                return "审核通过";
            default:
                return "";
        }
    }
}
