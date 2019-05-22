package cn.edu.pzhu.blog.web.admin;

import cn.edu.pzhu.base.common.RelationItem;
import cn.edu.pzhu.base.response.ApiResponse;
import cn.edu.pzhu.base.util.ExceptionUtils;
import cn.edu.pzhu.blog.dao.article.model.Article;
import cn.edu.pzhu.blog.dao.category.model.Category;
import cn.edu.pzhu.blog.dao.category.model.Relation;
import cn.edu.pzhu.blog.dao.category.model.Tag;
import cn.edu.pzhu.blog.dao.message.model.Message;
import cn.edu.pzhu.blog.service.article.ArticleConverter;
import cn.edu.pzhu.blog.service.article.ArticleService;
import cn.edu.pzhu.blog.service.article.dto.ArticleDTO;
import cn.edu.pzhu.blog.service.category.CategoryService;
import cn.edu.pzhu.blog.service.category.RelationService;
import cn.edu.pzhu.blog.service.message.MessageService;
import cn.edu.pzhu.blog.web.admin.converter.ArticleDTOConverter;
import cn.edu.pzhu.blog.web.admin.jo.ArticleJO;
import cn.edu.pzhu.blog.web.admin.jo.ManagerArticleJO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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


    @Autowired
    private RelationService relationService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/deleteMessage", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse deleteMessage(Integer id) {
        try {
            messageService.deleteById(id);
        } catch (Exception e) {
            log.error("调用ArticleController.deleteMessage 删除留言异常", e);
            return ApiResponse.error("操作异常");
        }
        return ApiResponse.success();
    }

    @RequestMapping(value = "/getMessageList", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getMessageList(@RequestParam(value = "index", defaultValue = "1") Integer index) {
        Integer uId = 1;
        PageHelper.startPage(index, 10);
        List<Message> messageList = messageService.queryMessage(uId);
        PageInfo<Message> pageInfo = new PageInfo<>(messageList, 2);

        return ApiResponse.success(pageInfo);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse delete(Integer aId) {
        return ApiResponse.success(articleService.deleteById(aId));
    }


    @RequestMapping(value = "/addAndUpdateArticle", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse add(ArticleJO articleJO, Session session)  {
        Integer  uId = 1;
        articleJO.setUId(uId);
        ArticleDTO articleDTO = ArticleDTOConverter.toArticleDTO(articleJO);
        if (articleJO.getId() != null) {
            articleService.updateArticle(articleDTO);
        } else {
            articleService.addCategory(articleDTO);
        }
        return ApiResponse.success(articleJO);
    }

    @RequestMapping(value = "/getArticleList", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getArticleList(@RequestParam(value = "index", defaultValue = "1") Integer index) {
        Integer userId = 1;
        PageHelper.startPage(index, 10);
        List<Article> list = articleService.getArticleList(userId);
        PageInfo<Article> info = new PageInfo<>(list,5);

        List<cn.edu.pzhu.blog.service.article.dto.ArticleJO> articleJOS = Lists.newArrayList();
        for (Article article : list) {
            articleJOS.add(getArticleJOFromArticle(userId, article));
        }
        ManagerArticleJO managerArticleJO = new ManagerArticleJO();
        managerArticleJO.setArticleList(articleJOS);
        managerArticleJO.setNavigatepageNums(info.getNavigatepageNums());
        managerArticleJO.setPageNum(info.getPageNum());
        managerArticleJO.setPages(info.getPages());
        managerArticleJO.setTotal(info.getTotal());

        return ApiResponse.success(managerArticleJO);

    }
    private cn.edu.pzhu.blog.service.article.dto.ArticleJO getArticleJOFromArticle(Integer uId, Article article) {
        cn.edu.pzhu.blog.service.article.dto.ArticleJO articleJO = ArticleConverter.toArticleJO(article);
        List<Relation> relations = relationService.getRelationByAid(uId, article.getId());

        for (Relation relation : relations) {
            if (RelationItem.getRelationItemByCode(relation.getIden()).equals(RelationItem.CATEGORY)) {
                Category category = categoryService.getCategoryById(relation.getItemId());
                articleJO.setCategoryName(category.getName());
                break;
            }
        }

        return articleJO;
    }




}
