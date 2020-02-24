package cn.dbdj1201.itravel.dao;

import cn.dbdj1201.itravel.domain.Favorite;
import cn.dbdj1201.itravel.domain.Route;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-25 2:02
 **/
public interface FavoriteDao {
    /**
     * 根据用户id 跟路线id确定收藏情况
     *
     * @param rid
     * @param uid
     * @return
     */
    Favorite findByRidAndUid(int rid, int uid);

    /**
     * 根据路线编号从收藏表中查找该路线被收藏次数
     *
     * @param rid 路线编号
     * @return
     */
    int findCountByRid(int rid);

    /**
     * @param rid
     * @param uid
     */
    void add(int rid, int uid);

    /**
     * 查找当前用户收藏的路线
     *
     * @return
     */
    List<Integer> findRidByUid(int uid);

}
