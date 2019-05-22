package cn.edu.pzhu.blog.web.admin.jo;

import lombok.Data;
import java.util.List;

/**
 * 文章 JO.
 * @author:CG
 * @date:2019/04/22 8:43
 */
@Data
public class ArticleJO {

    /**
     * 文章 id.
     */
    private Integer id;

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
    private List<String> tagIds;

    /**
     * 文章分类 id 集合.
     */
    private List<String>  categoryIds;

    /**
     * 文章内容.
     */
    private String content;

    /**
     * 略缩图 base64 编码.
     */
    private String imageStr;

}
