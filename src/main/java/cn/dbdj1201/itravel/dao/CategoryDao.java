package cn.dbdj1201.itravel.dao;

import cn.dbdj1201.itravel.domain.Category;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-24 11:42
 * 分类列表
 **/
public interface CategoryDao {
    List<Category> findAll();
}
