package cn.edu.pzhu.base.util;

import cn.edu.pzhu.base.exception.BusinessException;

/**
 * StringUtils  工具类 .
 * @author:CG
 * @date:2019/04/25 22:02
 */
public class StringUtils {

    /**
     * 断言字符串不能为空.
     * @param str 待检查的字符串。
     */
    public static void  isNotEmpty(String str) {
        if (str == null || str.equals("")) {
            throw new BusinessException(str + "不能为空");
        }
    }
}
