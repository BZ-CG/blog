package cn.edu.pzhu.blog.web.admin;

import cn.edu.pzhu.base.exception.BusinessException;
import cn.edu.pzhu.base.qiniu.QiniuUtils;
import cn.edu.pzhu.base.response.ApiResponse;
import cn.edu.pzhu.base.util.AssertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:CG
 */
@Controller
@RequestMapping(value = "/file")
@Slf4j
public class QiNiuController {

    @Autowired
    public QiniuUtils qiniuUtils;

    @RequestMapping(value = "/uploadImageToQiniu", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadImageToQiniu(MultipartFile imageFile, HttpSession session, HttpServletRequest request) {
        AssertUtils.isNotNull(imageFile, "imageFile");

        Map<String, Object> map = new HashMap<>();
        FileInputStream inputStream = null;
        try {
            inputStream = (FileInputStream) imageFile.getInputStream();
            String fileName = imageFile.getOriginalFilename();
            String fileNameExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            String realName = String.valueOf(System.currentTimeMillis()) + fileNameExtension;

            String path = qiniuUtils.uploadToken(inputStream, realName);
            map.put("errno", 0);
            map.put("data", Arrays.asList(path));

            return map;
        } catch (Exception e) {
            log.error("QiNiuController 上传图片到七牛云发生异常", e);
            throw new BusinessException("11111", e);
        }
    }
}
