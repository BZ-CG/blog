package cn.edu.pzhu.blog.web.article;

import cn.edu.pzhu.base.common.ImageInfoEnum;
import cn.edu.pzhu.base.common.RelationItem;
import cn.edu.pzhu.base.response.ApiResponse;
import cn.edu.pzhu.base.util.*;
import cn.edu.pzhu.blog.dao.article.model.Article;
import cn.edu.pzhu.blog.dao.category.model.Category;
import cn.edu.pzhu.blog.dao.category.model.Relation;
import cn.edu.pzhu.blog.dao.category.model.Tag;
import cn.edu.pzhu.blog.dao.image.model.ImageInfo;
import cn.edu.pzhu.blog.dao.link.model.FriendLink;
import cn.edu.pzhu.blog.dao.message.model.Message;
import cn.edu.pzhu.blog.service.article.ArticleConverter;
import cn.edu.pzhu.blog.service.article.ArticleService;
import cn.edu.pzhu.blog.service.article.dto.ArticleJO;
import cn.edu.pzhu.blog.service.category.CategoryService;
import cn.edu.pzhu.blog.service.category.RelationService;
import cn.edu.pzhu.blog.service.category.TagService;
import cn.edu.pzhu.blog.service.image.ImageInfoService;
import cn.edu.pzhu.blog.service.link.FriendLinkService;
import cn.edu.pzhu.blog.service.message.MessageService;
import cn.edu.pzhu.blog.web.article.jo.ArticleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import okhttp3.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author:CG
 * @date:2019/05/02 7:20
 */
@Controller
@RequestMapping(value = "/article")
public class FrontArticleController {

    private static final String[] styles = {"am-badge am-radius", "am-badge am-radius am-badge-primary", "am-badge am-radius am-badge-secondary",
                                            "am-badge am-radius am-badge-success", "am-badge am-radius am-badge-warning"};

    private static final String QQ_IMG_URL = "http://q1.qlogo.cn/g?b=qq&s=100&nk=";

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private RelationService relationService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private ImageInfoService imageInfoService;


