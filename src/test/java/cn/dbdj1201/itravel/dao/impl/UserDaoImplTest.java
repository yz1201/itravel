package cn.dbdj1201.itravel.dao.impl;

import cn.dbdj1201.itravel.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tyz1201
 * @datetime 2020-02-22 23:06
 **/
public class UserDaoImplTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByUsername() {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.findByUsername("root");
        System.out.println("user-->" + user);
    }

    @Test
    public void save() {
    }
}