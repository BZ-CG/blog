package cn.edu.pzhu.blog.service.category;

import cn.edu.pzhu.blog.dao.category.model.Relation;

import java.util.List;

/**
 * 关系表service.
 * @author:CG
 * @date:2019/04/25 22:47
 */
public interface RelationService {

    /**
     * 批量添加关系.
     * @param relationList
     */
    void batchAddRelation(List<Relation> relationList);
}
