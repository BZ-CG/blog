package cn.edu.pzhu.blog.web.admin;

import cn.edu.pzhu.base.common.CommonCodeConstants;
import cn.edu.pzhu.base.qiniu.QiniuUtils;
import cn.edu.pzhu.base.response.ApiResponse;
import cn.edu.pzhu.base.util.RedisUtils;
import cn.edu.pzhu.blog.dao.image.model.ImageInfo;
import cn.edu.pzhu.blog.dao.user.model.User;
import cn.edu.pzhu.blog.service.image.ImageInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author:CG
 * @date:2019/05/15 22:03
 */
@RequestMapping("/admin/imageInfo")
@Controller
@Slf4j
public class ImageInfoController {

    private static final String IMAGE_REDIS_KEY = "COMMON_IMAGE_URL";

    @Autowired
    private ImageInfoService imageInfoService;

    @Autowired
    private QiniuUtils qiniuUtils;

    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    @ResponseBody
    public String getImage(Integer code, HttpSession session) {
        User user = (User) session.getAttribute(CommonCodeConstants.USER);
        ImageInfo imageInfo = imageInfoService.getByCode(user.getId(), code);

        return imageInfo.getUrl();
    }


    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse uploadImage(Integer id, String baseStr) {
        try {
            String fileName = System.currentTimeMillis() + ".png";
            String url = qiniuUtils.uploadBase64(baseStr, fileName);
            imageInfoService.updateUrlById(id, url);
            redisUtils.deleteKey(IMAGE_REDIS_KEY);
            return ApiResponse.success(url);
        } catch (Exception e) {
            log.error("调用 ImageInfoController.uploadImage 异常", e);
            return ApiResponse.error("上传失败");
        }
    }

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getList(HttpSession session) {
        try {
            User user = (User) session.getAttribute(CommonCodeConstants.USER);
            List<ImageInfo> list = imageInfoService.queryByUid(user.getId());
            return ApiResponse.success(list);
        } catch (Exception e) {
            return ApiResponse.error("发生异常，请联系管理员!");
        }
    }
}
