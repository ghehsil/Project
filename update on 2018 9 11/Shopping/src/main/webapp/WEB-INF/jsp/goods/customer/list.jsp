<%@ page import="com.ls.entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         deferredSyntaxAllowedAsLiteral="true" %>
<!--引入jstl-->
<%@include file="../../common/tag.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>商品列表页</title>
    <%@include file="../../common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <%
                User user = (User) session.getAttribute("user");
                if (user == null) {
            %>
            <p class="text-right"><a href="/User/Login">登录</a>&nbsp;&nbsp;&nbsp;<a href="/User/Register">注册</a></p>
            <%} else {%>
            <p class="text-right">欢迎您,<%=user.getUserName()%>&nbsp;&nbsp;&nbsp;<a href="/Goods/Customer/myShoppingCart">我的购物车</a>&nbsp;&nbsp;&nbsp;<a
                    href="/Goods/Customer/myPayment">我的账单</a>&nbsp;&nbsp;&nbsp;<a href="/User/Login">退出登录</a></p>
            <%}%>
            <div style="margin: auto;">
                <form class="bs-example bs-example-form" action="/Goods/Customer/search" method="post" role="form">
                    <div class="input-group">
                        <input type="text" name="name" class="form-control"/>
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                搜索
                            </button>
                        </span>
                    </div>
                </form>
            </div>
            <h2>商品列表</h2>
        </div>

        <div>
            <ul class="nav nav-tabs">
                <c:forEach var="list" items="${goodsTypeList}">
                    <li><a href="/Goods/Customer/list?typeName=${list.typeName}">${list.typeName}</a></li>
                </c:forEach>
            </ul>
        </div>

        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>价格</th>
                    <th>图片</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="goods" items="${list}">
                    <tr>
                        <th>${goods.name}</th>
                        <th>${goods.price}</th>
                        <th><img class="img-thumbnail" width="80" height="80"
                                 src="/resources/Img/goods/${goods.picture}.jpg"/></th>
                        <th><a href="/Goods/Customer/detail?md5Id=${goods.md5Id}">详情</a></th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <table>
                <ul class="pagination pagination-lg">
                    <%if (Integer.parseInt(request.getAttribute("showNum").toString()) == 1) {%>
                    <li class="disabled"><a href="#">&laquo;</a></li>
                    <%} else {%>
                    <li><a href="/Goods/Customer/pageList?showNum=${showNum - 1}">&laquo;</a></li>
                    <%}%>

                    <%
                        int num = Integer.parseInt(request.getAttribute("pages").toString());
                        for (int i = 1; i <= num; i++) {
                    %>
                    <li><a href="/Goods/Customer/pageList?showNum=<%=i%>"><%=i%>
                    </a></li>
                    <%}%>

                    <%if (Integer.parseInt(request.getAttribute("pages").toString()) == 0 || Integer.parseInt(request.getAttribute("showNum").toString()) == Integer.parseInt(request.getAttribute("pages").toString())) {%>
                    <li class="disabled"><a href="#">&raquo;</a></li>
                    <%} else {%>
                    <li><a href="/Goods/Customer/pageList?showNum=${showNum + 1}">&raquo;</a></li>
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
