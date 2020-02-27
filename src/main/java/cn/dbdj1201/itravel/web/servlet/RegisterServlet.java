package cn.dbdj1201.itravel.web.servlet;

import cn.dbdj1201.itravel.domain.ResultInfo;
import cn.dbdj1201.itravel.domain.User;
import cn.dbdj1201.itravel.service.UserService;
import cn.dbdj1201.itravel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-02-22 23:23
 * 用户注册类
 **/
@WebServlet("/registerUser")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        System.out.println("hello world");

        //验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误,为啥是session中的判断空值？
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }


        UserService registerService = new UserServiceImpl();
        User registerUser = new User();

//        System.out.println("register servlet--->" + request.getParameterMap());

        for (String key : request.getParameterMap().keySet()) {
            System.out.println("register-->" + key + "--->" + Arrays.toString(request.getParameterMap().get(key)));
        }

        try {
            BeanUtils.populate(registerUser, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        boolean registerFlag = registerService.register(registerUser);
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo info = new ResultInfo();

        if (!registerFlag) {                //判断是否已经有该用户名
            info.setFlag(false);            //如果有了，result info置false
            info.setErrorMsg("注册失败");
        } else {
            info.setFlag(true);
        }

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(), info);
//        new ObjectMapper().readValue(request.get)


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
