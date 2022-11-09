<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-08
  Time: 오전 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<html>
<head>
    <title>memberSave</title>
    <script src="/resources/js/jquery.js"></script>
    <style>
        #page {
            width: 800px;
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="container-fluid text-center" id="page">
    <form action="/save" method="post" name="saveForm" id="saveForm">
        <input type="text" class="form-control mb-2" id="memberEmail" name="memberEmail" placeholder="이메일 입력" onblur="emailCheck()">
        <h6 id="emailCheckResult"></h6>
        <input type="password" class="form-control mb-2" id="memberPassword" name="memberPassword" placeholder="비밀번호 입력">
        <input type="text" class="form-control mb-2" id="memberName" name="memberName" placeholder="이름 입력">
        <input type="text" class="form-control mb-2" id="memberMobile" name="memberMobile" placeholder="전화번호 입력">
        <input type="file" class="form-control mb-2" id="memberProfile" name="memberProfile">
        <input type="button"  onclick="save()" class="btn btn-primary form-control mb-2" value="가입">
    </form>
</div>
</body>
<script>
    const save = () => {
        if(document.getElementById("memberEmail").value==""){
            alert("이메일을 입력해주세요")
            return false;
        }else if(document.getElementById("memberPassword").value==""){
            alert("비밀번호를 입력해주세요")
            return false;
        }else if(document.getElementById("memberName").value==""){
            alert("이름을 입력해주세요")
            return false;
        }else if(document.getElementById("memberMobile").value==""){
            alert("전화번호를 입력해주세요")
            return false;
        }else{
            document.saveForm.submit();
        }
    }

    const emailCheck = () =>{
        const inputMemberEmail = document.getElementById("memberEmail").value;
        if(inputMemberEmail.length < 5){
            document.getElementById("emailCheckResult").innerHTML= "5글자이상 입력해주세요";
            document.getElementById("emailCheckResult").style.color= "red";
        }else{
        $.ajax({
            type: "post",//http request method Post Update Delete
            url: "/memberCheck",//요청하는 주소
            data: {
                memberEmail : inputMemberEmail
            },
            dataType : "text",
            success: function (result){//요청이 성공하고 서버로부터 받는 응답
                /*값이 있다고 했을 때 그 값이 입력한 이메일이랑 같은지 봐야하나?
                * 체크해서 값이 있으면 있는거고 없으면 없는거니까 여기에서 같은지 다른지 확인할 필요는
                * 없을것같음. */
                console.log("성공");
                if(result == "ok"){
                    document.getElementById("emailCheckResult").innerHTML="좋은 이메일입니다.";
                    document.getElementById("emailCheckResult").style.color="green";
                }else if(result == "no") {
                    document.getElementById("emailCheckResult").innerHTML= "중복된 이메일이 있네요..";
                    document.getElementById("emailCheckResult").style.color= "red";
                }

            },
            error: function (){//요청이 실패하는 경우 실행됨
                console.log("실패");
            }
        });
        }
    }
</script>
</html>
