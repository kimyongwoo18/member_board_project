<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오후 2:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<html>
<head>
    <title>memberMain</title>
</head>
<body>
<div class="container">
    <input type="button" onclick="update()" value="회원정보수정" class="btn-primary btn">
</div>
</body>
<script>
    const update = () => {
      location.href="/member/update?memberEmail=${sessionScope.loginEmail}";
    }
</script>
</html>
