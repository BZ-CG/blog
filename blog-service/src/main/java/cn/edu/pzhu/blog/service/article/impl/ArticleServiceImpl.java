package cn.edu.pzhu.blog.service.article.impl;

import cn.edu.pzhu.base.common.RelationItem;
import cn.edu.pzhu.base.qiniu.QiniuUtils;
import cn.edu.pzhu.base.util.DateUtils;
import cn.edu.pzhu.base.util.ExceptionUtils;
import cn.edu.pzhu.base.util.StringUtils;
import cn.edu.pzhu.base.util.base64.Base64FileUtils;
import cn.edu.pzhu.blog.dao.article.ArticleDAO;
import cn.edu.pzhu.blog.dao.article.model.Article;
import cn.edu.pzhu.blog.dao.category.model.Category;
import cn.edu.pzhu.blog.dao.category.model.Relation;
import cn.edu.pzhu.blog.dao.category.model.Tag;
import cn.edu.pzhu.blog.service.article.ArticleConverter;
import cn.edu.pzhu.blog.service.article.ArticleService;
import cn.edu.pzhu.blog.service.article.dto.ArticleDTO;
import cn.edu.pzhu.blog.service.article.dto.EditArticleDTO;
import cn.edu.pzhu.blog.service.category.CategoryService;
import cn.edu.pzhu.blog.service.category.RelationService;
import cn.edu.pzhu.blog.service.category.TagService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ArticleServiceImpl.
 * @author:CG
 * @date:2019/04/24 23:49
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {


    private static final String[] DEFAULT_ARTICLE_IMG = {
            "1.jpg","2.jpg","3.jpg","4.jpg","5.jpg",
            "6.jpg","7.jpg","8.jpg","9.jpg","10.jpg"
    };

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private RelationService relationService;

    @Autowired
    private QiniuUtils qiniuUtils;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;



    @Override
    public Integer getCount(Integer uId) {
        try {
            return articleDAO.getCount(uId);
        } catch (Exception e) {
            log.error("调用 articleDAO.getCount 获取文章数量.", e);
            throw ExceptionUtils.buildBusinessException();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticle(ArticleDTO articleDTO) {
        Article article = ArticleConverter.toArticle(articleDTO);
        article.setCreateDate(null);

        if (StringUtils.isNotEmpty(articleDTO.getImageStr())) {
            article.setImageUrl(uploadImgToQiNiu(articleDTO));
        } else {
            article.setImageUrl(null);
        }

        articleDAO.updateById(article);
        List<Relation> relationByAid = relationService.getRelationByAid(article.getUId(), article.getId());
        List<Integer> itemIds = relationByAid.stream().map(Relation::getItemId).collect(Collectors.toList());
        List<Relation> relations = getRelationList(articleDTO, itemIds);

        if (!CollectionUtils.isEmpty(relations)) {
            relationService.batchAddRelation(relations);
        }
    }

    private List<Relation> getRelationList(ArticleDTO articleDTO, List<Integer> itemIds) {
        List<Relation> relations = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(articleDTO.getCategoryIdList())) {
            for (Integer cId : articleDTO.getCategoryIdList()) {
                if (!itemIds.contains(cId)) {
                    Relation relation = getRelation(articleDTO, articleDTO.getId(), cId, RelationItem.CATEGORY);
                    relations.add(relation);
                }
            }
        }
        if (!CollectionUtils.isEmpty(articleDTO.getTagList())) {
            for (Integer tId : articleDTO.getTagList()) {
                if (!itemIds.contains(tId)) {
                    Relation relation = getRelation(articleDTO, articleDTO.getId(), tId, RelationItem.TAG);
                    relations.add(relation);
                }
            }
        }
        return relations;
    }

    @Override
    public EditArticleDTO getArticleWithTagAndCategory(Integer uId, Integer aId) {
        EditArticleDTO editArticleDTO = null;
        try {
            editArticleDTO = ArticleConverter.toEditArticleDTO(articleDAO.getById(aId));
            List<Relation> relations = relationService.getRelationByAid(uId, editArticleDTO.getId());

            List<Tag> tags = Lists.newArrayList();
            List<Category> categories = Lists.newArrayList();

            for (Relation relation : relations) {
                RelationItem type = RelationItem.getRelationItemByCode(relation.getIden());
                if (type.equals(RelationItem.CATEGORY)) {
                    Category category = categoryService.getCategoryById(relation.getItemId());
                    categories.add(category);
                } else if (type.equals(RelationItem.TAG)){
                    Tag tag = tagService.getById(relation.getItemId());
                    tags.add(tag);
                }
            }

            editArticleDTO.setCategories(categories);
            editArticleDTO.setTags(tags);
        } catch (Exception e) {
            log.error("调用 ArticleService.getArticleWithTagAndCategory 获取修改文章实体异常.", e);
            throw ExceptionUtils.buildBusinessException();
        }
        return editArticleDTO;
    }

    @Override
    public int deleteById(Integer id) {
        int result = 0;
        List<Article> list;
        try {
            result = articleDAO.deleteById(id);
        } catch (Exception e) {
            log.error("调用 articleDAO.deleteById 根据 id 删除文章.", e);
            throw ExceptionUtils.buildBusinessException();
        }
        return result;
    }

    @Override
    public List<Article> getSlidesImageUrl(Integer number) {
        List<Article> list;
        try {
            list = articleDAO.getSlidesImageUrl(number);
        } catch (Exception e) {
            log.error("调用 articleDAO.getSlidesImageUrl 获取轮播图 url 异常", e);
            throw ExceptionUtils.buildBusinessException();
        }
        return list;
    }

    @Override
    public void addReadNumber(Integer uId, Integer aId) {
        try {
             articleDAO.addReadNumber(uId, aId);
        } catch (Exception e) {
            log.error("调用 articleDAO.addReadNumber 增加点击量异常", e);
            throw ExceptionUtils.buildBusinessException();
        }
    }

    @Override
    public List<Article> getArticleByIds(Integer uId, List<Integer> ids) {
        List<Article> articleList;
        try {
            if (CollectionUtils.isEmpty(ids)) {
                articleList = Lists.newArrayList();
            } else {
                articleList = articleDAO.getArticleByIds(uId, ids);
            }
        } catch (Exception e) {
            log.error("调用 articleDAO.getArticleByIds 获取文章信息异常", e);
            throw ExceptionUtils.buildBusinessException();
        }
        return articleList;
    }

    @Override
    public Article getById(Integer id) {
        Article article;
        try {
            article = articleDAO.getById(id);
        } catch (Exception e) {
            log.error("调用 articleDAO.getById 获取文章信息异常", e);
            throw ExceptionUtils.buildBusinessException();
        }

        return article;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCategory(ArticleDTO articleDTO) {
        //1. 新增文章
        Article article = ArticleConverter.toArticle(articleDTO);
        String url = getArticleImgUrl(articleDTO);
        article.setImageUrl(url);

        articleDAO.add(article);

        //2. 关系表：文章与标签和分类建立关系
        relationService.batchAddRelation(createCategoryRelationList(articleDTO, article.getId(), RelationItem.CATEGORY));
    }

    /**
     * 当发表博客，未选择略缩图时，随机选择一张图片作为略缩图。
     * @param articleDTO
     * @return
     */
    public String getArticleImgUrl(ArticleDTO articleDTO) {
        if (StringUtils.isNotEmpty(articleDTO.getImageStr())) {
            return uploadImgToQiNiu(articleDTO);
        } else {
            String prefix = "/img/articleDefaultImg/";
            int random = (int) ((Math.random()) * (DEFAULT_ARTICLE_IMG.length - 1));
            return prefix + DEFAULT_ARTICLE_IMG[random];
        }
    }


    private String uploadImgToQiNiu(ArticleDTO articleDTO) {
        String fileName = articleDTO.getTitle() + System.currentTimeMillis();

        return qiniuUtils.uploadBase64(articleDTO.getImageStr(), fileName);
    }

    @Override
    public List<Article> getList(Integer uId, Integer limitNumber) {
        List<Article> list;
        try {
            list = articleDAO.getList(uId, limitNumber);
        } catch (Exception e) {
            log.error("调用 articleDAO.getList 获取文章信息异常", e);
            throw ExceptionUtils.buildBusinessException();
        }

        return list;
    }

    @Override
    public List<Article> getArticleList(Integer uId) {
        List<Article> list;
        try {
            list = articleDAO.getArticleList(uId);
        } catch (Exception e) {
            log.error("调用 articleDAO.getArticleList 获取文章信息异常", e);
            throw ExceptionUtils.buildBusinessException();
        }

        return list;
    }


    //private ArticleJO getArticleDTOFromArticle(Integer uId, Article article) {
    //    ArticleJO articleJO = ArticleConverter.toArticleJO(article);
    //    List<Relation> relations = relationService.getRelationByAid(uId, article.getId());
    //    for (Relation relation : relations) {
    //        if (RelationItem.getRelationItemByCode(relation.getIden()).equals(RelationItem.CATEGORY)) {
    //            Category category = categoryService.getCategoryById(relation.getItemId());
    //            articleJO.setCategoryName(category.getName());
    //            break;
    //        }
    //    }
    //
    //    for (Relation relation : relations) {
    //        if (RelationItem.getRelationItemByCode(relation.getIden()).equals(RelationItem.TAG)) {
    //            Tag tag = tagService.getById(relation.getItemId());
    //            articleJO.setTagName(tag.getName());
    //            break;
    //        }
    //    }
    //    return articleJO;
    //}


    private Category getCategory(Integer uId, Integer aId) {


        return null;
    }

    /**
     * 根据分类和标签构造Relation
     * @param articleDTO
     * @param aId
     * @param type
     * @return
     */
    private List<Relation> createCategoryRelationList(ArticleDTO articleDTO, Integer aId, RelationItem type) {
        List<Relation> relations = Lists.newArrayList();
        for (Integer cId : articleDTO.getCategoryIdList()) {
            Relation relation  = getRelation(articleDTO, aId, cId, RelationItem.CATEGORY);
            relations.add(relation);
        }

        for (Integer tId : articleDTO.getTagList()) {
            Relation relation  = getRelation(articleDTO, aId, tId, RelationItem.TAG);
            relations.add(relation);
        }
        return relations;
    }

    private Relation getRelation(ArticleDTO articleDTO, Integer aId, Integer itemId, RelationItem type) {
        Relation relation  = new Relation();
        relation.setAId(aId);
        relation.setUId(articleDTO.getUId());
        relation.setItemId(itemId);
        relation.setModifyDate(DateUtils.getCurrentDateStr());
        relation.setCreateDate(DateUtils.getCurrentDateStr());
        relation.setName(type.getName());
        relation.setIden(type.getCode());

        return relation;
    }
}
