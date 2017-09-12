<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: joah
  Date: 17-9-10
  Time: 下午9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:choose>
        <c:when test="${sessionScope.loginStatus!='logged' && sessionScope.loginStatus!=''}">
            <p align="center" style="color: red">请先登录系统 <a href="login.jsp">点击登录</a></p>
        </c:when>
        <c:otherwise>
            <p align="center">Welcome ${sessionScope.username}</p>
        </c:otherwise>
    </c:choose>

</body>
</html>
