package cn.dbdj1201.itravel.service;

import cn.dbdj1201.itravel.domain.PageBean;
import cn.dbdj1201.itravel.domain.Route;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:39
 **/
public interface RouteService {
    /**
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 根据rid查找到当前路线详情
     *
     * @param rid
     * @return
     */
    Route findOne(int rid);

    /**
     * 查找当前用户的每页数据集
     *
     * @param uid
     * @param currentPage
     * @return
     */
    PageBean<Route> pageQueryForFavorite(int uid, int currentPage);
}
