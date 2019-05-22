package cn.edu.pzhu.blog.web.article.jo;

import cn.edu.pzhu.blog.service.article.dto.ArticleJO;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/06 17:03
 */
@Data
public class ArticleVO {

    private List<ArticleJO> articleJOList;
    private PageInfo pageInfo;
}
