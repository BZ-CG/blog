package cn.edu.pzhu.blog.service.article.impl;

import cn.edu.pzhu.blog.dao.article.ArticleDAO;
import cn.edu.pzhu.blog.dao.article.model.Article;
import cn.edu.pzhu.blog.service.article.ArticleConverter;
import cn.edu.pzhu.blog.service.article.ArticleService;
import cn.edu.pzhu.blog.service.article.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ArticleServiceImpl.
 * @author:CG
 * @date:2019/04/24 23:49
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Transactional
    @Override
    public void addCategory(ArticleDTO articleDTO) {
        //1. 新增文章
        Article article = ArticleConverter.toArticle(articleDTO);
        articleDAO.add(article);
        //2. 关系表：文章与分类建立关系
        //3. 标签处理：先判断是否需要插入，然后维护关系表
    }
}
