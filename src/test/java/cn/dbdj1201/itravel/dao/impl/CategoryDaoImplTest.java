package cn.dbdj1201.itravel.dao.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-02-24 12:05
 **/
public class CategoryDaoImplTest {

    @Test
    public void findAll() {
        CategoryDaoImpl dao = new CategoryDaoImpl();
        dao.findAll().forEach(System.out::println);
    }
}