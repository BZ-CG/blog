package cn.edu.pzhu.base.util;

import cn.edu.pzhu.base.common.ErrorCodeConstants;
import cn.edu.pzhu.base.exception.BusinessException;

/**
 * 异常工具类.
 * @author:CG
 * @date:2019/04/21 21:20
 */
public class ExceptionUtils {

    /**
     * 构建一个创建接口异常的业务异常实体.
     * @return
     */
    public static BusinessException buildBusinessException() {
        return new BusinessException(ErrorCodeConstants.ERROR_CODE, ErrorCodeConstants.ERROR_MSG);
    }
}
