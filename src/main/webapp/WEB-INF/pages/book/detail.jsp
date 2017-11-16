<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 8/15/17
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../common/_head.jsp"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.spinner.css" type="text/css" charset="utf-8" />
<title>${curBook.bookname} - fantasybooker</title>
<body>
<div id="background">
    <div id="page">
        <jsp:include page="../common/_header.jsp"/>
        <div id="contents">
            <div class="container-fluid detail">
                <div class="row">
                    <div class="col-md-4">
                        <img src="/resources/images/shirt-blue.jpg" alt="image">
                    </div>
                    <div class="col-md-8">
                        <div class="row row-div">
                            <div class="col-md-6 text-left">
                                <h3>${curBook.bookname}</h3>
                            </div>
                            <div class="col-md-6 text-right">
                                <p>作者: ${curBook.author}</p>
                                <p>现价: ¥${curBook.price*curBook.discount}</p>
                                <s>原价: ¥${curBook.price}</s>
                            </div>
                        </div>
                        <div class="row row-div">
                            <div class="col-md-12">内容简介: <p>This website template has been designed by Free Website Templates for you, for free. You can replace all this text with your own text. You can remove any link to our website from this website template, you're free to use this website template without linking back to us.
                                If you're having problems editing this website template, </p></div>
                        </div>
                        <div class="row row-div">
                            <div class="col-md-12">
                                配送至:
                                <span>
                                    <c:choose>
                                        <c:when test="${loginUser == null}">
                                            <a class="detail-a" href="/user/login?redirect=/book/${curBook.id}">登录填写收货地址</a>
                                        </c:when>
                                        <c:otherwise>
                                            <span>${loginUser.address}</span>
                                            <a class="detail-a" href="/user/${loginUser.userCode}" target="_blank">(更改收货地址?)</a>
                                        </c:otherwise>
                                    </c:choose>
                                </span>
                            </div>
                        </div>
                        <div class="row row-div">
                            <div class="col-md-12">
                                <input type="text" class="spinner"/>
                                <span>(剩余: ${curBook.amount}件)</span>
                            </div>
                        </div>
                        <div class="row row-div">
                            <div class="col-md-3"><button title="该商品一旦降价，系统会以邮件和站内信的形式通知您"><i class="fa fa-heart"></i> 关注</button></div>
                            <div class="col-md-3"><button class="shopping-cart"><i class="fa fa-shopping-cart"></i> 加入购物车</button></div>
                            <div class="col-md-3"><button><i class="fa fa-shopping-bag"></i> 立即购买</button></div>
                            <div class="col-md-3"></div>
                        </div>
                        <div class="row row-div">
                            <div class="col-md-12">温馨提示: 支持7天无理由退货</div>
                        </div>
                    </div>
                </div><!-- row -->
            </div><!-- container -->
        </div> <!-- /#contents -->
        <jsp:include page="../common/_footer.jsp"/>
    </div> <!-- /#page -->
</div> <!-- /#background -->
<jsp:include page="../common/_foot.jsp"/>
<script src='<%=request.getContextPath()%>/resources/js/jquery.spinner.js' language='JavaScript' charset='utf-8'></script>
<script type="text/javascript">
    $('.spinner').spinner();
</script>
<script type="text/javascript">
    $('.shopping-cart').on('click', function(){
        var postData = {
            bookId: ${curBook.id},
            count: 10
        };
        $.ajax({
            url:'/shop/shoppingCart',
            type:'POST',
            dataType:'json',
            contentType:'application/json', //contentType很重要
            data:JSON.stringify(postData),
            success:function(result){
                window.location.reload();
            }
        });
    });
</script>
</body>
</html>
