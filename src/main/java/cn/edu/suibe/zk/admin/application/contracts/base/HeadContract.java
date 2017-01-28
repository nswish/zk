package cn.edu.suibe.zk.admin.application.contracts.base;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by ns on 2017/1/8.
 */
public class HeadContract {
    private boolean status;
    private String message;
    private int errorCode;

    private Date time;

    public HeadContract() {
        this.status = true;
        this.message = "";
        this.errorCode = 0;
        this.time = new Date();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getTime() {
        return time;
    }
}
