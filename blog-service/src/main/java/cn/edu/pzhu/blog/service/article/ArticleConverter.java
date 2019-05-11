package cn.edu.pzhu.blog.service.article;

import cn.edu.pzhu.base.util.DateUtils;
import cn.edu.pzhu.blog.dao.article.model.Article;
import cn.edu.pzhu.blog.service.article.dto.ArticleDTO;
import cn.edu.pzhu.blog.service.article.dto.ArticleJO;
import cn.edu.pzhu.blog.service.article.dto.EditArticleDTO;

import java.util.Date;

/**
 * Article 转换类.
 * @author:CG
 * @date:2019/04/25 0:17
 */
public class ArticleConverter {



    public static EditArticleDTO toEditArticleDTO(Article article) {

        if (article == null) {
            return null;
        }
        EditArticleDTO editArticleDTO = new EditArticleDTO();
        editArticleDTO.setId(article.getId());
        editArticleDTO.setImageUrl(article.getImageUrl());
        editArticleDTO.setUId(article.getUId());
        editArticleDTO.setTitle(article.getTitle());
        editArticleDTO.setContent(article.getContent());
        return editArticleDTO;
    }


    public static ArticleJO toArticleJO(Article article) {

        if (article == null) {
            return null;
        }
        ArticleJO articleJO = new ArticleJO();
        articleJO.setId(article.getId());
        articleJO.setUId(article.getUId());
        articleJO.setTitle(article.getTitle());
        articleJO.setContent(article.getContent());
        articleJO.setReadNumber(article.getReadNumber());
        articleJO.setLikeNumber(article.getLikeNumber());
        articleJO.setImageUrl(article.getImageUrl());
        articleJO.setSysStatus(article.getSysStatus());
        articleJO.setModifyDate(article.getModifyDate());
        articleJO.setCreateDate(article.getCreateDate());
        return articleJO;
    }

    /**
     * 将 ArticleDTO 转化为 Article.
     * @param articleDTO 待转化的 DTO.
     * @return Article 对象.
     */
    public static Article toArticle(ArticleDTO articleDTO) {
        Article article = new Article();

        if (articleDTO != null) {
            article.setId(articleDTO.getId());
            article.setTitle(articleDTO.getTitle());
            article.setContent(articleDTO.getContent());
            article.setUId(articleDTO.getUId());
            article.setCreateDate(new Date());
            article.setModifyDate(new Date());
            article.setReadNumber(0);
            article.setLikeNumber(0);
            article.setSysStatus("1");
            article.setImageUrl("");
        }
        return article;
    }
}
