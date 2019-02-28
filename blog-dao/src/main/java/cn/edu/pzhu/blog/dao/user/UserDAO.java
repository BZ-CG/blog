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

    List<User> getAllUser();

    User getUserById(Integer id);
}
