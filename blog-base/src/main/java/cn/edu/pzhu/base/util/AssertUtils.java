package cn.edu.pzhu.base.util;

import cn.edu.pzhu.base.exception.BusinessException;

import java.io.Serializable;

/**
 * 断言工具类.
 * @author:CG
 * @date:2019/04/20 9:07
 */
public class AssertUtils implements Serializable {
    private static final long serialVersionUID = 4577403460155967255L;

    private static final String ERROR_CODE = "1111";
    private static final String ERROR_PARAM_CAN_NOT_BE_EMPTY = "%s不能为空";

    /**
     * 不能为 null 的判断.
     * @param obj 带判断的对象
     * @param paramName 被检查对象的名称
     */
    public static void isNotNull(Object obj, String paramName) {
        if (obj == null) {
            throw bulidBusinessException(String.format(ERROR_PARAM_CAN_NOT_BE_EMPTY, paramName));
        }
    }
    private static BusinessException bulidBusinessException(String message) {
        return new BusinessException(ERROR_CODE, message);
    }
}
