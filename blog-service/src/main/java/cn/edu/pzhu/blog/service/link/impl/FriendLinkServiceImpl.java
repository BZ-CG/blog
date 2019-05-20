package cn.edu.pzhu.blog.service.link.impl;

import cn.edu.pzhu.base.util.ExceptionUtils;
import cn.edu.pzhu.blog.dao.link.FriendLinkDAO;
import cn.edu.pzhu.blog.dao.link.model.FriendLink;
import cn.edu.pzhu.blog.service.link.FriendLinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author:CG
 * @date:2019/05/08 21:11
 */
@Service
@Slf4j
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkDAO friendLinkDAO;


    @Override
    public void deleteById(Integer id) {
        try {
            friendLinkDAO.deleteById(id);
        } catch (Exception e) {
            log.error("调用 friendLinkDAO.deleteById 删除友链信息失败.", e);
            ExceptionUtils.buildBusinessException();
        }
    }

    @Override
    public void addOrUpdate(FriendLink friendLink) {
        try {
            //修改
            if (friendLink.getId() != null) {
                friendLink.setModifyDate(new Date());
                friendLinkDAO.updateById(friendLink);
            } else {
                friendLink.setModifyDate(new Date());
                friendLink.setCreateDate(new Date());
                friendLinkDAO.add(friendLink);
            }
        } catch (Exception e) {
            log.error("调用 friendLinkDAO.addOrUpdate 新增或修改友链信息失败.", e);
            ExceptionUtils.buildBusinessException();
        }
    }

    @Override
    public List<FriendLink> queryFriendLink(Integer uId) {
        List<FriendLink> list = null;

        try {
            list = friendLinkDAO.queryFriendLink(uId);
        } catch (Exception e) {
            log.error("调用 friendLinkDAO.queryFriendLink 获取友链信息失败.", e);
            ExceptionUtils.buildBusinessException();
        }
        return list;
    }
}
