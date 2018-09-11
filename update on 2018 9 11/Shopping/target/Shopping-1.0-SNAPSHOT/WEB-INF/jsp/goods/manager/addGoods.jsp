<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--引入jstl-->
<%@include file="../../common/tag.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>增加商品页</title>
    <%@include file="../../common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel-heading text-center">
        <h2>增加商品</h2>
    </div>

    <div class="panel-body">
        <form class="form-horizontal" role="form" method="post" action="/Goods/Manager/addGoods"
              enctype="multipart/form-data">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">商品名称:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" name="name" placeholder="请输入商品名称"/>
                </div>
            </div>

            <div class="form-group">
                <label for="type" class="col-sm-2 control-label">商品类别:</label>
                <div class="col-sm-10">
                    <select name="type" id="type">
                        <option value="">--请选择--</option>
                        <c:forEach items="${goodsTypeList}" var="goodsTypeList">
                            <option value="${goodsTypeList.typeName}"> ${goodsTypeList.typeName} </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="price" class="col-sm-2 control-label">商品价格:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="price" name="price" placeholder="请输入商品价格"/>
                </div>
            </div>

            <div class="form-group">
                <label for="number" class="col-sm-2 control-label">商品库存:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="number" name="number" placeholder="请输入商品库存"/>
                </div>
            </div>

            <div class="form-group">
                <label for="picture" class="col-sm-2 control-label">商品图片:</label>
                <div class="col-sm-10">
                    <input type="file" class="form-control" id="picture" name="picture"/>
                </div>
            </div>

            <div class="form-group">
                <label for="detail">详情:</label>
                <textarea id="detail" name="detail" class="form-control" rows="3"></textarea>
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
