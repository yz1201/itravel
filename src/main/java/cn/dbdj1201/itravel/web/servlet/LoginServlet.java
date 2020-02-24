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

/**
 * @author tyz1201
 * @datetime 2020-02-23 1:23
 **/
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, request.getParameterMap());//获取用户登录信息
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

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


        UserService loginService = new UserServiceImpl();
        User user = loginService.login(loginUser);
        ResultInfo info = new ResultInfo();


        if (user == null) {
            info.setFlag(false);
            info.setErrorMsg("压根没这人");
        } else {
            if (!"Y".equals(user.getStatus())) {
                info.setFlag(false);
                info.setErrorMsg("还没激活，咋回事？");
            } else {
                request.getSession().setAttribute("user", user);
                info.setFlag(true);
            }
        }

        //响应数据
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(), info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
