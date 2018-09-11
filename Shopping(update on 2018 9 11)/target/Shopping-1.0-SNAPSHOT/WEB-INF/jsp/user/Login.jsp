<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--引入jstl-->
<%@include file="../common/tag.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>登录</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel-heading text-center">
        <h2>登录页面</h2>
    </div>

    <div class="panel-body">
        <form class="form-horizontal" action="/User/userLogin" method="post" role="form">
            <div class="form-group">
                <label for="userName" class="col-sm-2 control-label">账号:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="userName" id="userName" placeholder="请输入名字">
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">密码:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">登录</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
