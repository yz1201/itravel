package cn.dbdj1201.itravel.service;

import cn.dbdj1201.itravel.domain.PageBean;
import cn.dbdj1201.itravel.domain.Route;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:39
 **/
public interface RouteService {
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize);
}
