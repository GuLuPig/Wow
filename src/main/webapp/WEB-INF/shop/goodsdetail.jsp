<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/22 0022
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>



<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-3.2.1.min.js"></script>
</head>
<body>

<div class="panel panel-default">
    <div class="panel-heading">秒杀商品详情</div>
    <div class="panel-body">
        <%--<span th:if="${user eq null}"> 您还没有登录，请登陆后再操作<br/></span>--%>
        <%--<span>没有收货地址的提示。。。</span>--%>
    </div>
    <table class="table" id="goodslist">
        <tr>
            <td>商品名称</td>
            <td colspan="3" >${goods.goodsName}</td>
        </tr>
        <tr>
            <td>秒杀开始时间</td>
            <td><fmt:formatDate value="${goods.seckillGoods.startDate}" type="both"/></td>
        </tr>
        <tr>
             <td>距离开始抢购</td>
             <td id="seckillTip">
                 <input type="hidden" id="remainSeconds" value="${remainSeconds}" />
                 <c:if test="${seckillStatus eq 0}">
                     <span id="countDown" ></span></span></span>
                 </c:if>
                 <c:if test="${seckillStatus eq 1}">
                     <span>秒杀进行中</span>
                 </c:if>
                 <c:if test="${seckillStatus eq 2}">
                     <span>秒杀已结束</span>
                 </c:if>
             </td>
             <td>
                <form id="seckillForm" method="post" action="/seckill/do_seckill">
            <button class="btn btn-primary btn-block" type="submit" id="buyButton">立即秒杀</button>
            <input type="hidden" name="goodsId" value="${goods.id}" />
                 </form>
             </td>

        </tr>

        <tr>
            <td>商品原价</td>
            <td colspan="3">￥：${goods.goodsPrice}</td>
        </tr>
        <tr>
            <td>秒杀价</td>
            <td colspan="3">￥：${goods.seckillGoods.seckillPrice}</td>
        </tr>
        <tr>
            <td>数量</td>
            <td colspan="3">${goods.seckillGoods.stockCount}</td>
        </tr>
    </table>
</div>
</body>
<script>


    $(function(){
        countDown();
    });

    function countDown(){
        var remainSeconds = $("#remainSeconds").val();
        var h=remainSeconds/3600;
        var m=remainSeconds/3600%60;
        var s=remainSeconds%60;
        var timeout;
        if(remainSeconds > 0){//秒杀还没开始，倒计时
            $("#buyButton").attr("disabled", true);
            timeout = setTimeout(function(){
                $("#countDown").text((Math.floor((remainSeconds - 1)/3600))+"小时"+(Math.floor((remainSeconds - 1)%3600/60))+"分钟"+((remainSeconds - 1)%60)+"秒");
                $("#remainSeconds").val(remainSeconds - 1);
                countDown();
            },1000);
        }else if(remainSeconds == 0){//秒杀进行中
            $("#buyButton").attr("disabled", false);
            if(timeout){
                clearTimeout(timeout);
            }
            $("#seckillTip").html("秒杀进行中");
        }else{//秒杀已经结束
            $("#buyButton").attr("disabled", true);
            $("#seckillTip").html("秒杀已经结束");
        }


    }



</script>
</html>
