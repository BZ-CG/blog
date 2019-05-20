package cn.edu.pzhu.blog.dao.user;

import cn.edu.pzhu.blog.dao.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * @author:CG
 * @date:2019/02/23 21:10
 * @discription:
 */
@Repository
public interface UserDAO {

    /**
     * 通过名字获取 user 信息.
     * @param name
     * @return
     */
    User getByName(String name);

    List<User> getAllUser();

    User getUserById(Integer id);
}
