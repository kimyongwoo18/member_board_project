<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오후 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>memberUpdate</title>
</head>
<body>
<div class="container">
    <h3>회원정보 수정</h3>
    <h4>수정 가능한 정보는 비밀번호와 휴대전화번호와 프로필사진입니다.</h4>
    <form action="/member/update" method="post" name="updateForm">
        <input type="text" placeholder="기존 이메일 : ${member.memberEmail}" readonly>
        <input type="password" name="beforePassword" id="beforePassword"
               class="form-control" placeholder="기존 비밀번호">
        <input type="password" name="memberPassword" placeholder="새로운 비밀번호">
        <input type="text" name="memberMobile" class="form-control">
        <input type="file" name="memberProfile" class="form-control">
        <input type="button" onclick="update()" class="form-control">
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
