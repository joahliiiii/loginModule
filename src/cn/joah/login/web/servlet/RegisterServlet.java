package cn.joah.login.web.servlet;

import cn.joah.login.commons.reflect.ReflectUtils;
import cn.joah.login.entity.User;
import cn.joah.login.service.UserException;
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


@WebServlet(name = "RegisterServlet", urlPatterns = "/cn/joah/login/web/servlet/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 先设置编码问题
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 先校验验证码
        // 得到param参数的验证码
        String captche = request.getParameter("captche");
        // 得到session里面存储的验证吗
        String sCap = (String) request.getSession().getAttribute("captche");
        UserService userService=new UserService();
        // 验证码正确
        if(userService.checkCaptche(captche,sCap)){

            Map<String, String[]> parameterMap = request.getParameterMap();

            try {
                User user = ReflectUtils.toBean(parameterMap, User.class);

                try {
                    // 注册
                    userService.register(user);
                    // 注册成功之后显示注册成功的信息, 并转发到登录界面
                    response.getWriter().write(" 注册成功! ");
//            response.sendRedirect("/jsps/login.jsp");
                } catch (UserException e) {
                    request.setAttribute("registPageErr",e.getMessage());

                    // 把表单数据回显
                    request.setAttribute("User",user);

                    request.getRequestDispatcher("/jsps/regist.jsp").forward(request,response);
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException | IntrospectionException e) {
                e.printStackTrace();
            }


        }else{// 如果验证码错误
            request.setAttribute("registPageErr","验证码错误!");
            request.getRequestDispatcher("/jsps/regist.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
    }
}
