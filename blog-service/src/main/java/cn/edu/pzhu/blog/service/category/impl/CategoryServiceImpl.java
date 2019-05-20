package cn.edu.pzhu.blog.service.category.impl;

import cn.edu.pzhu.base.common.ErrorCodeConstants;
import cn.edu.pzhu.base.common.RelationItem;
import cn.edu.pzhu.base.exception.BusinessException;
import cn.edu.pzhu.base.util.ExceptionUtils;
import cn.edu.pzhu.base.util.StyleUtils;
import cn.edu.pzhu.blog.dao.category.CategoryDAO;
import cn.edu.pzhu.blog.dao.category.model.Category;
import cn.edu.pzhu.blog.service.category.CategoryService;
import cn.edu.pzhu.blog.service.category.RelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author:CG
 * @date:2019/04/21 8:48
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private RelationService relationService;

    @Override
    public Integer getCount(Integer uId) {
        try {
            return categoryDAO.getCount(uId);
        } catch (Exception e) {
            log.error("调用 categoryDAO.getCount 获取分类数量异常", e);
            throw ExceptionUtils.buildBusinessException();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer uId, Integer id) {
        try {
            relationService.deleteByItemId(uId, id, RelationItem.CATEGORY.getCode());
            categoryDAO.deleteById(id);
        } catch (Exception e) {
            log.error("调用 CategoryService.deleteById 删除分类异常", e);
            throw buildBusinessException();
        }
    }

    @Override
    public Category getCategoryById(Integer id) {
        Category category;
        try {
            category = categoryDAO.getCategoryById(id);
        } catch (Exception e) {
            log.error("调用 categoryDAO.getCategoryById 获取分类信息异常", e);
            throw buildBusinessException();
        }
        return category;
    }

    @Override
    public List<Category> getAllCategoryByUid(Integer uId) {
        List<Category> list;
        try {
            list = categoryDAO.getAllCategoryByUid(uId);
        } catch (Exception e) {
            log.error("调用 categoryDAO.getAllCategoryByUid 获取分类信息异常", e);
            throw buildBusinessException();
        }
        return list;
    }

    @Override
    public List<Category> getAll() {
        List<Category> list;
        try {
            list = categoryDAO.getAll();
        } catch (Exception e) {
            log.error("调用 categoryDAO.getAll 获取分类信息异常", e);
            throw buildBusinessException();
        }
        return list;
    }

    @Override
    public Category getCategoryByName(Integer uId, String name) {
        Category category;
        try {
            category = categoryDAO.getCategoryByName(uId, name);
        } catch (Exception e) {
            log.error("调用 categoryDAO.getCategoryByName 获取分类信息异常", e);
            throw buildBusinessException();
        }
        return category;
    }

    @Override
    public Integer addCategory(Integer userId, String name) {
        Category category = new Category();

        try {
            category.setName(name);
            category.setUserId(userId);
            category.setStyle(StyleUtils.getRandStyleClass());
            category.setCreateDate(getCurrentDateStr());
            category.setModifyDate(getCurrentDateStr());

            categoryDAO.addCategory(category);
        } catch (Exception e) {
            log.error("调用 categoryDAO.addCategory 添加分类信息异常", e);
            throw buildBusinessException();
        }
        return category.getId();
    }

    @Override
    public void updateCategory(Category category) {
        try {
            category.setModifyDate(getCurrentDateStr());
            categoryDAO.updateCategory(category);
        } catch (Exception e) {
            log.error("调用 categoryDAO.updateCategory 修改分类信息异常", e);
            throw buildBusinessException();
        }
    }

    /**
     * 构建一个创建接口异常的业务异常实体.
     * @return
     */
    private BusinessException buildBusinessException() {
        return new BusinessException(ErrorCodeConstants.ERROR_CODE, ErrorCodeConstants.ERROR_MSG);
    }

    /**
     * 获取当前时间字符串.
     * @return 时间字符串
     */
    public String getCurrentDateStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

}
