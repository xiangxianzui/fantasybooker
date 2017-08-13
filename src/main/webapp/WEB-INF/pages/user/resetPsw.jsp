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
<jsp:include page="../common/head.jsp"/>
<title>重置密码 - fantasybooker</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login.style.css" type="text/css" charset="utf-8" />

<body>
<div id="login-content">
    <div class="login-header">
        <span class="title">fantasybooker</span>
    </div>
    <f:form method="post" modelAttribute="resetPswUser">
        <div class="login-input-box" style="display: none">
            <span class="icon icon-user"></span>
            <f:input path="nickname"/>
        </div>
        <div class="login-input-box" style="display: none">
            <span class="icon icon-email"></span>
            <f:input path="userCode"/>
        </div>
        新密码：
        <div class="login-input-box">
            <span class="icon icon-password"></span>
            <f:password path="password"/>
        </div>
        <div class="login-input-box">
            <input class="login-button" type="submit" value="重置密码">
        </div>
    </f:form>

    <div class="logon-box">
        <a href="../login">立即登录</a>
    </div>
</div> <!-- /#login-content -->
<div class="result-msg">${result}</div>
</body>
</html>
