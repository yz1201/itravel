package cn.dbdj1201.itravel.dao;

import cn.dbdj1201.itravel.domain.User;

/**
 * @author tyz1201
 * @datetime 2020-02-22 22:42
 **/
public interface UserDao {

    /**
     * @param username 注册时根据用户名查找用户
     * @return 若存在返回该用户信息。
     */
    User findByUsername(String username);

    /**
     * @param user 保存用户信息
     * @return 返回影响行数，由此判断是否保存成功，这边告知用户。
     */
    int save(User user);

    /**
     * 根据用户名密码从数据库查找并返回该用户信息。
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);


}
