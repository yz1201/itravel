package cn.dbdj1201.itravel.dao;

import cn.dbdj1201.itravel.domain.Seller;

/**
 * @author tyz1201
 * @datetime 2020-02-25 0:59
 **/
public interface SellerDao {

    /**
     * @param sid 商家编号
     * @return 商家信息
     */
    Seller findBySid(int sid);
}
