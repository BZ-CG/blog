package cn.edu.pzhu.base.util;


/**
 * StringUtils  工具类 .
 * @author:CG
 * @date:2019/04/25 22:02
 */
public class StringUtils {

    /**
     * 判断字符串是否为空.
     * @param str 待检查的字符串。
     */
    public static boolean  isNotEmpty(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return true;
    }
}
