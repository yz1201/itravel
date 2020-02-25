package cn.dbdj1201.itravel.service.impl;

import cn.dbdj1201.itravel.dao.FavoriteDao;
import cn.dbdj1201.itravel.dao.RouteDao;
import cn.dbdj1201.itravel.dao.RouteImageDao;
import cn.dbdj1201.itravel.dao.SellerDao;
import cn.dbdj1201.itravel.dao.impl.FavoriteDaoImpl;
import cn.dbdj1201.itravel.dao.impl.RouteDaoImpl;
import cn.dbdj1201.itravel.dao.impl.RouteImageDaoImpl;
import cn.dbdj1201.itravel.dao.impl.SellerDaoImpl;
import cn.dbdj1201.itravel.domain.PageBean;
import cn.dbdj1201.itravel.domain.Route;
import cn.dbdj1201.itravel.service.RouteService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:39
 **/
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImageDao imageDao = new RouteImageDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    /**
     * @param cid         //所属分类
     * @param currentPage //当前页码
     * @param pageSize    //每页记录数
     * @param rname       //路线名
     * @return //查找的所需的当前页码实体类
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {

        List<Route> pageList;
        int size = routeDao.findTotalCount(cid, rname);      //获取总记录数
        PageBean<Route> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);                 //设置每页记录数
        pageBean.setTotalCount(size);
        pageBean.setCurrentPage(currentPage);           //记录当前页码
        pageBean.setTotalPage(size % pageSize == 0 ? size / pageSize : (size / pageSize + 1)); //设置页码数
        pageList = routeDao.findByPage(cid, (currentPage - 1) * pageSize, pageSize, rname);    //查找当前页码的数据集
        pageBean.setList(pageList);
        return pageBean;
    }

    /**
     * @param rid 路线编号
     * @return 该编号路线
     */
    @Override
    public Route findOne(int rid) {
        Route route = routeDao.findByRid(rid);           //从route表中查找该编号路线信息
        route.setRouteImgList(imageDao.findByRid(rid));  //从image表查找对应图片集
        route.setSeller(sellerDao.findBySid(route.getSid()));       //从商家表查找商家信息
        route.setCount(favoriteDao.findCountByRid(rid)); //?路线表里明明有count数据，还去favorite表查？脑子不好？
        return route;
    }

    /**
     * 我的收藏分页查询
     *
     * @param uid
     * @param currentPage
     * @return
     */
    @Override
    public PageBean<Route> pageQueryForFavorite(int uid, int currentPage) {
        List<Integer> rids = favoriteDao.findRidByUid(uid); //用户收藏的路线编号集
        PageBean<Route> pb = new PageBean<>();
        List<Route> routeList = new ArrayList<>();
        if (rids != null) {
            int totalCount = rids.size();
            int totalPage = totalCount % 12 == 0 ? totalCount / 12 : ((totalCount / 12) + 1);
            pb.setCurrentPage(currentPage);
            pb.setTotalCount(totalCount);
            pb.setTotalPage(totalPage);
            pb.setPageSize(12);
            rids = getWordRouteIds(currentPage, rids);
            for (Integer rid : rids) {
                routeList.add(routeDao.findByRid(rid));
            }
            pb.setList(routeList);
            return pb;
        }
        return null;
    }


    /**
     * 拿到当前页码的收藏路线id集
     *
     * @param currentPage
     * @param rids
     * @return
     */
    public List<Integer> getWordRouteIds(int currentPage, List<Integer> rids) {
        int begin = (currentPage - 1) * 12;   //rids 集合开始
        int end = begin + 12; // 每次拉取12个
        int size = rids.size();
        List<Integer> workRouteRids;
        if (end > size) {
            workRouteRids = rids.subList(begin, size);
        } else {
            workRouteRids = rids.subList(begin, end);
        }
        return workRouteRids;
    }
}
