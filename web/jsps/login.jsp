<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>login</title>
    <script>var contextPath="${pageContext.request.contextPath}";</script>
    <script src="${pageContext.request.contextPath}/javascripts/registPageJS.js"></script>
</head>
<body>
    <p align="center" style="font-weight: 500;color: red">${loginPageErr}</p>
    <h1>Sign in to mypage</h1>
    <form action="${pageContext.request.contextPath}/cn/joah/login/web/servlet/LoginServlet" method="post">
        <label for="username">username</label><br><input type="text" name="username" id="username" value="${User.username}" onblur="checkUname()">
                    <span id="userInfo"></span><br>

        <label for="password">password</label><br><input type="password" name="password" id="password" onblur="checkPass()" >
                    <span id="passInfo"></span><br>

        <label for="captcheI">captche</label><br><input type="text" name="captche" id="captcheI" style="width: 255px">
            <a href="javascript:captcheChange()"><img src="${pageContext.request.contextPath}/cn/joah/login/commons/captche/CaptcheServlet" id="ICaptche"/></a>
                <span id="capInfo"></span><br>

        <button type="submit" onclick="return checkLogin()"> sign in </button>
        <button type="button"  onclick="javascript:window.location.href='regist.jsp'"> sign up </button>
    </form>
</body>
</html>
