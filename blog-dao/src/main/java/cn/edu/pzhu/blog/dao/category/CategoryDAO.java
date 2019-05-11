package cn.edu.pzhu.blog.dao.category;

import cn.edu.pzhu.blog.dao.category.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分类 DAO.
 * @author:CG
 * @date:2019/04/21 0:16
 */
@Repository
public interface CategoryDAO {


    /**
     * 通过id获取分类信息.
     * @param id
     * @return
     */
    Category getCategoryById(Integer id);

    /**
     * 获取 user 下所有的分类.
     * @param uId
     * @return 分类列表
     */
    List<Category> getAllCategoryByUid(Integer uId);

    /**
     * 获取所有的分类信息.
     * @return  分类列表
     */
    List<Category> getAll();

    /**
     * 根据分类名查询.
     * @param name 分类名
     * @return 分类
     */
    Category getCategoryByName(String name);

    /**
     * 新增一个分组信息.
     * @param category 分组
     * @return id
     */
    Integer addCategory(Category category);

    /**
     * 按照id 修改分组.
     * @param category
     */
    void updateCategory(Category category);
}
