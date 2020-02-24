package cn.dbdj1201.itravel.service.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-02-24 12:28
 **/
public class CategoryServiceImplTest {

    @Test
    public void findAll() {
        CategoryServiceImpl service = new CategoryServiceImpl();
        System.out.println(service.findAll());
    }
}