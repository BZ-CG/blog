package cn.edu.pzhu.blog.web.admin;

import cn.edu.pzhu.base.response.ApiResponse;
import cn.edu.pzhu.blog.service.article.ArticleConverter;
import cn.edu.pzhu.blog.web.admin.jo.ArticleJO;
import lombok.extern.slf4j.Slf4j;
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


    @RequestMapping(value = "/addArticle", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse add(ArticleJO articleJO)  {
        System.out.println("articleDTO: "  + articleJO);
        return ApiResponse.success(articleJO);
    }

}
