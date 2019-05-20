package cn.edu.pzhu.blog.service.article.dto;

import cn.edu.pzhu.blog.dao.article.model.Article;
import lombok.Data;

/**
 * @author:CG
 * @date:2019/05/05 10:21
 */
@Data
public class ArticleJO extends Article {
    private String tagName;

    private String categoryName;

    private Integer categoryId;

    private Integer pages;

}
