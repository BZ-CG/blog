package cn.edu.pzhu.blog.service.category.impl;

import cn.edu.pzhu.blog.dao.category.RelationDAO;
import cn.edu.pzhu.blog.dao.category.model.Relation;
import cn.edu.pzhu.blog.service.category.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RelationService 实现.
 * @author:CG
 * @date:2019/04/25 23:05
 */
@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationDAO relationDAO;


    @Override
    public void batchAddRelation(List<Relation> relationList) {
        relationDAO.batchAddRelation(relationList);
    }
}
