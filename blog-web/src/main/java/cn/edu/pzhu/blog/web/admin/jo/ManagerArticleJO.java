package cn.edu.pzhu.blog.web.admin.jo;

import cn.edu.pzhu.blog.service.article.dto.ArticleJO;
import lombok.Data;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/10 22:27
 */
@Data
public class ManagerArticleJO extends ArticleJO {
    List<ArticleJO> articleList;
    private Integer pageNum;
    private Long total;
    private int[] navigatepageNums;
    private Integer pages;
}
