package cn.dbdj1201.itravel.dao;

import cn.dbdj1201.itravel.domain.Route;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:38
 **/
public interface RouteDao {
    /**
     * 根据分类查找路线。
     *
     * @return
     */
    List<Route> findByPage(int cid, int start, int pageSize);

    int findTotalCount(int cid);
}
