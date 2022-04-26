package tk.endcy.exception;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 14:56
 */
public class NotFountBeanException extends Exception {
    private static final long serialVersionUID = 5031334811006058473L;

    public NotFountBeanException() {
        super("Bean not found");
    }

    public NotFountBeanException(String message) {
        super(message);
    }

    public NotFountBeanException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFountBeanException(Throwable cause) {
        super(cause);
    }

    protected NotFountBeanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
