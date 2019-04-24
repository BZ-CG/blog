package cn.edu.pzhu.blog.service.article;

import cn.edu.pzhu.base.util.DateUtils;
import cn.edu.pzhu.blog.dao.article.model.Article;
import cn.edu.pzhu.blog.service.article.dto.ArticleDTO;

/**
 * Article 转换类.
 * @author:CG
 * @date:2019/04/25 0:17
 */
public class ArticleConverter {



    /**
     * 将 ArticleDTO 转化为 Article.
     * @param articleDTO 待转化的 DTO.
     * @return Article 对象.
     */
    public static Article toArticle(ArticleDTO articleDTO) {
        Article article = new Article();

        if (articleDTO != null) {
            article.setTitle(articleDTO.getTitle());
            article.setContent(articleDTO.getContent());
            article.setUId(articleDTO.getUId());
            article.setCreateDate(DateUtils.getCurrentDateStr());
            article.setModifyDate(DateUtils.getCurrentDateStr());
            article.setReadNumber(0);
            article.setLikeNumber(0);
            article.setSysStatus("1");
            article.setImageUrl("");
        }
        return article;
    }
}
