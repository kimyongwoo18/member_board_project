<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오후 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<html>
<head>
    <title>memberUpdate</title>
    <style>
        #page {
            width: 800px;
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="container" id="page">
    <h3>회원정보 수정</h3>
    <h4>수정 가능한 정보는 비밀번호와 휴대전화번호와 프로필사진입니다.</h4>
    <form action="/member/update" method="post" name="updateForm" enctype="multipart/form-data">

        <input type="text" class="form-control" placeholder="기존 이메일 : ${member.memberEmail}" readonly>
        <input type="password"  name="beforePassword" id="beforePassword"
               class="form-control" placeholder="기존 비밀번호">
        <input type="password" name="memberPassword" class="form-control" placeholder="새로운 비밀번호">
        <input type="text" name="memberMobile" class="form-control" placeholder="기존 전화번호 : ${member.memberMobile}">
        <img src="${pageContext.request.contextPath}/upload/${member.storedFileName}"
             alt="" width="100" height="100">
        <input type="file" name="memberProfile" class="form-control">
        <input type="button" onclick="update()" class="btn btn-success" value="수정">
    </form>
    <button href="/">초기화면으로</button>
</div>
</body>
<script>
    const update = () => {
      if(document.getElementById("beforePassword").value == ${member.memberPassword}){
          document.updateForm.submit();
      }else{
          alert("기존비밀번호가 일치하지않습니다.")
      }
    }
</script>
</html>
