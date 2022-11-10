<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오전 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<html>
<head>
    <title>memberLogin</title>
    <style>
        #page {
            width: 800px;
            margin-top: 30px;

        }
    </style>
</head>
<body>
<%--아이디 비밀번호 입력 후 세션으로 로그인 상태 유지 로그인 하면 페이징 처리 된 글목록 화면으로 간다--%>
<div class="container-fluid" id="page">
    <form action="/login" method="post" name="loginForm" id="loginForm">
        <input type="text" name="memberEmail" id="memberEmail" class="form-control" placeholder="이메일 입력" maxlength="50">
        <input type="password" name="memberPassword" id="memberPassword" class="form-control" placeholder="비밀번호 입력" maxlength="30">
        <input type="button" onclick="loginFn()" class="btn btn-primary" value="로그인">
    </form>
</div>
</body>
<script>
    const loginFn = () => {
        console.log("이메일 길이"+document.getElementById("memberEmail").value.length);
        console.log("비밀번호 길이"+document.getElementById("memberPassword").value.length);
           if(document.getElementById("memberEmail").value.length == 0){
               alert("이메일을 입력해주세요");
               return false;
           }else if(document.getElementById("memberPassword").value.length == 0){
               alert("비밀번호를 입력해주세요");
               return false;
           }else{
               document.loginForm.submit();
           }
    }
</script>

</html>
