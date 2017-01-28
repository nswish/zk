package cn.edu.suibe.zk.common.exceptions;

public class DatabaseException extends BaseException {
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(String messsage) {
        super(messsage);
    }
}
