package cn.edu.pzhu.base.util.base64;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

/**
 * @author:CG
 * @date:2019/05/10 10:44
 */
public class Base64FileUtils {
    /**
     * 	  根据传来的 base64 字符串，解析后返回一个 MultipartFile
     * @Title: base64ToMultipart
     * @param @param base64
     * @param @return    参数
     * @return MultipartFile    返回类型
     */
    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStrs[1]);

            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFile(b, baseStrs[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
