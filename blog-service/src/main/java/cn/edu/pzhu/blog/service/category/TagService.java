package cn.edu.pzhu.blog.service.category;

import cn.edu.pzhu.blog.dao.category.model.Tag;

import java.util.List;

/**
 * 标签 service.
 * @author:CG
 * @date:2019/04/21 21:08
 */
public interface TagService {

    /**
     * 通过id获取标签信息.
     * @param id
     * @return
     */
    Tag getById(Integer id);

    /**
     * 根据标签名查找.
     * @param name 标签名
     * @return 标签对象
     */
    Tag findTagByName(String name);

    /**
     * 根据用户 id 和 文章 id 查询标签列表.
     * @param uId 用户 id
     * @param aId 文章 id
     * @return 标签列表
     */
    List<Tag> getTagByAid(Integer uId, Integer aId);

    /**
     * 根据 id 删除标签.
     * @param id 标签 id
     */
    void deleteTag(Integer id);

    /**
     * 新增 tag.
     *
     * @param uId
     * @param tagName
     * @return 新增后的 id
     */
    Integer addTag(Integer uId, String tagName);
}
