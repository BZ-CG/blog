package cn.edu.pzhu.blog.service.user;

import cn.edu.pzhu.blog.dao.user.model.User;

import java.util.List;

/**
 * @author CG
 * @date 2019/02/23 22:05
 * @discription
 */
public interface UserService {

    /**
     * 通过名字获取 user 信息.
     * @param name
     * @return
     */
    User getByName(String name);

    List<User> getAllUser();
    User getUserById(Integer id);
}
