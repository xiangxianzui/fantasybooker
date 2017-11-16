<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.wanghao.util.Constant" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
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
                <div class="btn-group">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown">
                        ${loginUser.nickname}
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li>
                            <a href="/user/${loginUser.userCode}">个人中心</a>
                        </li>
                        <li>
                            <a href="#">我的关注</a>
                        </li>
                        <li>
                            <a href="#">我的订单</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/user/logout">退出登录</a>
                        </li>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>
        |
        <div class='btn-group'>
            <a href='' class='dropdown-toggle' data-toggle='dropdown'>
                购物车
                <span class='caret'></span>
            </a>
            <ul class='dropdown-menu' role='menu'>
                <li>
                    <a href='#' class='btn'>bookname: ¥9.99 x 10</a>
                </li>
                <li class='divider'></li>
                <li>
                    <a class='btn'>totalprice</a>
                </li>
                <li class='divider'></li>
                <li>
                    <a href='' class='btn'>结算</a>
                </li>
                <%
                    Cookie[] cookies = request.getCookies();
                    String shoppingCart = null;
                    for (Cookie cookie : cookies){
                        if(StringUtils.equals(cookie.getName(), Constant.SHOPPING_CART)){
                            shoppingCart = cookie.getValue();
                            break;
                        }
                    }
                    if(!StringUtils.isBlank(shoppingCart)){
                        JSONObject jsonObject = JSON.parseObject(shoppingCart);
                        JSONArray cartItems = jsonObject.getJSONArray("cartItems");
                        BigDecimal totalPrice = jsonObject.getBigDecimal("totalPrice");
                        StringBuilder html = new StringBuilder();
                        for(int i=0; i<cartItems.size(); i++){
                            /*JSONObject book = (JSONObject)cartItems.get(i).;
                            String bookname = cartItems[i]
                            html.append("<li><a href='' class='btn'>")*/
                        }
                        html.append("<li></li>");
                        out.write(html.toString());
                    }
                    else{
                        out.write("<li>购物车还是空的</li>");
                    }
                %>
            </ul>
        </div>

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
