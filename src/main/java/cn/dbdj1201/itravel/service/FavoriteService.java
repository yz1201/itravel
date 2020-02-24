package cn.dbdj1201.itravel.service;


/**
 * @author tyz1201
 * @datetime 2020-02-25 2:00
 **/
public interface FavoriteService {
    /**
     * @param rid 路线编号
     * @param uid 用户编号
     * @return 是否被收藏
     */
    boolean isFavorite(int rid, int uid);

    /**
     * @param rid
     * @param uid
     */
    void add(int rid, int uid);
}
