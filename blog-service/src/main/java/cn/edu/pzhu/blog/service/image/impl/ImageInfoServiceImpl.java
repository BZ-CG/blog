package cn.edu.pzhu.blog.service.image.impl;

import cn.edu.pzhu.base.util.ExceptionUtils;
import cn.edu.pzhu.blog.dao.image.ImageInfoDAO;
import cn.edu.pzhu.blog.dao.image.model.ImageInfo;
import cn.edu.pzhu.blog.service.image.ImageInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/15 21:58
 */
@Service
@Slf4j
public class ImageInfoServiceImpl implements ImageInfoService {

    @Autowired
    private ImageInfoDAO imageInfoDAO;


    @Override
    public void updateUrlById(Integer id, String url) {
        try {
            imageInfoDAO.updateUrlById(id, url);
        } catch (Exception e) {
            log.error("调用 imageInfoDAO.updateUrlById 修改图片信息失败", e);
            throw ExceptionUtils.buildBusinessException();
        }
    }

    @Override
    public List<ImageInfo> queryByUid(Integer uId) {
        List<ImageInfo> list = null;
        try {
            list = imageInfoDAO.queryByUid(uId);
        } catch (Exception e) {
            log.error("调用 imageInfoDAO.queryByUid 获取用户图片信息失败", e);
            throw ExceptionUtils.buildBusinessException();
        }

        return list;
    }

    @Override
    public ImageInfo getByCode(Integer uId, Integer code) {
        ImageInfo info = null;
        try {
            info = imageInfoDAO.getByCode(uId, code);
        } catch (Exception e) {
            log.error("调用 imageInfoDAO.getByCode 获取图片信息失败", e);
            throw ExceptionUtils.buildBusinessException();
        }
        return info;
    }
}
