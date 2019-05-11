package cn.edu.pzhu.base.util.base64;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 处理 base64 形式的文件类
 * @author:CG
 * @date:2019/05/10 10:45
 */
public class BASE64DecodedMultipartFile implements MultipartFile {
    private  byte[] imgContent;
    private  String header;

    public BASE64DecodedMultipartFile(byte[] imgContent,String header) {
        this.imgContent = imgContent;
        this.header = header.split(";")[0];
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        // TODO Auto-generated method stub
        return System.currentTimeMillis() + (int)Math.random() * 10000 + "." + header.split("/")[1];
    }

    @Override
    public String getContentType() {
        // TODO Auto-generated method stub
        return header.split(":")[1];
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        // TODO Auto-generated method stub
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        // TODO Auto-generated method stub
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // TODO Auto-generated method stub
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        // TODO Auto-generated method stub
        new FileOutputStream(dest).write(imgContent);
    }
}
