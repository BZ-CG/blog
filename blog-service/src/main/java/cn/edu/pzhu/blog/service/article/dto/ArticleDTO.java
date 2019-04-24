package cn.edu.pzhu.blog.service.article.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ArticleDTO.
 * @author:CG
 * @date:2019/04/24 23:46
 */
@Data
public class ArticleDTO implements Serializable {

    private static final long serialVersionUID = -7503582362186943105L;

    /**
     * 用户 id.
     */
    private Integer uId;

    /**
     * 文章标题.
     */
    private String title;

    /**
     * 文章标签.
     */
    private List<String> tagList;

    /**
     * 文章分类 id 集合.
     */
    private List<Integer> categoryIdList;

    /**
     * 文章内容.
     */
    private String content;
}

