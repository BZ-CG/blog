package cn.edu.pzhu.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 与 Date 相关操作的工具类.
 * @author:CG
 * @date:2019/04/25 0:22
 */
public class DateUtils {
    /**
     * 获取当前时间字符串.
     * @return 时间字符串
     */
    public static String getCurrentDateStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }
}
