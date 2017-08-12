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
        <a href="">
            <c:choose>
                <c:when test="${loginUser == null}">guest</c:when>
                <c:otherwise>${loginUser.nickname}</c:otherwise>
            </c:choose>
        </a>|<a href="">View Bags</a>
    </span>
    <a href="index.html" id="logo"></a> <!-- /#logo -->
    <ul id="navigation">
        <li><a href="index.html">Home</a></li>
        <li><a href="about.html">About</a></li>
        <li><a href="blog.html">Blog</a></li>
        <li><a href="shop.html">Shop</a></li>
        <li><a href="contact-us.html">Contact Us</a></li>
    </ul>
</div> <!-- /#header -->
