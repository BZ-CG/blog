package cn.edu.pzhu.blog.dao.article;

import cn.edu.pzhu.blog.dao.article.model.Article;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 文章 DAO.
 * @author:CG
 * @date:2019/04/24 23:59
 */
@Component
public interface ArticleDAO {

    /**
     * 获取文章数量.
     * @param uId
     * @return
     */
    Integer getCount(Integer uId);

    /**
     * 修改文章内容.
     * @param article
     */
    void updateById(Article article);

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
     * @param uId
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

    /**
     * 新增博客.
     * @param article 博客对象.
     * @return id .
     */
    Integer add(Article article);

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
