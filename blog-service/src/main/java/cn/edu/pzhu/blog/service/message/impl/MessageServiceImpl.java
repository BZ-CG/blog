package cn.edu.pzhu.blog.service.message.impl;

import cn.edu.pzhu.base.util.ExceptionUtils;
import cn.edu.pzhu.blog.dao.message.MessageDAO;
import cn.edu.pzhu.blog.dao.message.model.Message;
import cn.edu.pzhu.blog.service.message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/06 23:38
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public void add(Message message) {
        try {
             messageDAO.add(message);
        } catch (Exception e) {
            log.error("调用 messageDAO.add 新增留言信息失败.", e);
            ExceptionUtils.buildBusinessException();
        }
    }

    @Override
    public List<Message> queryMessage(Integer uId) {
        List<Message> list = null;
        try {
            list = messageDAO.queryMessage(uId);
        } catch (Exception e) {
            log.error("调用 messageDAO.queryMessage 方法获取留言信息失败.", e);
            ExceptionUtils.buildBusinessException();
        }
        return list;
    }
}
