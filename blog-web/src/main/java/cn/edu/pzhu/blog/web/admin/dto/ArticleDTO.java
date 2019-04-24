package cn.edu.pzhu.blog.web.admin.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 文章 DTO.
 * @author:CG
 * @date:2019/04/22 8:43
 */
@Data
public class ArticleDTO implements Serializable {
    private static final long serialVersionUID = -2531689347603190655L;

    /**
     * 文章标题.
     */
    private String title;

    /**
     * 文章标签.
     */
    private String tags;

    /**
     * 文章分类 id 集合.
     */
    private List<Integer>  categoryIds;

    /**
     * 文章内容.
     */
    private String content;

}
