package cn.edu.pzhu.base.response;


import cn.edu.pzhu.base.common.CommonCodeConstants;
import cn.edu.pzhu.base.common.ErrorCodeConstants;
import lombok.Data;
import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = -7823193327589571252L;
    private Head head;
    private T body;

    public ApiResponse(Head head, T body) {
        this.head = head;
        this.body = body;
    }

    public ApiResponse(Head head) {
        this.head = head;
    }

    public static ApiResponse success(Object body) {
        return new ApiResponse(new Head(CommonCodeConstants.SUCCESS_CODE, "", CommonCodeConstants.SUCCESS_MSG), body);
    }

    public static ApiResponse success() {
        return new ApiResponse(new Head(CommonCodeConstants.SUCCESS_CODE, "", CommonCodeConstants.SUCCESS_MSG));
    }

    public static ApiResponse error(String msg) {
        return new ApiResponse(new Head(ErrorCodeConstants.ERROR_CODE, msg, CommonCodeConstants.FAIL_MSG));
    }
}
