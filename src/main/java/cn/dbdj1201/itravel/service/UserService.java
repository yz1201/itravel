package cn.dbdj1201.itravel.service;

import cn.dbdj1201.itravel.domain.User;

/**
 * @author tyz1201
 * @datetime 2020-02-22 22:42
 **/
public interface UserService {

    boolean register(User user);

    boolean active(String code);

    /**
     * 查找并返回用户信息。
     * @param user
     * @return user
     */
    User login(User user);
}
