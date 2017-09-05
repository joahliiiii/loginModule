package cn.joah.login.web.servlet;

import cn.joah.login.entity.Captche;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "captcheServlet",urlPatterns = "/cn/joah/login/web/servlet/captcheServlet")
public class captcheServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Captche captche=new Captche();
        BufferedImage image = captche.getImage(4, 5, 0.07F);
        // 把验证码的文本保存到Session里面
        request.getSession().setAttribute("captche",captche.stringBuffer.toString());
        // 吧图片缓冲输出到 response 输出流中
        captche.out(image,response.getOutputStream());
    }
}
