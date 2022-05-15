package barogo.delivery.rs.module.common;


public class BizException extends RuntimeException {
    private int code;
    private Object[] data;

    public BizException(String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }

    public Object[] getData() {
        return data;
    }
}
