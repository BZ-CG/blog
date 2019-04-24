package cn.edu.pzhu.blog.dao.category.model;

import lombok.Data;

/**
 * 标签 model.
 * @author:CG
 * @date:2019/04/21 20:57
 */
@Data
public class Tag {
    private Integer id;
    private Integer uId;
    private String name;
    private String style;
    private String modifyDate;
    private String createDate;
}
