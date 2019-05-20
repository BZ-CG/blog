package cn.edu.pzhu.blog.service.message;

import cn.edu.pzhu.blog.dao.message.model.Message;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/06 23:38
 */
public interface MessageService {

    /**
     * 获取留言数量.
     * @param uId
     * @return
     */
    Integer getCount(Integer uId);

    /**
     * 按 id 删除留言.
     * @param id
     */
    void deleteById(Integer id);

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
