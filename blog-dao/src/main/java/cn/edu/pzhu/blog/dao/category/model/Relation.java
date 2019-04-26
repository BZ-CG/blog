package cn.edu.pzhu.blog.dao.category.model;

import lombok.Data;

/**
 * 关系表 model.
 * @author:CG
 * @date:2019/04/25 22:51
 */
@Data
public class Relation {
    private Integer id;
    private Integer uId;
    private Integer aId;
    private Integer itemId;
    private String name;
    private Integer iden;
    private String modifyDate;
    private String createDate;
}
