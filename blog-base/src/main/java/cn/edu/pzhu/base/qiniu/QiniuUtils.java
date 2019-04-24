package cn.edu.pzhu.base.qiniu;

import cn.edu.pzhu.base.exception.BusinessException;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.net.URLEncoder;

/**
 * @author:CG
 * @date:2019/04/20 0:46
 */
@Component
@Slf4j
public class QiniuUtils {
    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.secretKey}")
    private String secretKey;
    @Value("${qiniu.bucket}")
    private String bucket;
    @Value("${qiniu.path}")
    private String path;

    public String uploadToken(FileInputStream file, String key) {
        //Zone.zone0() 表示华北区域
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        log.info("path=" + path);
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                log.info("key:" + putRet.key);

                String encodedFileName = URLEncoder.encode(putRet.key, "utf-8");
                //获取下载地址,前端可以通过img访问
                String finalUrl = String.format("http://%s/%s", path, encodedFileName);
                log.info("访问地址:" + finalUrl);

                return finalUrl;
            } catch (QiniuException ex) {
                log.error("使用七牛云上传图片失败", ex);
                throw new BusinessException("1111","使用七牛云上传图片失败", ex);
            }
        } catch (Exception e) {
            log.error("使用七牛云上传图片异常", e);
            throw new BusinessException("1111","使用七牛云上传图片异常", e);
        }
    }
}
