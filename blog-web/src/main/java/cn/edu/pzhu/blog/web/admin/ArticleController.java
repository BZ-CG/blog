package cn.edu.pzhu.blog.web.admin;

import cn.edu.pzhu.base.response.ApiResponse;
import cn.edu.pzhu.blog.service.article.ArticleConverter;
import cn.edu.pzhu.blog.service.article.ArticleService;
import cn.edu.pzhu.blog.web.admin.converter.ArticleDTOConverter;
import cn.edu.pzhu.blog.web.admin.jo.ArticleJO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文章controller.
 * @author:CG
 * @date:2019/04/24 22:21
 */
@RequestMapping(value = "/admin")
@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/addArticle", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse add(ArticleJO articleJO, Session session)  {
        Integer  uId = 1;
        articleJO.setUId(uId);
        articleService.addCategory(ArticleDTOConverter.toArticleDTO(articleJO));
        return ApiResponse.success(articleJO);
    }

}
