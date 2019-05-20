package cn.edu.pzhu.blog.dao.link.model;

import lombok.Data;

import java.util.Date;

/**
 * @author:CG
 * @date:2019/05/08 20:49
 */
@Data
public class FriendLink {
    private Integer id;
    private Integer uId;
    private String content;
    private String imageUrl;
    private String linkUrl;
    private String name;
    private Date createDate;
    private Date modifyDate;
}
