package cn.dbdj1201.itravel.dao.impl;

import cn.dbdj1201.itravel.dao.UserDao;
import cn.dbdj1201.itravel.domain.User;
import cn.dbdj1201.itravel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author tyz1201
 * @datetime 2020-02-22 22:43
 **/
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     * @param username 注册时根据用户名查找用户
     * @return 根据用户名的存在情况返回响应信息，有问题就拦截返回空值。expect 1 actual？
     */
    @Override
    public User findByUsername(String username) {
        User user;
        try {
            user = template.queryForObject("select * from tab_user where username = ?",
                    new BeanPropertyRowMapper<>(User.class), username);
        } catch (DataAccessException e) {
//            e.printStackTrace();
            return null;
        }
        return user;
    }

    /**
     * @param user 保存用户信息
     * @return 返回对应影响行数。
     */
    @Override
    public int save(User user) {
        return template.update("insert into tab_user(username,password,name,birthday,sex,telephone," +
                        "email,status,code) values(?,?,?,?,?,?,?,?,?)",
                user.getUsername(), user.getPassword(), user.getName(),
                user.getBirthday(), user.getSex(), user.getTelephone(),
                user.getEmail(), user.getStatus(), user.getCode());
    }

    /**
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user;
        try {
            user = template.queryForObject("select * from tab_user where username = ? and password = ?",
                    new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }

    /**
     * 根据激活码找到用户
     *
     * @param code 激活码
     * @return 返回相应用户
     */
    @Override
    public User findByCode(String code) {
        User user;
        try {
            user = template.queryForObject("select * from tab_user where code = ?",
                    new BeanPropertyRowMapper<>(User.class), code);
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }

    /**
     * 修改指定用户的激活状态
     *
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        template.update("update tab_user set status = 'Y' where uid = ?", user.getUid());
    }

}
