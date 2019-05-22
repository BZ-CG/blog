package cn.edu.pzhu.blog.web.article;

import lombok.Data;

/**
 * @author:CG
 * @date:2019/05/02 7:25
 */
@Data
public class HotListJO {
    private Integer id;
    private Integer index;
    private String title;
    private Integer readNumber;
    private String classStyle;
}
