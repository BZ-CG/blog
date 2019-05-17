package cn.edu.pzhu.blog.web.admin;

import cn.edu.pzhu.base.common.CommonCodeConstants;
import cn.edu.pzhu.base.common.ImageInfoEnum;
import cn.edu.pzhu.base.exception.BusinessException;
import cn.edu.pzhu.blog.dao.user.model.User;
import cn.edu.pzhu.blog.service.article.ArticleService;
import cn.edu.pzhu.blog.service.article.dto.EditArticleDTO;
import cn.edu.pzhu.blog.service.category.CategoryService;
import cn.edu.pzhu.blog.service.category.TagService;
import cn.edu.pzhu.blog.service.message.MessageService;
import cn.edu.pzhu.blog.service.user.UserService;
import cn.edu.pzhu.blog.web.util.ImageUrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author:CG
 */
@RequestMapping(value = "/admin")
@Controller
@Slf4j
public class PageController {
    private static final String PREFIX = "/AdminLTE";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ImageUrlUtils imageUrlUtils;


    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public ModelAndView exit(HttpSession session) {
        ModelAndView model = new ModelAndView();
        session.removeAttribute(CommonCodeConstants.USER);
        model.setViewName(PREFIX + "/login");
        return model;
    }

    @RequestMapping(value = "/toImageManagePage", method = RequestMethod.GET)
    public ModelAndView toImageManagePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX + "/pages/imageManage");
        model.addObject("active", "imageManage");
        model.addObject("headUrl", imageUrlUtils.getImageUrl(ImageInfoEnum.HEAD_IMAGE));
        return model;
    }

    @RequestMapping(value = "/toFriendLinkManagePage", method = RequestMethod.GET)
    public ModelAndView toFriendLinkManagePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX + "/pages/friendLinkManage");
        model.addObject("active", "friendLink");
        model.addObject("headUrl", imageUrlUtils.getImageUrl(ImageInfoEnum.HEAD_IMAGE));
        return model;
    }

    @RequestMapping(value = "toMessageManagePage", method = RequestMethod.GET)
    public ModelAndView toMessageManagePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX + "/pages/messageManage");
        model.addObject("active", "messageManage");
        model.addObject("headUrl", imageUrlUtils.getImageUrl(ImageInfoEnum.HEAD_IMAGE));
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User user, HttpSession session) {
        ModelAndView model = new ModelAndView();
        User dbUser = userService.getByName(user.getName());

        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            model.setViewName(PREFIX + "/index");
            model.addObject("active", "dashboard");
            model.addObject("articleCount", articleService.getCount(dbUser.getId()));
            model.addObject("categoryCount", categoryService.getCount(dbUser.getId()));
            model.addObject("tagCount", tagService.getCount(dbUser.getId()));
            model.addObject("messageCount", messageService.getCount(dbUser.getId()));
            session.setAttribute(CommonCodeConstants.USER, dbUser);
        } else {
            model.setViewName(PREFIX + "/login");
            model.addObject("msg","用户名或密码错误");
        }

        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PREFIX + "/login");
        return modelAndView;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PREFIX + "/login");
        return modelAndView;
    }

    @RequestMapping(value = "/toEditArticlePage", method = RequestMethod.GET)
    public ModelAndView toEditArticlePage(Integer aId) {
        Integer uId = 1;

        ModelAndView model = new ModelAndView();
        EditArticleDTO dto = articleService.getArticleWithTagAndCategory(uId, aId);

        model.setViewName(PREFIX + "/pages/article/publish");
        model.addObject("active", "writeBlog");
        model.addObject("fatherActive", "myblog");
        model.addObject("dto", dto);
        model.addObject("headUrl", imageUrlUtils.getImageUrl(ImageInfoEnum.HEAD_IMAGE));
        return model;
    }

    @RequestMapping(value = "/toClassificationManagePage", method = RequestMethod.GET)
    public ModelAndView toClassificationManagePage() {
        ModelAndView model = new ModelAndView();
        try {
            model.setViewName(PREFIX + "/pages/classificationManage");
            model.addObject("categoryList", categoryService.getAll());
            model.addObject("tagList", tagService.getTagByAid(1, 2));
            model.addObject("active", "manageCategory");
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("PageController.toClassificationManagePage", e);
            throw new BusinessException(e.getMessage());
        }
        model.addObject("headUrl", imageUrlUtils.getImageUrl(ImageInfoEnum.HEAD_IMAGE));
        return model;
    }

    @RequestMapping(value = "/toManageArticlePage", method = RequestMethod.GET)
    public ModelAndView toManageArticlePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX + "/pages/article/manageArticle");
        model.addObject("active", "manageBlog");
        model.addObject("fatherActive", "myblog");
        model.addObject("headUrl", imageUrlUtils.getImageUrl(ImageInfoEnum.HEAD_IMAGE));
        return model;

    }

    @RequestMapping(value = "/toPublishPage", method = RequestMethod.GET)
    public ModelAndView toPublishPage(@RequestParam(value = "aId",  required = false) Integer aId) {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX + "/pages/article/publish");
        model.addObject("active", "writeBlog");
        model.addObject("fatherActive", "myblog");
        model.addObject("headUrl", imageUrlUtils.getImageUrl(ImageInfoEnum.HEAD_IMAGE));
        return model;
    }

    @RequestMapping(value = "/toIndex", method = RequestMethod.GET)
    public ModelAndView toIndex(HttpSession session) {
        User user = (User) session.getAttribute(CommonCodeConstants.USER);
        ModelAndView model = new ModelAndView();

        model.setViewName(PREFIX + "/index");
        model.addObject("active", "dashboard");
        model.addObject("articleCount", articleService.getCount(user.getId()));
        model.addObject("categoryCount", categoryService.getCount(user.getId()));
        model.addObject("tagCount", tagService.getCount(user.getId()));
        model.addObject("messageCount", messageService.getCount(user.getId()));
        model.addObject("headUrl", imageUrlUtils.getImageUrl(ImageInfoEnum.HEAD_IMAGE));
        return model;
    }
}
