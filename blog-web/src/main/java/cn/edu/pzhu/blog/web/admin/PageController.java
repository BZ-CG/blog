package cn.edu.pzhu.blog.web.admin;

import cn.edu.pzhu.base.exception.BusinessException;
import cn.edu.pzhu.blog.service.category.CategoryService;
import cn.edu.pzhu.blog.service.category.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping(value = "/toClassificationManagePage", method = RequestMethod.GET)
    public ModelAndView toClassificationManagePage() {
        ModelAndView model = new ModelAndView();
        try {
            model.setViewName(PREFIX + "/pages/classificationManage");
            model.addObject("categoryList", categoryService.getAll());
            model.addObject("tagList", tagService.getTagByAid(1, 2));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("PageController.toClassificationManagePage", e);
            throw new BusinessException(e.getMessage());
        }
        return model;
    }

    @RequestMapping(value = "/toManageArticlePage", method = RequestMethod.GET)
    public ModelAndView toManageArticlePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX + "/pages/article/manageArticle");
        return model;

    }

    @RequestMapping(value = "/toPublishPage", method = RequestMethod.GET)
    public ModelAndView toPublishPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX + "/pages/article/publish");
        return model;
    }

    @RequestMapping(value = "/toIndex", method = RequestMethod.GET)
    public ModelAndView toIndex() {
        ModelAndView model = new ModelAndView();
        model.setViewName(PREFIX + "/index");
        return model;
    }
}
