package cn.edu.suibe.zk.admin.contracts.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ns on 2017/1/8.
 */
public class JsonContract {
    private HeadContract head;
    private Map<String, Object> body;

    public JsonContract() {
        this.head = new HeadContract();
        this.body = new HashMap<>();
    }

    public void setStatus(boolean status) {
        this.head.setStatus(status);
    }

    public void setMessage(String message) {
        this.head.setMessage(message);
    }

    public void setErrorCode(int errorCode) {
        if(errorCode == 0) {
            this.head.setStatus(true);
        } else {
            this.head.setStatus(false);
        }

        this.head.setErrorCode(errorCode);
    }

    public void put(String key, Object value) {
        this.body.put(key, value);
    }

    public Object get(String key) {
        return this.body.get(key);
    }

    public HeadContract getHead() {
        return head;
    }

    public Map<String, Object> getBody() {
        return body;
    }
}
