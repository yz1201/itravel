package cn.dbdj1201.itravel.service.impl;

import cn.dbdj1201.itravel.dao.UserDao;
import cn.dbdj1201.itravel.dao.impl.UserDaoImpl;
import cn.dbdj1201.itravel.domain.User;
import cn.dbdj1201.itravel.service.UserService;
import cn.dbdj1201.itravel.util.MailUtils;
import cn.dbdj1201.itravel.util.UuidUtil;

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

            //2.保存用户信息
            //2.1设置激活码，唯一字符串
            user.setCode(UuidUtil.getUuid());
            //2.2设置激活状态
            user.setStatus("N");
            dao.save(user);

            //3.激活邮件发送，邮件正文？

            String content = "<a href='http://localhost/itravel/activeUserServlet?code=" + user.getCode()
                    + "'>点击激活【黑马旅游网】</a>";
            MailUtils.sendMail(user.getEmail(), content, "激活邮件");
//            dao.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean active(String code) {
        User user = dao.findByCode(code);
        if (user != null) {
            dao.updateStatus(user);
            return true;
        }
        return false;
    }

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

}
