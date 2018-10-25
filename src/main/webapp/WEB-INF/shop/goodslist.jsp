<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>

    <!-- jquery -->
    <script type="text/javascript" th:src="@{/js/jquery.min.js}"></script>
    <!-- bootstrap -->
    <link rel="stylesheet" type="text/css" th:href="@{/bootstrap/css/bootstrap.min.css}" />
    <script type="text/javascript" th:src="@{/bootstrap/js/bootstrap.min.js}"></script>
    <!-- jquery-validator -->
    <script type="text/javascript" th:src="@{/jquery-validation/jquery.validate.min.js}"></script>
    <script type="text/javascript" th:src="@{/jquery-validation/localization/messages_zh.min.js}"></script>
    <!-- layer -->
    <script type="text/javascript" th:src="@{/layer/layer.js}"></script>
    <!-- md5.js -->
    <script type="text/javascript" th:src="@{/js/md5.min.js}"></script>
    <!-- common.js -->
    <script type="text/javascript" th:src="@{/js/common.js}"></script>


</head>
<body>


<style type="text/css">
    table.hovertable {
        font-family: verdana,arial,sans-serif;
        font-size:11px;
        color:#333333;
        border-width: 1px;
        border-color: #999999;
        border-collapse: collapse;
    }
    table.hovertable th {
        background-color:#c3dde0;
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }
    table.hovertable tr {
        background-color:#d4e3e5;
    }
    table.hovertable td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }
</style>

<!-- Table goes in the document BODY -->
<table class="hovertable">
    <tr>
        <th>ID</th><th>商品名称</th><th>商品图片</th><th>商品原价</th><th>库存数量</th><th>详情</th>
    </tr>
<c:forEach var="c" items="${goodsList}" varStatus="states">
    <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
        <td>${states.index+1}</td>
        <td>${c.goodsName}</td>
        <td>这里是放图片的</td>
        <td>${c.goodsPrice}</td>
        <td>${c.goodsStock}</td>
        <td><a href="${pageContext.request.contextPath}/goods/to_detail?id=${c.id}">详情</a></td>
    </tr>
</c:forEach>
</table>

</body>
</html>
