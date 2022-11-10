<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-08
  Time: 오전 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<html>
<head>
    <title>index</title>
    <style>
        #page {
            width: 800px;
            margin-top: 30px;
        }
    </style>
</head>
<body>

<div class="container-fluid text-center" id="page">
    <h2>회원제게시판</h2>
    <h4>FOR ONLY MEMBERS</h4>
    <h6>로그인 후에 글 목록 조회 및 작성 가능합니다.</h6>
<c:if test="${sessionScope.loginEmail != null}">
    <h6>${sessionScope.loginName}님 환영합니다.</h6>
</c:if>
    <button class="btn btn-primary" onclick="saveFn()">회원가입</button>
    <button class="btn btn-secondary" onclick="loginFn()">로그인</button>
    <c:if test="${sessionScope.loginEmail != null}">
        <button class="btn btn-success" onclick="listFn()">글목록</button>
    </c:if>
</div>

</body>
<script>
    const saveFn = () => {
        location.href = "/save";
    }
    const loginFn = () => {
        location.href = "/login";
    }
    const listFn = () => {
        location.href = "/board/";
    }
</script>
</html>
