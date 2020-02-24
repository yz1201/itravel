package cn.dbdj1201.itravel.dao.impl;

import cn.dbdj1201.itravel.dao.RouteDao;
import cn.dbdj1201.itravel.domain.Route;
import cn.dbdj1201.itravel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:38
 **/
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<Route> findByPage(int cid, int start, int pageSize) {

        return template.query("select * from tab_route where cid = ? limit ?, ?",
                new BeanPropertyRowMapper<>(Route.class), cid, start, pageSize);

    }

    @Override
    public int findTotalCount(int cid) {
        return template.queryForObject("select count(*) from tab_route where cid = ?",
                Integer.class, cid);
    }
}
