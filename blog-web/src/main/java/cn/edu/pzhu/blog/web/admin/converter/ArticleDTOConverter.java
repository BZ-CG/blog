package cn.edu.pzhu.blog.web.admin.converter;

import cn.edu.pzhu.blog.service.article.dto.ArticleDTO;
import cn.edu.pzhu.blog.web.admin.jo.ArticleJO;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ArticleDTO 转换.
 * @author:CG
 * @date:2019/04/25 21:53
 */
public class ArticleDTOConverter {

    /**
     * 将 ArticleJO 转化为ArticleDTO.
     * @param articleJO
     * @return
     */
    public static ArticleDTO toArticleDTO(ArticleJO articleJO) {

        if (articleJO == null) {
            return null;
        }
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(articleJO.getId());
        articleDTO.setUId(articleJO.getUId());
        articleDTO.setTitle(articleJO.getTitle());
        articleDTO.setContent(articleJO.getContent());
        articleDTO.setImageStr(articleJO.getImageStr());
        if (!CollectionUtils.isEmpty(articleJO.getTagIds())) {
            List<Integer> tags = Lists.newArrayList();;
            for (String tagId : articleJO.getTagIds()) {
                tags.add(Integer.valueOf(tagId));
            }
            articleDTO.setTagList(tags);
        }

        if (!CollectionUtils.isEmpty(articleJO.getCategoryIds())) {
            List<Integer> idList = Lists.newArrayList();
            for (String categoryId : articleJO.getCategoryIds()) {
                idList.add(Integer.valueOf(categoryId));
            }
            articleDTO.setCategoryIdList(idList);
        }
        return articleDTO;
    }
}
