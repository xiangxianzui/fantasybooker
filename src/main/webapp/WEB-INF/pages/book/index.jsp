<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 8/13/17
  Time: 6:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../common/_head.jsp"/>
<body>

<c:set var="page" value="${page}"/>
<c:set var="pageNum" value="${pageNum}"/>
<c:set var="books" value="${books}"/>

<table class="table table-hover table-responsive table-striped table-bordered">
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

<div class="text-center">
    <nav>
        <ul class="pagination">
            <li><a href="<c:url value="/book/list?page=1"/>">首页</a></li>
            <li><a href="<c:url value="/book/list?page=${page-1}"/>">&laquo;</a></li>
            <c:choose>
                <c:when test="${pageNum > 10}">
                    <c:forEach begin="${page}" end="${page+9>pageNum ? pageNum : page+9}" varStatus="loop">
                        <c:set var="active" value="${loop.index==page?'active':''}"/>
                        <li class="${active}">
                            <a href="<c:url value="/book/list?page=${loop.index}"/>">${loop.index}</a>
                        </li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach begin="1" end="${pageNum}" varStatus="loop">
                        <c:set var="active" value="${loop.index==page?'active':''}"/>
                        <li class="${active}">
                            <a href="<c:url value="/book/list?page=${loop.index}"/>">${loop.index}</a>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <li><a href="<c:url value="/book/list?page=${page+1}"/>">&raquo;</a></li>
            <li><a href="<c:url value="/book/list?page=${pageNum}"/>">尾页</a></li>

        </ul>
    </nav>
</div>

<jsp:include page="../common/_foot.jsp"/>
</body>
</html>
