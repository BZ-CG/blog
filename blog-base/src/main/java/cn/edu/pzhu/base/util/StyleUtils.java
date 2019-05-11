package cn.edu.pzhu.base.util;

import java.util.Random;

/**
 * @author:CG
 * @date:2019/05/10 7:58
 */
public class StyleUtils {
    private static final String[] BTN_CLASS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    /**
     * 生成一个随机的 style.
     * @return
     */
    public static String getRandStyleClass() {
        Random random = new Random();
        int r = random.nextInt(BTN_CLASS.length);
        return BTN_CLASS[r];
    }
}
