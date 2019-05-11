package cn.edu.pzhu.blog.service.message;

import cn.edu.pzhu.blog.dao.message.model.Message;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/06 23:38
 */
public interface MessageService {

    /**
     * 新增留言.
     * @param message
     */
    void add(Message message);

    /**
     * 查询留言信息.
     * @param uId 用户id
     * @return
     */
    List<Message> queryMessage(Integer uId);
}
