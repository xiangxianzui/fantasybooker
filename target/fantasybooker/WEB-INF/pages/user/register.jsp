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
    <title>用户注册</title>
</head>
<body>
    <f:form method="post" modelAttribute="registerUser">
        Nickname: <f:input path="nickname"/><f:errors path="nickname"/><br>
        Email: <f:input path="email"/><f:errors path="email"/><br>
        Password: <f:password path="password"/><f:errors path="password"/><br>
        <input type="submit" value="注册">
    </f:form>
    <br><h3>${result}</h3>
</body>
</html>
