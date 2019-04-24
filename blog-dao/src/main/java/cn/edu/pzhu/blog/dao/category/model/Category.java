package cn.edu.pzhu.blog.dao.category.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 分类 model.
 * @author:CG
 * @date:2019/04/21 0:17
 */
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = -6180851019333989349L;

    private Integer id;
    private Integer userId;
    private String name;
    private String style;
    private String modifyDate;
    private String createDate;
}
