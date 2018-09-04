<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--引入jstl-->
<%@include file="../common/tag.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>人员列表页</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>人员列表</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>用户头像</th>
                    <th>用户姓名</th>
                    <th>用户生日</th>
                    <th>用户电话</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="users" items="${list}">
                    <tr>
                        <th><img class="img-thumbnail" width="80" height="80"
                                 src="/resources/Img/user/${users.portrait}.jpg"/></th>
                        <th>${users.userName}</th>
                        <th>${users.birth}</th>
                        <th>${users.phone}</th>
                        <th><a href="/User/deleteOne?md5Id=${users.md5Id}">删除</a></th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <table>
                <ul class="pagination pagination-lg">
                    <%if (Integer.parseInt(request.getAttribute("showNum").toString()) == 1) {%>
                    <li class="disabled"><a href="#">&laquo;</a></li>
                    <%} else {%>
                    <li><a href="/User/pageList?showNum=${showNum - 1}">&laquo;</a></li>
                    <%}%>

                    <%
                        int num = Integer.parseInt(request.getAttribute("pages").toString());
                        for (int i = 1; i <= num; i++) {
                    %>
                    <li><a href="/User/pageList?showNum=<%=i%>"><%=i%>
                    </a></li>
                    <%}%>

                    <%if (Integer.parseInt(request.getAttribute("pages").toString()) == 0 || Integer.parseInt(request.getAttribute("showNum").toString()) == Integer.parseInt(request.getAttribute("pages").toString())) {%>
                    <li class="disabled"><a href="#">&raquo;</a></li>
                    <%} else {%>
                    <li><a href="/User/pageList?showNum=${showNum + 1}">&raquo;</a></li>
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
