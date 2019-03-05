package cn.edu.pzhu.base.response;


import cn.edu.pzhu.base.common.CommonCodeConstants;
import cn.edu.pzhu.base.common.ErrorCodeConstants;
import lombok.Data;
import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = -7823193327589571252L;
    private static final String SUCCESS = "Y";
    private static final String FAIL = "N";
    private Head head;
    private T body;

    public ApiResponse<T> head(Head head) {
        setHead(head);
        return this;
    }

    public ApiResponse<T> body(T body) {
        setBody(body);
        return this;
    }

    public static <T> ApiResponse<T> success(T body) {

        return new ApiResponse<T>().head(new Head(CommonCodeConstants.SUCCESS_CODE,
                CommonCodeConstants.SUCCESS_MSG, SUCCESS)).body(body);
    }

    public static ApiResponse success() {
        return success(null);
    }

    public static <T> ApiResponse<T> error(String msg) {
        return new ApiResponse<T>().head(new Head(CommonCodeConstants.FAIL_CODE,
                msg, FAIL)).body(null);
    }
}
