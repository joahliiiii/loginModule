package cn.joah.login.commons.captche;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "CaptcheServlet",urlPatterns = "/cn/joah/login/commons/captche/CaptcheServlet")
public class CaptcheServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().write("hello this is CaptcheServlet!");

        Captche captche=new Captche();
        BufferedImage image = captche.getImage(4, 5, 0.07F);
        // 把验证码的文本保存到Session里面
        request.getSession().setAttribute("captche",captche.stringBuffer.toString());
        // 吧图片缓冲输出到 response 输出流中
        captche.out(image,response.getOutputStream());
    }
}
