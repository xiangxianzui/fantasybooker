<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 8/15/17
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="../common/_head.jsp"/>
<title>个人主页 - fantasybooker</title>
<body>
<div id="background">
    <div id="page">
        <jsp:include page="../common/_header.jsp"/>
        <div id="contents">
            <div id="main">
                <f:form method="post" modelAttribute="loginUser">
                    <div class="form-group">
                        <span class="corbenbold">昵称: </span>
                        <f:input path="nickname" disabled="true" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <span class="corbenbold">邮箱: </span>
                        <f:input path="email" disabled="true" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <span class="corbenbold">联系方式: </span>
                        <f:input path="phone" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <span class="corbenbold">收货地址: </span>
                        <f:input path="address" cssClass="form-control"/>
                    </div>
                    <div class="form-group">
                        <span class="corbenbold">注册时间: </span>
                        <f:input path="registerTime" disabled="true" cssClass="form-control"/>
                    </div>
                    <br>
                    <input type="submit" value="更新" class="button"/>
                </f:form>
            </div>
        </div> <!-- /#contents -->
        <jsp:include page="../common/_footer.jsp"/>
    </div> <!-- /#page -->
</div> <!-- /#background -->
<jsp:include page="../common/_foot.jsp"/>
</body>
</html>
