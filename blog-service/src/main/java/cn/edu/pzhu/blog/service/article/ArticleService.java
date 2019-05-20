package cn.edu.pzhu.blog.service.article;

import cn.edu.pzhu.blog.dao.article.model.Article;
import cn.edu.pzhu.blog.service.article.dto.ArticleDTO;
import cn.edu.pzhu.blog.service.article.dto.ArticleJO;
import cn.edu.pzhu.blog.service.article.dto.EditArticleDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 文章 service.
 * @author:CG
 * @date:2019/04/24 23:42
 */
public interface ArticleService {


    /**
     * 获取文章数量.
     * @param uId
     * @return
     */
    Integer getCount(Integer uId);

    /**
     * 修改文章内容.
     * @param articleDTO
     */
    void updateArticle(ArticleDTO articleDTO);

    /**
     * 获取带标签和分类的文章实体.
     * @param uId
     * @param aId
     * @return
     */
    EditArticleDTO getArticleWithTagAndCategory(Integer uId, Integer aId);

    /**
     * 根据 id 删除文章.
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 获取轮播图的图片地址.
     * @param number 图片的数量.
     * @return
     */
    List<Article> getSlidesImageUrl(Integer number);

    /**
     * 点击量加一.
     * @param uId
     * @param aId
     */
    void addReadNumber(Integer uId, Integer aId);


    /**
     * 通过 id 列表获取文章信息.
     * @param ids
     * @return
     */
    List<Article> getArticleByIds(Integer uId, @RequestParam("ids") List<Integer> ids);

    /**
     * 通过id获取文章信息.
     * @param id
     * @return
     */
    Article getById(Integer id);

    void addCategory(ArticleDTO articleDTO);

    /**
     * 获取list数据.
     * @param uId 用户id
     * @param limitNumber  数据条数
     * @return
     */
    List<Article> getList(Integer uId, Integer limitNumber);

    /**
     * 获取用户的文章.
     * @param uId 用户 id
     * @return
     */
    List<Article> getArticleList(Integer uId);
}
