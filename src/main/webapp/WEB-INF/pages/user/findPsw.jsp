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
<jsp:include page="../common/_head.jsp"/>
<title>找回密码 - fantasybooker</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login.style.css" type="text/css" charset="utf-8" />
<body>
<div id="login-content">
    <div class="login-header">
        <span class="title">fantasybooker</span>
    </div>
    <f:form method="post" modelAttribute="findPswUser">
        <div class="login-input-box">
            <span class="icon icon-email"></span>
            <f:input path="email"/>
        </div>
        <div class="login-input-box">
            <input class="login-button" type="submit" value="发送密码到我的邮箱">
        </div>
    </f:form>
    <div class="logon-box">
        <a href="../login">返回登录</a>
    </div>
</div> <!-- /#login-content -->
<div class="result-msg">${result}</div>
</body>
</html>
