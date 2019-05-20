package cn.edu.pzhu.blog.dao.image.model;

import lombok.Data;

import java.util.Date;

/**
 * 图片信息 model.
 * @author:CG
 * @date:2019/05/15 21:42
 */
@Data
public class ImageInfo {
    private Integer id;
    private Integer uId;
    private String url;
    private Integer code;
    private String title;
    private Date createDate;
    private Date modifyDate;

}

