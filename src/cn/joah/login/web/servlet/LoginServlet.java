package cn.joah.login.web.servlet;

import cn.joah.login.commons.reflect.ReflectUtils;
import cn.joah.login.entity.User;
import cn.joah.login.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "LoginServlet",urlPatterns = "/cn/joah/login/web/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String paraCap = request.getParameter("captche");
        String sessCap = (String) request.getSession().getAttribute("captche");
        UserService userService= new UserService();

        if(userService.checkCaptche(paraCap,sessCap)){

            Map<String, String[]> parameterMap = request.getParameterMap();
            try {
                // 吧表单参数封装成一个类
                User user = ReflectUtils.toBean(parameterMap, User.class);


                boolean status = userService.login(user.getUsername(), user.getPassword());

                if(status){
                    request.getSession().setAttribute("username",user.getUsername());
                    request.getSession().setAttribute("loginStatus","logged");

                    request.getRequestDispatcher("/jsps/success.jsp").forward(request,response);
                }else {// 密码或用户名错误
                    request.setAttribute("loginPageErr","用户名或密码错误!");
                    request.getRequestDispatcher("/jsps/login.jsp").forward(request,response);
                }

            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InstantiationException | IntrospectionException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }else{ // 验证码错误
            request.setAttribute("loginPageErr","验证码错误!");
            request.getRequestDispatcher("/jsps/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("hello this is loginSevlet");
    }
}
