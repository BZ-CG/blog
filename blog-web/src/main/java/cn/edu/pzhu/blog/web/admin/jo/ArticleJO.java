package cn.edu.pzhu.blog.web.admin.jo;

import lombok.Data;
import java.util.List;

/**
 * 文章 DTO.
 * @author:CG
 * @date:2019/04/22 8:43
 */
@Data
public class ArticleJO {

    /**
     * 用户 id.
     */
    private Integer uId;

    /**
     * 文章标题.
     */
    private String title;

    /**
     * 文章标签.
     */
    private String tags;

    /**
     * 文章分类 id 集合.
     */
    private List<String>  categoryIds;

    /**
     * 文章内容.
     */
    private String content;

}
