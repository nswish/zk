package cn.edu.suibe.zk.common.exceptions;

public class DomainException extends BaseException {
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(String messsage) {
        super(messsage);
    }
}
