package cn.dbdj1201.itravel.web.servlet;

import cn.dbdj1201.itravel.domain.Category;
import cn.dbdj1201.itravel.service.CategoryService;
import cn.dbdj1201.itravel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tyz1201
 * @datetime 2020-02-24 11:27
 **/
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service = new CategoryServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        System.out.println("/findAll-->" + service.findAll());

        new ObjectMapper().writeValue(response.getWriter(), service.findAll());
    }

}
