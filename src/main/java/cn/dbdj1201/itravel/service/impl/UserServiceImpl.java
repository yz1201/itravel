package cn.dbdj1201.itravel.service.impl;

import cn.dbdj1201.itravel.dao.UserDao;
import cn.dbdj1201.itravel.dao.impl.UserDaoImpl;
import cn.dbdj1201.itravel.domain.User;
import cn.dbdj1201.itravel.service.UserService;

/**
 * @author tyz1201
 * @datetime 2020-02-22 22:42
 **/
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    /**
     * 用户注册，若已存在该用户，返回false，不存在则保存该用户，并返回true
     *
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        User registerUser = dao.findByUsername(user.getUsername());
        if (registerUser == null) {
            int rows = dao.save(user);
            if (rows != 1) {
                System.out.println("save fail");
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean active(String code) {
        return false;
    }

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

}
