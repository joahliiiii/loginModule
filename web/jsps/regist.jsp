<%--
  Created by IntelliJ IDEA.
  User: joah
  Date: 17-9-4
  Time: 上午9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>regist</title>
    <script>var contextPath="${pageContext.request.contextPath}";</script>
    <script src="${pageContext.request.contextPath}/javascripts/registPageJS.js" ></script>

</head>
<body>
    <h1>Join us</h1>
    <p>The best way to design, build, and ship software.</p>
    <h2>Create your personal account.</h2>
    <form action="${pageContext.request.contextPath}/cn/joah/login/web/servlet/registerServlet" method="post">
        username: <br><input type="text" name="username" id="username" onblur="checkUname()"><span id="userInfo" ></span><br>
        password: <br><input type="password" name="password" id="password" onblur="checkPass()"><span id="passInfo"></span><br>
        Confirmed password: <br><input type="password" name="confirmPass" id="conPassword" onblur="checkPasss()"><span id="conpassInfo"></span><br>
        sex: <input type="radio" name="sex" value="male">male
        <input type="radio" name="sex" value="female">female<br>
        email: <br><input type="text" name="email"><br>
        website: <br><input type="text" name="website" ><br>
        profile: <br><textarea name="profile" style="width: 334px; height: 42px;"></textarea> <br>
        captche: <input type="text" name="captche" style="width: 185px" id="IcaptcheI"/>
        <a href="javascript:captcheChange()"><img id="ICaptche" src="${pageContext.request.contextPath}/cn/joah/login/web/servlet/captcheServlet"></a><br>
        <%--<span id="capInfo"></span>--%>
        <button type="submit" onclick="return check()"  >submit</button>
    </form>
</body>
</html>
