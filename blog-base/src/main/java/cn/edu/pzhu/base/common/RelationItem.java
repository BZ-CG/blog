package cn.edu.pzhu.base.common;

import lombok.Data;

/**
 * 关系表meijulei.
 * @author:CG
 * @date:2019/04/25 22:40
 */
public enum RelationItem {

    CATEGORY(1, "分类"),
    TAG(2,  "标签");

    private Integer code;
    private String name;

    RelationItem(Integer code,  String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
