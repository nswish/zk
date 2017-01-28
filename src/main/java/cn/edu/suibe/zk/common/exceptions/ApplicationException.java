package cn.edu.suibe.zk.common.exceptions;

public class ApplicationException extends BaseException {
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(String messsage) {
        super(messsage);
    }
}
