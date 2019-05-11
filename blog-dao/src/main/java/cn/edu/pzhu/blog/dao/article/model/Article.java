package cn.edu.pzhu.blog.dao.article.model;

import lombok.Data;

import java.util.Date;

/**
 * 文章 model.
 * @author:CG
 * @date:2019/04/24 23:59
 */
@Data
public class Article {
    private Integer id;
    private Integer uId;
    private String title;
    private String content;
    private Integer readNumber;
    private Integer likeNumber;
    private String imageUrl;
    private String sysStatus;
    private Date modifyDate;
    private Date createDate;
}
