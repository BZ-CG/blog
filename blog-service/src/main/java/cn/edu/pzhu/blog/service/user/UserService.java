package cn.edu.pzhu.blog.service.user;

import cn.edu.pzhu.blog.dao.user.model.User;

import java.util.List;

/**
 * @author CG
 * @date 2019/02/23 22:05
 * @discription
 */
public interface UserService {

    List<User> getAllUser();
    User getUserById(Integer id);
}
