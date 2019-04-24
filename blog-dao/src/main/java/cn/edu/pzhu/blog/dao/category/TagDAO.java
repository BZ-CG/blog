package cn.edu.pzhu.blog.dao.category;

import cn.edu.pzhu.blog.dao.category.model.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 标签接口.
 * @author:CG
 * @date:2019/04/21 21:00
 */
@Component
public interface TagDAO {

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
     * @param tag 带新增的 tag 对象
     * @return 新增后的 id
     */
    Integer addTag(Tag tag);
}
