package cn.edu.pzhu.blog.service.link;

import cn.edu.pzhu.blog.dao.link.model.FriendLink;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/08 21:08
 */
public interface FriendLinkService {

    /**
     * 获取友链列表.
     * @param uId
     * @return
     */
    List<FriendLink> queryFriendLink(Integer uId);
}
