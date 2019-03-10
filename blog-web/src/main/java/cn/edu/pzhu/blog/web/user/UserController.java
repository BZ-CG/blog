package cn.edu.pzhu.blog.web.user;

import cn.edu.pzhu.base.response.ApiResponse;
import cn.edu.pzhu.blog.dao.user.model.User;
import cn.edu.pzhu.blog.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author CG
 * @date 2019/02/23 22:04
 * @discription
 */
@Controller
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping("/user/getById")
    @ResponseBody
    public ApiResponse<User> getUserById(@RequestParam("id") Integer id) {

        //throw new RuntimeException("这是一个异常信息");
        return ApiResponse.success(userService.getUserById(id));
    }



    @RequestMapping("/user/getAll")
    @ResponseBody
    public ApiResponse<List<User>> getAllUser() {

        LOGGER.error("测试信息------");
        return ApiResponse.success(userService.getAllUser());
    }


}
