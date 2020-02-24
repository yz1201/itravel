package cn.dbdj1201.itravel.dao;

import cn.dbdj1201.itravel.domain.Route;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:38
 **/
public interface RouteDao {
    /**
     * @param cid      所属分类
     * @param start    当前页开始记录数
     * @param pageSize 当前页码记录数
     * @param rname    路线名称
     * @return 符合条件的路线集合
     */
    List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * @param cid   所属分类
     * @param rname 路线名称
     * @return 符合条件的记录数
     */
    int findTotalCount(int cid, String rname);

    /**
     * @param rid 路线id
     * @return 该id的路线信息
     */
    Route findByRid(int rid);

    /**
     * @param start
     * @return
     */
    List<Route> findPageForFavorite(int start);
}
