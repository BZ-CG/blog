package cn.edu.pzhu.blog.web.blog;

import cn.edu.pzhu.base.common.ImageInfoEnum;
import cn.edu.pzhu.base.util.RedisUtils;
import cn.edu.pzhu.blog.dao.article.model.Article;
import cn.edu.pzhu.blog.dao.category.model.Category;
import cn.edu.pzhu.blog.dao.image.model.ImageInfo;
import cn.edu.pzhu.blog.service.article.ArticleService;
import cn.edu.pzhu.blog.service.category.CategoryService;
import cn.edu.pzhu.blog.service.image.ImageInfoService;
import cn.edu.pzhu.blog.web.util.ImageUrlUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author:CG
 */
@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageUrlUtils imageUrlUtils;

    private static final String IMAGE_REDIS_KEY = "COMMON_IMAGE_URL";

        @RequestMapping(value = "toIndex", method = RequestMethod.GET)
    public ModelAndView toIndex() {
        ModelAndView model = new ModelAndView();
        model.addObject("url", imageUrlUtils.getImageUrl(ImageInfoEnum.BG_IMAGE));
        model.setViewName("/index");
        return model;
    }

    @RequestMapping(value = "/friendLinks", method = RequestMethod.GET)
    public ModelAndView toFriendLinks() {
        ModelAndView model = new ModelAndView();
        model.addObject("url", imageUrlUtils.getImageUrl(ImageInfoEnum.BG_IMAGE));
        model.setViewName("/blog/friendLink");
        return model;
    }

    @RequestMapping(value = "/toArticleClassification", method = RequestMethod.GET)
    public ModelAndView toArticleClassification(@RequestParam("id") Integer id, @RequestParam("flag") Integer flag) {
        ModelAndView model = new ModelAndView();
        Category category = categoryService.getCategoryById(id);
        model.setViewName("/blog/articleClassification");
        model.addObject("category", category);
        //1 走分类，2 走时间.
        model.addObject("flag", flag);
        model.addObject("url", imageUrlUtils.getImageUrl(ImageInfoEnum.BG_IMAGE));
        return model;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView toAdmin() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/AdminLTE/starter");
        return model;
    }

    @RequestMapping(value = "/toMessageBoard", method = RequestMethod.GET)
    public ModelAndView toMessageBoard() {
        ModelAndView model = new ModelAndView();
        model.addObject("url", imageUrlUtils.getImageUrl(ImageInfoEnum.BG_IMAGE));
        model.setViewName("/blog/messageBoard");
        return model;
    }

    @RequestMapping(value = "/toDonationPage", method = RequestMethod.GET)
    public ModelAndView toDonationPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("url", imageUrlUtils.getImageUrl(ImageInfoEnum.BG_IMAGE));
        model.setViewName("/blog/donationPage");
        return model;
    }

    @RequestMapping(value = "toaboutMe", method = RequestMethod.GET)
    public ModelAndView toAboutMe() {
        ModelAndView model = new ModelAndView();
        model.addObject("url", imageUrlUtils.getImageUrl(ImageInfoEnum.BG_IMAGE));
        model.addObject("headUrl", imageUrlUtils.getImageUrl(ImageInfoEnum.HEAD_IMAGE));
        model.addObject("weChatUrl", imageUrlUtils.getImageUrl(ImageInfoEnum.WECHAT));
        model.setViewName("/blog/aboutMe");
        return model;
    }

    @RequestMapping(value = "/toBlogPage", method = RequestMethod.GET)
    public ModelAndView toBlogPage() {
        ModelAndView model = new ModelAndView();

        List<Article> list = articleService.getSlidesImageUrl(5);

        model.addObject("url", imageUrlUtils.getImageUrl(ImageInfoEnum.BG_IMAGE));
        model.addObject("articles", list);
        model.setViewName("/blog/blogPage");

        return model;
    }

    //public String getImageUrl(ImageInfoEnum imageInfoEnum) {
    //    Integer uId = 1;
    //    List<ImageInfo> imageInfos = null;
    //    Object urlObject = redisUtils.lGet(IMAGE_REDIS_KEY);
    //    if (urlObject == null) {
    //        imageInfos = imageInfoService.queryByUid(uId);
    //        redisUtils.lSet(IMAGE_REDIS_KEY, imageInfos);
    //    } else {
    //        imageInfos = (List<ImageInfo>) urlObject;
    //    }
    //    for (ImageInfo imageInfo : imageInfos) {
    //        if (ImageInfoEnum.getByCode(imageInfo.getCode()).equals(imageInfoEnum)) {
    //            return imageInfo.getUrl();
    //        }
    //    }
    //    return "";
    //}
}
