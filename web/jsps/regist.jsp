<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <%--这里显示错误信息--%>
    <p align="center" style="color: red;font-weight: 100"> ${registPageErr}</p>
    <h1>Join us</h1>
    <p>The best way to design, build, and ship software.</p>
    <h2>Create your personal account.</h2>
    <form action="${pageContext.request.contextPath}/cn/joah/login/web/servlet/RegisterServlet" method="post">
        <label for="username">username: </label><br><input type="text" name="username" id="username" value="${User.username}" onblur="checkUname()"><span id="userInfo" ></span><br>

        <label for="password">password: <br></label><input type="password" name="password" id="password" onblur="checkPass()"><span id="passInfo"></span><br>

        <label for="conPassword">Confirmed password: <br></label><input type="password" name="confirmPass" id="conPassword" onblur="checkPasses()"><span id="conpassInfo"></span><br>

        sex: <input type="radio" name="sex" value="male" id="male" checked="${User.sex == "male"}"><label for="male">male</label></input>
        <input type="radio" name="sex" value="female" id="female" checked="${User.sex == "female"}"><label for="female">female</label></input><br>

        <label for="email">email: <br></label><input type="text" name="email" id="email" value="${User.email}" onblur="checkMail()"><span id="mailInfo"></span><br>

        <label for="website">website: <br></label><input type="text" name="website" id="website" value="${User.website}"><br>

        <label for="profile">profile: <br></label><textarea name="profile" style="width: 334px; height: 42px;" id="profile" >${User.profile}</textarea><span id="proInfo"></span> <br>
        <%--<input type="text" name="captche" style="width: 53px" id="IcaptcheI" onblur="checkCaptche(${sessionScope.captche})"/>--%>

        <label for="IcaptcheI">captche:</label><input type="text" name="captche" style="width: 53px" id="IcaptcheI"/>
        <a href="javascript:captcheChange()"><img id="ICaptche" src="${pageContext.request.contextPath}/cn/joah/login/commons/captche/CaptcheServlet"></a>
        <%--<script type="text/javascript">--%>
            <%--var captche="<%=session.getAttribute("captche")%>";--%>
            <%--alert(captche);--%>
        <%--</script>--%>
        <span id="capInfo"></span><br>

        <button type="submit" onclick="return check()"  >submit</button>
    </form>
</body>
</html>
