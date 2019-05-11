package cn.edu.pzhu.base.common;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

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
    private static Map<Integer, RelationItem> map = new HashMap();

    static {
        for (RelationItem item : values()) {
            map.put(item.getCode(), item);
        }
    }

    public static RelationItem getRelationItemByCode(Integer code) {
        return map.get(code);
    }

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
