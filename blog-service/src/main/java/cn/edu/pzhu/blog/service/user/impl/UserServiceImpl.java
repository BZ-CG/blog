package cn.edu.pzhu.blog.service.user.impl;
/*
 * @author CG
 * @date 2019/02/23 21:20
 * @discription
 */

import cn.edu.pzhu.blog.dao.user.UserDAO;
import cn.edu.pzhu.blog.dao.user.model.User;
import cn.edu.pzhu.blog.service.user.UserService;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> getAllUser() {
        List<User> list = Lists.newArrayList();
        User u1 = new User();
        u1.setName("张超");

        User u2 = new User();
        u2.setName("秋蓉");

        list.add(u1);
        list.add(u2);

        return list;
    }

    @Override
    public User getUserById(Integer id) {
        User user = null;
        try {
            user = userDAO.getUserById(id);
            log.info("调用 UserDAO 获取 USER 成功，user:{}", JSON.toJSONString(user));
        } catch (Exception e) {
            log.error("调用 UserDAO 获取 USER 异常", e);
        }

        return user;
    }
}
