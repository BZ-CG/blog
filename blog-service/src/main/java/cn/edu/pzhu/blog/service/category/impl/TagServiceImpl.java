package cn.edu.pzhu.blog.service.category.impl;

import cn.edu.pzhu.base.util.ExceptionUtils;
import cn.edu.pzhu.blog.dao.category.TagDAO;
import cn.edu.pzhu.blog.dao.category.model.Tag;
import cn.edu.pzhu.blog.service.category.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:CG
 * @date:2019/04/21 21:10
 */
@Service
@Slf4j
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDAO tagDAO;

    @Override
    public Tag findTagByName(String name) {
        Tag tag;
        try {
            tag = tagDAO.findTagByName(name);
        } catch (Exception e) {
            log.error("调用 tagDAO.findTagByName 获取标签信息失败.", e);
            throw ExceptionUtils.buildBusinessException();
        }
        return tag;
    }

    @Override
    public List<Tag> getTagByAid(Integer uId, Integer aId) {
        List<Tag> list;
        try {
            list = tagDAO.getTagByAid(uId, aId);
        } catch (Exception e) {
            log.error("调用 tagDAO.getTagByAid 获取标签信息失败.", e);
            throw ExceptionUtils.buildBusinessException();
        }
        return list;
    }

    @Override
    public void deleteTag(Integer id) {
        try {
          tagDAO.deleteTag(id);
        } catch (Exception e) {
            log.error("调用 tagDAO.deleteTag 删除标签信息失败.", e);
            throw ExceptionUtils.buildBusinessException();
        }
    }

    @Override
    public Integer addTag(Tag tag) {
        try {
            tagDAO.addTag(tag);
        } catch (Exception e) {
            log.error("调用 tagDAO.addTag 新增标签信息失败.", e);
            throw ExceptionUtils.buildBusinessException();
        }
        return tag.getId();
    }
}
