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
     * 新增友链.
     * @param friendLink
     */
    void add(FriendLink friendLink);

    /**
     * 通过 id 删除.
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 通过 id 修改.
     * @param friendLink
     */
    void updateById(FriendLink friendLink);

    /**
     * 获取友链列表.
     * @param uId
     * @return
     */
    List<FriendLink> queryFriendLink(Integer uId);
}
