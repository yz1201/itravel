package cn.dbdj1201.itravel.web.servlet;

import cn.dbdj1201.itravel.domain.PageBean;
import cn.dbdj1201.itravel.domain.Route;
import cn.dbdj1201.itravel.service.RouteService;
import cn.dbdj1201.itravel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-24 14:36
 **/
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();

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
        PageBean<Route> pageBean;
        //获取来自前台的数据并封装。
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int cid = Integer.parseInt(request.getParameter("cid"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        //查询当前页面的数据，封装到pb中。
        pageBean = service.pageQuery(cid, currentPage, pageSize);
        response.setContentType("application/json;charset=utf-8");
        //响应数据。
        new ObjectMapper().writeValue(response.getWriter(), pageBean);
    }

}
