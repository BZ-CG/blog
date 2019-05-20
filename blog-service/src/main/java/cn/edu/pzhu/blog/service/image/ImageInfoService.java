package cn.edu.pzhu.blog.service.image;

import cn.edu.pzhu.blog.dao.image.model.ImageInfo;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/15 21:57
 */
public interface ImageInfoService {

    /**
     * 修改 url.
     * @param id
     * @param url
     */
    void updateUrlById(Integer id, String url);

    /**
     * 获取 uid 下所有的图片信息.
     * @param uId
     * @return
     */
    List<ImageInfo> queryByUid(Integer uId);

    /**
     * 通过枚举 id 获取图片信息.
     * @param uId
     * @param code
     * @return
     */
    ImageInfo getByCode(Integer uId, Integer code);
}
