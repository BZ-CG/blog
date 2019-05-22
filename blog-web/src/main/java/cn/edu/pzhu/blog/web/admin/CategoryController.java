package cn.edu.pzhu.blog.web.admin;

import cn.edu.pzhu.base.response.ApiResponse;
import cn.edu.pzhu.blog.dao.category.model.Category;
import cn.edu.pzhu.blog.service.category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author:CG
 * @date:2019/04/21 10:18
 */
@RequestMapping("/admin/category")
@Controller
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse update(Integer id, String name) {
        Integer uId = 1;
        try {
            Category categoryByName = categoryService.getCategoryByName(uId, name);
            if (categoryByName != null) {
                return ApiResponse.error("分类重复!");
            } else {
                Category category = new Category();
                category.setName(name);
                category.setId(id);

                categoryService.updateCategory(category);
                return ApiResponse.success();
            }
        } catch (Exception e) {
            log.error("CategoryController.update 修改分类失败。", e);
            return ApiResponse.error("操作失败!");
        }
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse query() {
        Integer userId = 1;
        List<Category> list;

        try {
            list = categoryService.getAllCategoryByUid(userId);
        } catch (Exception e) {
            log.error("CategoryController.query 查询分类失败。", e);
            return ApiResponse.error("");
        }
        return ApiResponse.success(list);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse addCategory(@RequestParam("name") String name) {
        Integer userId = 1;
        try {
            if (categoryService.getCategoryByName(userId, name) != null) {
                return ApiResponse.error("该分组已存在.");
            }
            categoryService.addCategory(userId, name);
        } catch (Exception e) {
            log.error("CategoryController.addCategory 添加分类失败。", e);
            return ApiResponse.error("添加分类失败，请联系管理员.");
        }
        return ApiResponse.success();
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse delete(Integer id) {
        Integer uId = 1;
        try {
            categoryService.deleteById(uId, id);
        } catch (Exception e) {
            log.error("CategoryController.delete 删除分类失败。", e);
            return ApiResponse.error("操作失败");
        }
        return ApiResponse.success();
    }
}
