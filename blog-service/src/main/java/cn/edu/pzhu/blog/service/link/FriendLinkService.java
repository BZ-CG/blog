package cn.edu.pzhu.blog.service.link;

import cn.edu.pzhu.blog.dao.link.model.FriendLink;

import java.util.List;

/**
 * @author:CG
 * @date:2019/05/08 21:08
 */
public interface FriendLinkService {

    /**
     * 通过 id 删除.
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 添加或修改.
     * @param friendLink
     */
    void addOrUpdate(FriendLink friendLink);

    /**
     * 获取友链列表.
     * @param uId
     * @return
     */
    List<FriendLink> queryFriendLink(Integer uId);
}
