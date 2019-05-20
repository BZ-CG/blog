package cn.edu.pzhu.blog.service.category;

import cn.edu.pzhu.blog.dao.category.model.Category;

import java.util.List;

/**
 * CategoryService.
 * @author:CG
 * @date:2019/04/21 8:46
 */
public interface CategoryService {

    /**
     * 获取分类章数量.
     * @param uId
     * @return
     */
    Integer getCount(Integer uId);

    /**
     * 根据 id 删除.
     * @param uId
     * @param id
     */
    void deleteById(Integer uId, Integer id);

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
     *
     * @param uId
     * @param name 分类名
     * @return 分类
     */
    Category getCategoryByName(Integer uId, String name);

    /**
     * 新增一个分组信息.
     *
     * @param userId 用户id
     * @param name 分类名
     * @return id
     */
    Integer addCategory(Integer userId, String name);

    /**
     * 按照id 修改分组.
     * @param category
     */
    void updateCategory(Category category);
}
