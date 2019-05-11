package cn.edu.pzhu.blog.dao.message;

import cn.edu.pzhu.blog.dao.message.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/06 21:17
 */
@Component
public interface MessageDAO {

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
