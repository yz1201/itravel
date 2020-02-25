package cn.dbdj1201.itravel.service.impl;

import cn.dbdj1201.itravel.dao.FavoriteDao;
import cn.dbdj1201.itravel.dao.impl.FavoriteDaoImpl;
import cn.dbdj1201.itravel.service.FavoriteService;

/**
 * @author tyz1201
 * @datetime 2020-02-25 2:03
 **/
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao dao = new FavoriteDaoImpl();

    /**
     * @param rid 路线编号
     * @param uid 用户编号
     * @return 有人收藏该路线为1，否则为0;
     */
    @Override
    public boolean isFavorite(int rid, int uid) {
        return dao.findByRidAndUid(rid, uid) != null;
    }



    @Override
    public void add(int rid, int uid) {
        dao.add(rid, uid);
    }
}
