<%--
  Created by IntelliJ IDEA.
  User: joah
  Date: 17-9-4
  Time: 上午9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
        <label for="username">username: </label><br><input type="text" name="username" id="username" onblur="checkUname()"><span id="userInfo" ></span><br>
        <label for="password">password: <br></label><input type="password" name="password" id="password" onblur="checkPass()"><span id="passInfo"></span><br>
        <label for="conPassword">Confirmed password: <br></label><input type="password" name="confirmPass" id="conPassword" onblur="checkPasss()"><span id="conpassInfo"></span><br>
        sex: <input type="radio" name="sex" value="male" id="male"><label for="male">male</label>
        <input type="radio" name="sex" value="female" id="female"><label for="female">female<br></label>
        <label for="email">email: <br></label><input type="text" name="email" id="email"><br>
        <label for="website">website: <br></label><input type="text" name="website" id="website"><br>
        <label for="profile">profile: <br></label><textarea name="profile" style="width: 334px; height: 42px;" id="profile"></textarea> <br>
        <%--<input type="text" name="captche" style="width: 53px" id="IcaptcheI" onblur="checkCaptche(${sessionScope.captche})"/>--%>
        <label for="IcaptcheI">captche:</label><input type="text" name="captche" style="width: 53px" id="IcaptcheI"/>
        <a href="javascript:captcheChange()"><img id="ICaptche" src="${pageContext.request.contextPath}/cn/joah/login/web/servlet/captcheServlet"></a>
        <span id="capInfo"></span><br>
        <button type="submit" onclick="return check()"  >submit</button>
    </form>
</body>
</html>
