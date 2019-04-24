package cn.edu.pzhu.blog.web.admin;

import cn.edu.pzhu.base.response.ApiResponse;
import cn.edu.pzhu.blog.dao.category.model.Category;
import cn.edu.pzhu.blog.service.category.CategoryService;
import cn.edu.pzhu.blog.service.category.TagService;
import cn.edu.pzhu.blog.service.category.impl.TagServiceImpl;
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
            if (categoryService.getCategoryByName(name) != null) {
                return ApiResponse.error("该分组已存在.");
            }
            categoryService.addCategory(userId, name);
        } catch (Exception e) {
            log.error("CategoryController.addCategory 添加分类失败。", e);
            return ApiResponse.error("添加分类失败，请联系管理员.");
        }
        return ApiResponse.success();
    }
}
