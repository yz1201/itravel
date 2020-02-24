package cn.dbdj1201.itravel.dao.impl;

import cn.dbdj1201.itravel.dao.FavoriteDao;
import cn.dbdj1201.itravel.domain.Favorite;
import cn.dbdj1201.itravel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-25 2:04
 **/
public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * @param rid 路线编号
     * @param uid 用户编号
     * @return 返回对应的收藏信息
     */
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        String sql = "select * from tab_favorite where rid = ? and uid = ?";
        Favorite favorite;
        try {
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return favorite;
    }

    /**
     * @param rid 路线编号
     * @return
     */
    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        Integer count;
        try {
            count = template.queryForObject(sql, Integer.class, rid);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return -1;
        }
        if (count != null)
            return count;
        return -1;
    }

    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values (?, ?, ?);";
        template.update(sql, rid, new Date(), uid);
    }

    @Override
    public List<Integer> findRidByUid(int uid) {
        String sql = "select rid from tab_favorite where uid = ?";
        List<Integer> rids;
        try {
            rids = template.queryForList(sql, Integer.class, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return rids;
    }
}
