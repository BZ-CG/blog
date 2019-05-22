package cn.edu.pzhu.blog.web.admin;

import cn.edu.pzhu.base.common.CommonCodeConstants;
import cn.edu.pzhu.base.response.ApiResponse;
import cn.edu.pzhu.blog.dao.link.model.FriendLink;
import cn.edu.pzhu.blog.dao.user.model.User;
import cn.edu.pzhu.blog.service.link.FriendLinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author:CG
 * @date:2019/05/15 17:13
 */
@RequestMapping(value = "/admin")
@Controller
@Slf4j
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;


    @RequestMapping(value = "/deleteFriendLink", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse deleteFriendLink(Integer id) {
        friendLinkService.deleteById(id);
        return ApiResponse.success();
    }

    @RequestMapping(value = "/addOrUpdateFriendLink", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse editFriendLink(FriendLink friendLink, HttpSession session) {
        try {
            User user = (User) session.getAttribute(CommonCodeConstants.USER);
            friendLink.setUId(user.getId());
            friendLinkService.addOrUpdate(friendLink);
        } catch (Exception e) {
            return ApiResponse.error("操作失败!");
        }
        return ApiResponse.success();
    }
}
