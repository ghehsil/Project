<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--引入jstl-->
<%@include file="../common/tag.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>用户注册页面</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel-heading text-center">
        <h1>用户注册</h1>
    </div>

    <div class="panel-body">
        <form class="form-horizontal" role="form" method="post" action="/User/userRegister" enctype="multipart/form-data">
            <div class="form-group">
                <label for="userName" class="col-sm-2 control-label">用户名:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入用户名"/>
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">密码:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码"/>
                </div>
            </div>

            <div class="form-group">
                <label for="passwordRepeat" class="col-sm-2 control-label">重复密码:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="passwordRepeat" name="passwordRepeat" placeholder="请重复输入密码"/>
                </div>
            </div>

            <div class="form-group">
                <label for="portrait" class="col-sm-2 control-label">头像:</label>
                <div class="col-sm-10">
                    <input type="file" class="form-control" id="portrait" name="portrait"/>
                </div>
            </div>

            <div class="form-group">
                <label for="phone" class="col-sm-2 control-label">电话:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入电话"/>
                </div>
            </div>

            <div class="form-group">
                <label for="birth" class="col-sm-2 control-label">生日:</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" id="birth" name="birth"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-info">提交</button>
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
