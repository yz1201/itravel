package cn.dbdj1201.itravel.service.impl;

import cn.dbdj1201.itravel.dao.impl.FavoriteDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-02-25 16:03
 **/
public class FavoriteServiceImplTest {

    @Test
    public void isFavorite() {
    }

    @Test
    public void add() {
        FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();
        for (int i = 1; i < 100; i++) {
            favoriteDao.add(i, 1);
        }
    }
}