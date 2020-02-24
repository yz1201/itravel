package cn.dbdj1201.itravel.dao.impl;

import cn.dbdj1201.itravel.dao.RouteImageDao;
import cn.dbdj1201.itravel.domain.RouteImage;
import cn.dbdj1201.itravel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-25 0:56
 **/
public class RouteImageDaoImpl implements RouteImageDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * @param rid 所属路线
     * @return
     */
    @Override
    public List<RouteImage> findByRid(int rid) {
        String sql = "select * from tab_route_img where rid = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(RouteImage.class), rid);
    }
}
