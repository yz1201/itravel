package cn.dbdj1201.itravel.web.servlet;

import cn.dbdj1201.itravel.domain.PageBean;
import cn.dbdj1201.itravel.domain.ResultInfo;
import cn.dbdj1201.itravel.domain.Route;
import cn.dbdj1201.itravel.domain.User;
import cn.dbdj1201.itravel.service.FavoriteService;
import cn.dbdj1201.itravel.service.RouteService;
import cn.dbdj1201.itravel.service.impl.FavoriteServiceImpl;
import cn.dbdj1201.itravel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:36
 **/
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 根据分类id查询当前页码的数据
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        //接受rname 线路名称
        String rname = request.getParameter("rname");
        if (rname != null)
            rname = new String(rname.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        System.out.println("rname = " + rname);

        int cid = 0;//类别id
        //2.处理参数
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示5条记录
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }

        //3. 调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);

        //4. 将pageBean对象序列化为json，返回
        writeValue(pb, response);
    }

    /**
     * 查询详情页
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        writeValue(routeService.findOne(Integer.parseInt(Objects.requireNonNull(request.getParameter("rid")))), response);
    }

    /**
     * 判断当前路线是否有用户收藏
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int rid = Integer.parseInt(request.getParameter("rid"));     //封装前台发来的数据
        User user = (User) request.getSession().getAttribute("user");
        int uid = 0;
        if (user != null)               //如果用户在线则获取用户的uid，否则置0
            uid = user.getUid();

        ResultInfo info = new ResultInfo();  //设置响应消息
        info.setFlag(favoriteService.isFavorite(rid, uid));
        writeValue(info, response);
    }

    /**
     * 处理收藏按钮点击事件
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int rid = Integer.parseInt(Objects.requireNonNull(request.getParameter("rid")));
        User user = (User) request.getSession().getAttribute("user");
        favoriteService.add(rid, user.getUid());
    }

    /**
     * 我的收藏pb
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void queryPageForMyFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接受参数
        String currentPageStr = request.getParameter("currentPage");

        int currentPage;//当前页码，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int uid = ((User) request.getSession().getAttribute("user")).getUid();//用户id


        //3. 调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQueryForFavorite(uid, currentPage);

        //4. 将pageBean对象序列化为json，返回
        writeValue(pb, response);

    }

}
