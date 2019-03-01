package cn.edu.pzhu.base.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author CG
 */
@Data
public class Head implements Serializable {
    private static final long serialVersionUID = 7654890997607787551L;

    private String code;
    private String message;
    private String status;

    public Head(String code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
