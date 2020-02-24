package cn.dbdj1201.itravel.dao.impl;

import cn.dbdj1201.itravel.dao.CategoryDao;
import cn.dbdj1201.itravel.domain.Category;
import cn.dbdj1201.itravel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-24 11:43
 **/
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        return template.query("select * from tab_category order by cid", new BeanPropertyRowMapper<>(Category.class));
    }
}
