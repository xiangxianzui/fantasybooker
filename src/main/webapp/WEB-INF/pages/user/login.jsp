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
<jsp:include page="../common/_head.jsp"/>
<title>登录 - fantasybooker</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login.style.css" type="text/css" charset="utf-8" />
<body>
<div id="login-content">
    <div class="login-header">
        <span class="title">fantasybooker</span>
    </div>
    <f:form method="post" modelAttribute="loginUser">
        <div class="login-input-box">
            <span class="icon icon-user"></span>
            <f:input path="nickname"/>
            <f:errors path="nickname" cssStyle="font-size:x-small;color: #cc2f1b;"/>
        </div>
        <div class="login-input-box">
            <span class="icon icon-email"></span>
            <f:input path="email"/>
            <f:errors path="email" cssStyle="font-size:x-small;color: #cc2f1b;"/>
        </div>
        <div class="login-input-box">
            <span class="icon icon-password"></span>
            <f:password path="password"/>
            <f:errors path="password" cssStyle="font-size:x-small;color: #cc2f1b;"/>
        </div>
        <div class="login-input-box">
            <input class="login-button" type="submit" value="登录">
        </div>
    </f:form>

    <div class="logon-box">
        <a href="password/find">忘记密码?</a>
        |
        <a href="register">注册账户</a>
    </div>
</div> <!-- /#login-content -->
<div class="result-msg">${result}</div>
</body>
</html>
