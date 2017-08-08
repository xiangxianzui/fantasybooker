<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 8/5/17
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<f:form method="post" modelAttribute="loginUser">
    Nickname: <f:input path="nickname"/><br>
    Email: <f:input path="email"/><br>
    Password: <f:password path="password"/><br>
    <input type="submit" value="登录">
</f:form>
<br><h3>${result}</h3>
</body>
</html>
