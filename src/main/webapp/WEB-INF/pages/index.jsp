<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<title>主页</title>
<body>
<h1>welcome
<c:choose>
    <c:when test="${loginUser == null}">guest</c:when>
    <c:otherwise>${loginUser.nickname}</c:otherwise>
</c:choose>
</h1>
</body>
</html>