<%--
  Created by IntelliJ IDEA.
  User: joah
  Date: 17-9-10
  Time: 上午10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:set var="User.sex" value="sex" scope="request"/>
    ${User.sex}
    ${User.sex == "female"}
</body>
</html>
