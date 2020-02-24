package cn.dbdj1201.itravel.dao.impl;

import cn.dbdj1201.itravel.dao.SellerDao;
import cn.dbdj1201.itravel.domain.Seller;
import cn.dbdj1201.itravel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author tyz1201
 * @datetime 2020-02-25 1:01
 **/
public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * @param sid 商家编号
     * @return
     */
    @Override
    public Seller findBySid(int sid) {
        String sql = "select * from tab_seller where sid = ?";
        Seller seller;
        try {
            seller = template.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return seller;
    }
}
