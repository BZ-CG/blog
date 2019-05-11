package cn.edu.pzhu.blog.dao.category;

import cn.edu.pzhu.blog.dao.category.model.Relation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 关系表DAO.
 * @author:CG
 * @date:2019/04/25 22:51
 */
@Component
public interface RelationDAO {

    /**
     * 根据 id 批量删除.
     * @param ids
     */
    void deleteByIds(@Param(value = "ids") List<Integer> ids);

    /**
     * 通过 item id 和 枚举类型获取关系列表.
     * @param itemId
     * @param type
     * @return
     */
    List<Relation> getByItemAndType(Integer itemId, Integer type);

    /**
     * 通过分类 id 获取分类下所有文章的 id.
     * @param uId 用户id.
     * @param cId 分类 id.
     * @return
     */
    List<Integer> getAidByCid(Integer uId, Integer cId);

    /**
     * 批量添加关系.
     * @param relations
     */
    void batchAddRelation(@Param("relations") List<Relation> relations);


    /**
     * 获取文章关系.
     * @param uId
     * @param aId
     * @return
     */
    List<Relation> getRelationByAid(Integer uId, Integer aId);
}
