package cn.edu.pzhu.blog.service.article.dto;

import cn.edu.pzhu.blog.dao.category.model.Category;
import cn.edu.pzhu.blog.dao.category.model.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/11 8:00
 */
@Data
public class EditArticleDTO extends ArticleDTO {
    private Integer id;
    private List<Tag> tags;
    private List<Category> categories;
    private String imageUrl;
}
