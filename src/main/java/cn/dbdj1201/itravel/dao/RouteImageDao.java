package cn.dbdj1201.itravel.dao;

import cn.dbdj1201.itravel.domain.RouteImage;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-25 0:54
 **/
public interface RouteImageDao {
    /**
     * @param rid 所属路线
     * @return 返回该路线的图片集
     */
    List<RouteImage> findByRid(int rid);
}
