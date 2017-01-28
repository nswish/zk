package cn.edu.suibe.zk.common.exceptions;

public class BaseException extends RuntimeException {
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String messsage) {
        super(messsage);
    }
}
