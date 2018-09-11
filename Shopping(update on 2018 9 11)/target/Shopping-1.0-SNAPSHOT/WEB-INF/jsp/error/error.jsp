<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--引入jstl-->
<%@include file="../common/tag.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>错误页面</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel-heading text-center">
        <h1>${error}</h1>
    </div>
</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
