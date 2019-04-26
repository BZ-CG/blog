package cn.edu.pzhu.blog.service.article.impl;

import cn.edu.pzhu.base.common.RelationItem;
import cn.edu.pzhu.base.util.DateUtils;
import cn.edu.pzhu.blog.dao.article.ArticleDAO;
import cn.edu.pzhu.blog.dao.article.model.Article;
import cn.edu.pzhu.blog.dao.category.model.Relation;
import cn.edu.pzhu.blog.service.article.ArticleConverter;
import cn.edu.pzhu.blog.service.article.ArticleService;
import cn.edu.pzhu.blog.service.article.dto.ArticleDTO;
import cn.edu.pzhu.blog.service.category.RelationService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ArticleServiceImpl.
 * @author:CG
 * @date:2019/04/24 23:49
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private RelationService relationService;

    @Transactional
    @Override
    public void addCategory(ArticleDTO articleDTO) {
        //1. 新增文章
        Article article = ArticleConverter.toArticle(articleDTO);
        articleDAO.add(article);

        //2. 关系表：文章与分类建立关系
        relationService.batchAddRelation(createCategoryRelationList(articleDTO, article.getId()));
        //3. 标签处理：先判断是否需要插入，然后维护关系表

    }

    /**
     * 根据分类id集合构造Relation
     * @param articleDTO
     * @param aId
     * @return
     */
    private List<Relation> createCategoryRelationList(ArticleDTO articleDTO, Integer aId) {
        List<Relation> relations = Lists.newArrayList();
        for (Integer cId : articleDTO.getCategoryIdList()) {
            Relation relation  = new Relation();
            relation.setAId(aId);
            relation.setUId(articleDTO.getUId());
            relation.setItemId(cId);
            relation.setName(RelationItem.CATEGORY.getName());
            relation.setIden(RelationItem.CATEGORY.getCode());
            relation.setModifyDate(DateUtils.getCurrentDateStr());
            relation.setCreateDate(DateUtils.getCurrentDateStr());

            relations.add(relation);
        }

        return relations;
    }
}
