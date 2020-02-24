package cn.dbdj1201.itravel.web.servlet;

import cn.dbdj1201.itravel.domain.ResultInfo;
import cn.dbdj1201.itravel.domain.User;
import cn.dbdj1201.itravel.service.UserService;
import cn.dbdj1201.itravel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
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
 * @datetime 2020-02-24 10:31
 **/
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl(); //抽取出公共调用的service

    /**
     * 用户注册
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        //验证校验
        String check = request.getParameter("check");
        System.out.println("get from website -->" + check);
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        System.out.println("session checkcode-->" + checkcode_server);
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误,为啥是session中的判断空值？
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.getWriter().write(json);
            return;
        }

        User registerUser = new User();
        try {
            BeanUtils.populate(registerUser, request.getParameterMap()); //封装前台数据
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        boolean registerFlag = service.register(registerUser);
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo info = new ResultInfo();

        if (!registerFlag) {                //判断是否已经有该用户名
            info.setFlag(false);            //如果有了，result info置false
            info.setErrorMsg("注册失败");
        } else {
            info.setFlag(true);
        }
        mapper.writeValue(response.getWriter(), info);
    }

    /**
     * 用户登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

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
            response.getWriter().write(json);
            return;
        }

        User user = service.login(loginUser);
        ResultInfo info = new ResultInfo();
        /*
            从数据库查询用户，无此人/没激活/没问题。
         */
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
        //将用户信息转成json响应。
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), info);
    }

    /**
     * 找到当前登录用户信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user"); //从session中获取已保存的用户
        writeValue(user, response);      //回写给客户端。
    }

    /**
     * 退出登录状态
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /**
     * 用户激活
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取激活码
        String code = request.getParameter("code");
        if (code != null) {
            //2.调用service完成激活
            boolean flag = service.active(code);

            //3.判断标记
            String msg;
            if (flag) {
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            } else {
                //激活失败
                msg = "激活失败，请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

}
