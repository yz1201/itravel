package cn.dbdj1201.itravel.service.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author tyz1201
 * @datetime 2020-02-24 15:20
 **/
public class RouteServiceImplTest {

    @Test
    public void pageQuery() {
//        RouteServiceImpl service = new RouteServiceImpl();
//        for (int i = 1; i <= 10; i++) {
//            System.out.println("page-->" + i);
//            service.pageQuery(5, i, 10).getList().forEach(System.out::println);
//        }
    }

    @Test
    public void findOne() {
        RouteServiceImpl service = new RouteServiceImpl();
        System.out.println(service.findOne(1));
    }

}