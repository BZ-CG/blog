package cn.edu.pzhu.blog.dao.message.model;

import lombok.Data;

import java.util.Date;

/**
 * @author:CG
 * @date:2019/05/06 21:17
 */
@Data
public class Message {
    private Integer id;
    private Integer uId;
    private String content;
    private Date writeTime;
    private String position;
    private String qqNumber;
    private String qqImage;
    private String qqName;
    private String modifyDate;
    private String createDate;
}
