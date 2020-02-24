package cn.dbdj1201.itravel.service.impl;

import cn.dbdj1201.itravel.dao.RouteDao;
import cn.dbdj1201.itravel.dao.impl.RouteDaoImpl;
import cn.dbdj1201.itravel.domain.PageBean;
import cn.dbdj1201.itravel.domain.Route;
import cn.dbdj1201.itravel.service.RouteService;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:39
 **/
public class RouteServiceImpl implements RouteService {
    private RouteDao dao = new RouteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize) {

        List<Route> pageList;
        int size = dao.findTotalCount(cid);
        PageBean<Route> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(size);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(size % pageSize == 0 ? size / pageSize : (size / pageSize + 1));
        pageList = dao.findByPage(cid, (currentPage - 1) * pageSize, pageSize);
        pageBean.setList(pageList);
        return pageBean;
    }
}
