package cn.edu.pzhu.blog.dao.link;

import cn.edu.pzhu.blog.dao.link.model.FriendLink;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/08 20:48
 */
@Component
public interface FriendLinkDAO {


    /**
     * 获取友链列表.
     * @param uId
     * @return
     */
    List<FriendLink> queryFriendLink(Integer uId);
}
