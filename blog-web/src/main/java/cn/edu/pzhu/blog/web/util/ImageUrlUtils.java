package cn.edu.pzhu.blog.web.util;

import cn.edu.pzhu.base.common.ImageInfoEnum;
import cn.edu.pzhu.base.util.RedisUtils;
import cn.edu.pzhu.blog.dao.image.model.ImageInfo;
import cn.edu.pzhu.blog.service.image.ImageInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/17 19:30
 */
@Component
@Slf4j
public class ImageUrlUtils {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ImageInfoService imageInfoService;

    private static final String IMAGE_REDIS_KEY = "COMMON_IMAGE_URL";



    public String getImageUrl(ImageInfoEnum imageInfoEnum) {
        Integer uId = 1;
        List<ImageInfo> imageInfos = null;
        try {
            Object urlObject = redisUtils.lGet(IMAGE_REDIS_KEY);
            if (urlObject == null) {
                imageInfos = imageInfoService.queryByUid(uId);
                redisUtils.lSet(IMAGE_REDIS_KEY, imageInfos, 60 * 60 * 24);
            } else {
                imageInfos = (List<ImageInfo>) urlObject;
            }
        } catch (Exception e) {
            log.error("从 redis 中获取url失败", e);
            imageInfos = imageInfoService.queryByUid(uId);
        }
        for (ImageInfo imageInfo : imageInfos) {
            if (ImageInfoEnum.getByCode(imageInfo.getCode()).equals(imageInfoEnum)) {
                return imageInfo.getUrl();
            }
        }
        return "";
    }
}
