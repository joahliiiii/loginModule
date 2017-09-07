package cn.joah.login.web.servlet;

import cn.joah.login.commons.jdbc.Utils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


@WebServlet(name = "registerServlet", urlPatterns = "/cn/joah/login/web/servlet/registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().write("hello");

        // 先设置编码问题
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 先校验验证码
        // 得到param参数的验证码
        String captche = request.getParameter("captche");
        // 得到session里面存储的验证吗
        String sCap = (String) request.getSession().getAttribute("captche");
        // 如果param-captche 的值不为空,那么就开始校验
        if(captche!=null){
            // 如果相同 就吧数据存储到数据库内 ,重定向到登录页面
            if(sCap.equalsIgnoreCase(captche)){
                response.getWriter().write("hello data will transfer to database!");
                /**
                 * 需要新增加邮箱和profile的非法字符检测.
                 * 先验证看看这里获取的参数是否全部正确,然后在进行下面的操作.
                 *
                 * 在这里需要吧密码先进行hash计算在吧所有数据存入数据库
                 */

                    String uName =request.getParameter("username");
                    String pass = request.getParameter("password");
                    String sex = request.getParameter("sex");
                    String email = request.getParameter("email");
                    String website = request.getParameter("website");
                    String profile = request.getParameter("profile");
                    response.getWriter().write("\n"+"uName: "+uName+" pass: "+pass +" sex: "+sex+" email: "+email+" website:"+website
                    +" profile: "+profile);
                    String sql="insert into user(username,password,sex,email,website,profile) values(?,?,?,?,?,?)";
//                    System.out.println("before connection!");
//                    Utils utils=new Utils();
                    Connection connection = Utils.getConnection();

                   /* System.out.println("connection: "+connection);
                    response.getWriter().write("\n"+"connection: "+connection);*/
                    Utils.updateInfo(connection,sql,uName, pass,sex,email,website,profile);
//                    Utils.release(connection,null,null);
//                    System.out.println("insert over!");

                return;
            }else{// 如果不相同的话就转发到注册页面
                request.getRequestDispatcher("/jsps/regist.jsp").forward(request,response);
//            return;
            }
        }
        request.getRequestDispatcher("/jsps/regist.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
    }
}
