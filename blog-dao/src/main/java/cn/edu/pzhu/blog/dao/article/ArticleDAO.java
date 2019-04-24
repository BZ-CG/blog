package cn.edu.pzhu.blog.dao.article;

import cn.edu.pzhu.blog.dao.article.model.Article;
import org.springframework.stereotype.Component;

/**
 * 文章 DAO.
 * @author:CG
 * @date:2019/04/24 23:59
 */
@Component
public interface ArticleDAO {

    /**
     * 新增博客.
     * @param article 博客对象.
     * @return id .
     */
    Integer add(Article article);
}
