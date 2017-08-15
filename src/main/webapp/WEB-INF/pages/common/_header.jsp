<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 8/12/17
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
	<span id="connect">
		<a href="http://sc.chinaz.com/" target="_blank" class="facebook"></a>
        <a href="http://sc.chinaz.com/" target="_blank" class="twitter"></a>
        <a href="http://sc.chinaz.com/" target="_blank" class="vimeo"></a>
    </span>
    <span id="infos">
        <c:choose>
            <c:when test="${loginUser == null}">
                <a href="/user/login">登录</a>
                |
                <a href="/user/register">注册</a>
            </c:when>
            <c:otherwise>
                <a href="<c:url value="/user/${loginUser.userCode}" />">${loginUser.nickname}</a>
                |
                <a href="/user/logout">退出</a>
            </c:otherwise>
        </c:choose>
    </span>
    <a href="/index" id="logo"></a> <!-- /#logo -->
    <ul id="navigation">
        <li><a href="/index">Home</a></li>
        <li><a href="about.html">About</a></li>
        <li><a href="blog.html">Blog</a></li>
        <li><a href="shop.html">Shop</a></li>
        <li><a href="contact-us.html">Contact Us</a></li>
    </ul>
</div> <!-- /#header -->
