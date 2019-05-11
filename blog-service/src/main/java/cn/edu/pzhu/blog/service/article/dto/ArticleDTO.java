package cn.edu.pzhu.blog.service.article.dto;

import cn.edu.pzhu.blog.dao.category.model.Category;
import cn.edu.pzhu.blog.dao.category.model.Tag;
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
     * 文章 id.
     */
    private Integer id;

    /**
     * 用户 id.
     */
    private Integer uId;

    /**
     * 文章标题.
     */
    private String title;

    /**
     * 文章标签 id 集合.
     */
    private List<Integer> tagList;

    /**
     * 文章分类 id 集合.
     */
    private List<Integer> categoryIdList;

    /**
     * 文章内容.
     */
    private String content;

    /**
     * 略缩图 base64 编码.
     */
    private String imageStr;
}

