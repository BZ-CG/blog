package cn.edu.pzhu.base.exception;

import lombok.Data;

/**
 * @author:CG
 * @date:2019/04/20 8:16
 */
@Data
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1127814530839964568L;

    private String errorMsg;
    private String code;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
