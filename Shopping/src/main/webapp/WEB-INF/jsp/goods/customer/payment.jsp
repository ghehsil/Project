<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--引入jstl-->
<%@include file="../../common/tag.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>账单列表页</title>
    <%@include file="../../common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <p class="text-right"><a href="/Goods/Customer/list">返回商品页面</a></p>
            <h2>账单列表</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>图片</th>
                    <th>名称</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>总价</th>
                    <th>明细</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="payment" items="${payment}">
                    <tr>
                        <th><img class="img-thumbnail" width="80" height="80"
                                 src="/resources/Img/goods/${payment.picture}.jpg"/></th>
                        <th>${payment.name}</th>
                        <th>${payment.price}</th>
                        <th>${payment.number}</th>
                        <th>${payment.totalPrice}</th>
                        <th>${payment.detail}</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <table>
                <ul class="pagination pagination-lg">
                    <%if(Integer.parseInt(request.getAttribute("showNum").toString()) == 1){%>
                    <li class="disabled"><a href="#">&laquo;</a></li>
                    <%}else{%>
                    <li><a href="/Goods/Customer/myPayment?showNum=${showNum - 1}">&laquo;</a></li>
                    <%}%>

                    <%int num=Integer.parseInt(request.getAttribute("pages").toString());for(int i = 1;i <= num;i++){%>
                    <li><a href="/Goods/Customer/myPayment?showNum=<%=i%>"><%=i%></a></li>
                    <%}%>

                    <%if(Integer.parseInt(request.getAttribute("pages").toString()) == 0 || Integer.parseInt(request.getAttribute("showNum").toString()) == Integer.parseInt(request.getAttribute("pages").toString())){%>
                    <li class="disabled"><a href="#">&raquo;</a></li>
                    <%}else{%>
                    <li><a href="/Goods/Customer/myPayment?showNum=${showNum + 1}">&raquo;</a></li>
                    <%}%>
                </ul>
            </table>
        </div>
    </div>
</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