    @RequestMapping(value = "/deleteTag", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse deleteTag(Integer id) {
        Integer uId = 1;
        tagService.deleteTag(uId, id);
        return ApiResponse.success();
    }

    @RequestMapping(value = "/addTag", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse addTag(String tagName) {
        Integer uId = 1;
        return ApiResponse.success(tagService.addTag(uId, tagName));
    }

    @RequestMapping(value = "/getFriendLink", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getFriedLink(@RequestParam(value = "index", defaultValue = "1") Integer index) {
        Integer uId = 1;
        PageHelper.startPage(index, 16);
        List<FriendLink> list = friendLinkService.queryFriendLink(uId);
        PageInfo<FriendLink> pageInfo = new PageInfo<>(list, 5);

        return ApiResponse.success(pageInfo);
    }

    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse addMessage(String qqNumber, String content, HttpServletRequest request) {
        Integer uId = 1;
        Message message = new Message();
        fillPositionInformation(message, request);

        JSONArray jsonArray = getQQObject(qqNumber);
        message.setQqNumber(qqNumber);
        message.setQqImage(QQ_IMG_URL + qqNumber);
        message.setQqName(jsonArray.get(6).toString());

        message.setContent(content);
        message.setCreateDate(DateUtils.getCurrentDateStr());
        message.setModifyDate(DateUtils.getCurrentDateStr());
        message.setWriteTime(new Date());
        message.setUId(uId);

        messageService.add(message);
        return ApiResponse.success();
    }

    private void fillPositionInformation(Message message, HttpServletRequest request) {
        String ipAddress = IPUtils.getIpAddress(request);
        //String ipAddress = "172.31.42.41";
        String addresses = AddressUtils.getAddresses("ip=" + ipAddress, "utf-8");

        JSONObject resultObject = JSONObject.fromObject(addresses);

        String region = JSONObject.fromObject(resultObject.get("data")).get("region").toString();
        String city = JSONObject.fromObject(resultObject.get("data")).get("city").toString();
        String county = JSONObject.fromObject(resultObject.get("data")).get("county").toString();

        message.setPosition(region + "/" + city + "/" + county);

    }

    private JSONArray getQQObject(String qqNumber) {
        String qqUrlStr = "http://users.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=" + qqNumber;
        String json = HttpClientUtils.send(qqUrlStr);

        json = json.replaceAll("portraitCallBack|\\\\s*|\\t|\\r|\\n", "");
        json = json.substring(1, json.length() - 1);

        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray(qqNumber);

        return jsonArray;

    }

    @RequestMapping(value = "/getMessageList", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getMessageList(@RequestParam(value = "index", defaultValue = "1") Integer index) {
        Integer uId = 1;
        PageHelper.startPage(index, 5);
        List<Message> messageList = messageService.queryMessage(uId);
        PageInfo<Message> pageInfo = new PageInfo<>(messageList, 5);

        return ApiResponse.success(pageInfo);
    }


    @RequestMapping(value = "/getArticleByClassification", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getArticleByClassification(Integer cId, @RequestParam(value = "index", defaultValue = "1") Integer index) {
        Integer uId = 1;
        Category categoryByName = categoryService.getCategoryById(cId);

        List<Integer> aIds = relationService.getAidByCid(uId, categoryByName.getId());

        PageHelper.startPage(index, 5);
        List<Article> articleList = articleService.getArticleByIds(uId, aIds);
        PageInfo<Article> info = new PageInfo<>(articleList,5);

        List<ArticleJO> articleJOS = Lists.newArrayList();
        for (Article article : articleList) {
            articleJOS.add(getArticleJOFromArticle(uId, article, categoryByName.getId(), 1));
        }
        ArticleVO articleVO = new ArticleVO();
        articleVO.setArticleJOList(articleJOS);
        articleVO.setPageInfo(info);

        return ApiResponse.success(articleVO);
    }


    @RequestMapping(value = "/getArticleDetails", method = RequestMethod.GET)
    public ModelAndView getArticleDetails(Integer aId) {
        Integer userId = 1;
        articleService.addReadNumber(userId, aId);
        Article article = articleService.getById(aId);
        ArticleJO articleJO = getArticleJOFromArticle(userId, article, null, 1);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/blog/articleDetails");
        modelAndView.addObject("articleJO", articleJO);
        modelAndView.addObject("url", getImageUrl(ImageInfoEnum.BG_IMAGE));
        return modelAndView;
    }

    @RequestMapping("/getArticleList")
    @ResponseBody
    public ApiResponse getArticleList(@RequestParam(value = "index", defaultValue = "1") Integer index) {
        Integer userId = 1;
        PageHelper.startPage(index, 3);
        List<Article> list = articleService.getArticleList(userId);
        PageInfo<Article> info = new PageInfo<>(list,1);

        List<ArticleJO> articleJOS = Lists.newArrayList();
        for (Article article : list) {
            articleJOS.add(getArticleJOFromArticle(userId, article, null, info.getPages()));
        }
        return ApiResponse.success(articleJOS);
    }


    private ArticleJO getArticleJOFromArticle(Integer uId, Article article, Integer cId, Integer pages) {
        ArticleJO articleJO = ArticleConverter.toArticleJO(article);
        List<Relation> relations = relationService.getRelationByAid(uId, article.getId());
        if (cId != null) {
            Category category = categoryService.getCategoryById(cId);
            articleJO.setCategoryName(category.getName());
            articleJO.setCategoryId(category.getId());
        } else {
            for (Relation relation : relations) {
                if (RelationItem.getRelationItemByCode(relation.getIden()).equals(RelationItem.CATEGORY)) {
                    Category category = categoryService.getCategoryById(relation.getItemId());
                    articleJO.setCategoryName(category.getName());
                    articleJO.setCategoryId(category.getId());
                    break;
                }
            }
        }

        for (Relation relation : relations) {
            if (RelationItem.getRelationItemByCode(relation.getIden()).equals(RelationItem.TAG)) {
                Tag tag = tagService.getById(relation.getItemId());
                articleJO.setTagName(tag.getName());
                break;
            }
        }
        articleJO.setPages(pages);
        return articleJO;
    }

    @RequestMapping("/getClassification")
    @ResponseBody
    public ApiResponse<List<Category>> getClassification() {
        Integer userId = 1;
        List<Category> list = categoryService.getAllCategoryByUid(userId);
        return ApiResponse.success(list);
    }

    @RequestMapping("/getHotList")
    @ResponseBody
    public ApiResponse<List<HotListJO>> getHotList() {
        List<Article> list = articleService.getList(1, 5);
        return ApiResponse.success(converterHotListJO(list));
    }

    public List<HotListJO> converterHotListJO(List<Article> list) {
        List<HotListJO> hotListJOS = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            HotListJO hotListJO = new HotListJO();
            hotListJO.setId(list.get(i).getId());
            hotListJO.setIndex(i + 1);
            hotListJO.setTitle(list.get(i).getTitle());
            hotListJO.setReadNumber(list.get(i).getReadNumber());
            hotListJO.setClassStyle(styles[i]);

            hotListJOS.add(hotListJO);
        }
        return hotListJOS;
    }
    public String getImageUrl(ImageInfoEnum imageInfoEnum) {
        Integer uId = 1;
        List<ImageInfo> imageInfos = imageInfoService.queryByUid(uId);
        for (ImageInfo imageInfo : imageInfos) {
            if (ImageInfoEnum.getByCode(imageInfo.getCode()).equals(imageInfoEnum)) {
                return imageInfo.getUrl();
            }
        }
        return "";
    }
}
