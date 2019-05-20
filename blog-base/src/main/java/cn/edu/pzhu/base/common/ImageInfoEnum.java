package cn.edu.pzhu.base.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 图片枚举类.
 * @author:CG
 * @date:2019/05/15 21:29
 */
public enum ImageInfoEnum {

    ALI_PLAY(1, "支付宝扫一扫"),
    WECHAT_PLAY(2, "微信扫一扫"),
    WECHAT(3, "微信"),
    BG_IMAGE(4, "背景图片"),
    HEAD_IMAGE(5, "头像")
    ;

    private Integer code;
    private String title;
    private static Map<Integer, ImageInfoEnum> map = Maps.newHashMap();


    static {

        for (ImageInfoEnum item : values()) {
            map.put(item.getCode(), item);
        }
    }


    public static ImageInfoEnum getByCode(Integer code) {
        return map.get(code);
    }

    ImageInfoEnum(Integer code, String title) {
        this.code = code;
        this.title = title;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }}
