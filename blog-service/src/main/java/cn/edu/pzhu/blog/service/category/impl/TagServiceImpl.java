package cn.edu.pzhu.blog.service.category.impl;

import cn.edu.pzhu.base.common.RelationItem;
import cn.edu.pzhu.base.util.DateUtils;
import cn.edu.pzhu.base.util.ExceptionUtils;
import cn.edu.pzhu.base.util.StyleUtils;
import cn.edu.pzhu.blog.dao.category.TagDAO;
import cn.edu.pzhu.blog.dao.category.model.Relation;
import cn.edu.pzhu.blog.dao.category.model.Tag;
import cn.edu.pzhu.blog.service.category.RelationService;
import cn.edu.pzhu.blog.service.category.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.naming.Name;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:CG
 * @date:2019/04/21 21:10
 */
@Service
@Slf4j
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDAO tagDAO;

    @Autowired
    private RelationService relationService;

    @Override
    public Tag getById(Integer id) {
        Tag tag;
        try {
            tag = tagDAO.getById(id);
        } catch (Exception e) {
            log.error("调用 tagDAO.getById 获取标签信息失败.", e);
            throw ExceptionUtils.buildBusinessException();
        }
        return tag;
    }

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
    @Transactional(rollbackFor = Exception.class)
    public void deleteTag(Integer id) {
        try {
            List<Relation> relations = relationService.getByItemAndType(id, RelationItem.TAG.getCode());
            List<Integer> relationIds = relations.stream().map(Relation::getId).collect(Collectors.toList());

            if (!CollectionUtils.isEmpty(relationIds)) {
                relationService.deleteByIds(relationIds);
            }
            tagDAO.deleteTag(id);
        } catch (Exception e) {
            log.error("调用 tagDAO.deleteTag 删除标签信息失败.", e);
            throw ExceptionUtils.buildBusinessException();
        }
    }

    @Override
    public Integer addTag(Integer uId, String tagName) {
        Tag tag = new Tag();
        try {
            tag.setUId(uId);
            tag.setName(tagName);
            tag.setCreateDate(DateUtils.getCurrentDateStr());
            tag.setModifyDate(DateUtils.getCurrentDateStr());
            tag.setStyle(StyleUtils.getRandStyleClass());

            tagDAO.addTag(tag);
        } catch (Exception e) {
            log.error("调用 tagDAO.addTag 新增标签信息失败.", e);
            throw ExceptionUtils.buildBusinessException();
        }
        return tag.getId();
    }
}
