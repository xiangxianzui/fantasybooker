<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 8/14/17
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<f:form method="post" modelAttribute="searchBean">
    <div class="login-input-box">
        <span class="icon icon-user"></span>
        <f:input path="searchword"/>
    </div>
    <div class="login-input-box">
        <span class="icon icon-user"></span>
        <f:input path="searchpage"/>
    </div>
    <div class="login-input-box">
        <input class="login-button" type="submit" value="搜索">
    </div>
</f:form>

<table id="search-results" class="table table-hover table-responsive table-striped table-bordered">
    <thead>
    <tr>
        <td>书名</td>
        <td>作者</td>
        <td>描述</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.bookname}</td>
            <td>${book.author}</td>
            <td>${book.description}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../common/_foot.jsp"/>
<script  type="text/javascript">
    $(document).ready(function(){
        var winH = $(window).height(); //页面可视区域高度
        //定义鼠标滚动事件
        $(window).bind('scroll', function(){
            alert("ahhaioho");
        });
    });
</script>
</body>
</html>
