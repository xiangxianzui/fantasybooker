<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 8/8/17
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>找回密码</title>
</head>
<body>
    <f:form method="post" modelAttribute="findPswUser">
        Email: <f:input path="email"/><br>
        <input type="submit" value="找回密码">
    </f:form>
<br><h3>${result}</h3>
</body>
</html>
