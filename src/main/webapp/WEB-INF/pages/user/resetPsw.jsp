<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 8/8/17
  Time: 11:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>重置密码</title>
</head>
<body>
    <f:form method="post" modelAttribute="resetPswUser">
        Nickname: <f:input path="nickname"/><br>
        Usercode: <f:input path="userCode"/><br>
        New Password: <f:password path="password"/><br>
        <input type="submit" value="重置密码">
    </f:form>
<br><h3>${result}</h3>
</body>
</html>
