<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/22 0022
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>

</head>
<body>

<div id="login">
    <h1>Login</h1>
    <form action="${pageContext.request.contextPath}/user/do_login" method="post">
        <input type="text" style="height: 40px;width: 300px" required="required" placeholder="用户名" value="aaa" name="name"></input>
        <input type="password" style="height: 40px;width: 300px" required="required" placeholder="密码" value="aaa" name="password"></input>
        <button class="but" type="submit">登录</button>
    </form>
</div>

</body>
</html>
