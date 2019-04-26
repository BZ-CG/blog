package cn.edu.pzhu.blog.dao.category;

import cn.edu.pzhu.blog.dao.category.model.Relation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 关系表DAO.
 * @author:CG
 * @date:2019/04/25 22:51
 */
@Component
public interface RelationDAO {

    /**
     * 批量添加关系.
     * @param relations
     */
    void batchAddRelation(@Param("relations") List<Relation> relations);
}
