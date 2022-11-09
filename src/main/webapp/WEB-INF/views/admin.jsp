<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오전 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<html>
<head>
    <title>admin</title>
</head>
<body>
<button class="btn btn-success" onclick="memberlist()">회원목록</button>
</body>
<script>
    const memberList = () => {
      location.href="/member/"
    }
</script>
</html>
