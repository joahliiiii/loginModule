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
    <script src="cn/joah/login/web/servlet/RegisterServlet"></script>
</head>
<body>
    <p align="center" style="font-weight: 500;color: red">${loginPageErr}</p>
    <p>hello this is login.jsp</p>
    <h1>Sign in to mypage</h1>
    <form action="${pageContext.request.contextPath}/cn/joah/login/web/servlet/LoginServlet" method="post">
        <label for="username">username</label><br><input type="text" name="username" id="username" value="${User.username}" onblur="checkUname()">
                    <span id="userInfo"></span><br>

        <label for="password">password</label><br><input type="password" name="password" id="password" onblur="checkPass()" >
                    <span id="passInfo"></span><br>

        <label for="captcheI">captche</label><input type="text" name="captche" id="captcheI">
            <a href="javascript:captcheChange()"><img src="<c:url value="/cn/joah/login/commons/captche/CaptcheServlet"/>"></a>
                <span id="capInfo"></span>

        <button type="submit" onclick="return checkLogin()"> submit </button>
    </form>
</body>
</html>
