<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-08
  Time: 오전 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<button class="btn btn-primary" onclick="saveFn()">회원가입</button>
<button class="btn btn-secondary" onclick="loginFn()">로그인</button>
<button class="btn btn-success" onclick="listFn()">글목록</button>
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
