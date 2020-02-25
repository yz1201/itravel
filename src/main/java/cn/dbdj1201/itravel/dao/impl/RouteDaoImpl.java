package cn.dbdj1201.itravel.dao.impl;

import cn.dbdj1201.itravel.dao.RouteDao;
import cn.dbdj1201.itravel.domain.Route;
import cn.dbdj1201.itravel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:38
 **/
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        String sql = "select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();

        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }

        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ? ");
            params.add("%" + rname + "%");
        }
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);
        return template.query(sb.toString(),
                new BeanPropertyRowMapper<>(Route.class), params.toArray());

    }

    @Override
    public int findTotalCount(int cid, String rname) {
        String sql = "select  count(*) from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);

        List<Object> params = new ArrayList<>();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }

        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ? ");
            params.add("%" + rname + "%");
        }

        Integer totalCount = template.queryForObject(sb.toString(), Integer.class, params.toArray());
        if (totalCount != null) {
            return totalCount;
        }
        return -1;
    }

    @Override
    public Route findByRid(int rid) {
        String sql = "select * from tab_route where rid = ?";
        Route route;
        try {
            route = template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return route;
    }

//    @Override
//    public List<Route> findPageForFavorite(List<Integer> rids) {
//        String sql = "select * from tab_route where 1 = 1 ";
//        int size = rids.size();
//        StringBuilder sb = new StringBuilder(sql);
//        for (int i = 0; i <size ; i++) {
//            sb.append(" and rid")
//        }
//
//        return null;
//    }
}
