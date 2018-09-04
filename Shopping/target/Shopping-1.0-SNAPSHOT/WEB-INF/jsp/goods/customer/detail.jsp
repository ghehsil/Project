<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--引入jstl-->
<%@include file="../../common/tag.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>商品详情页</title>
    <%@include file="../../common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>商品详情</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>价格</th>
                    <th>图片</th>
                    <th>详情</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>${goods.name}</th>
                    <th>${goods.price}</th>
                    <th><img class="img-thumbnail" width="80" height="80"
                             src="/resources/Img/goods/${goods.picture}.jpg"/></th>
                    <th>${goods.detail}</th>
                    <th><a href="/Goods/Customer/addToShoppingCart?md5Id=${goods.md5Id}">加入购物车</a></th>
                </tr>
                </tbody>
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
