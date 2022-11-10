<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오후 1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<html>
<head>
    <title>boardSave</title>
    <style>
        #page {
            width: 800px;
            margin-top: 30px;
        }
        #boardContents{
            width: 800px;
            height: 400px;
          }
    </style>
</head>
<body>
<div class="container" id="page">
<form action="/boardSave" method="post" name="saveForm" id="saveForm" enctype="multipart/form-data">
    <input type="text" class="form-control mb-2" id="boardTitle" name="boardTitle" placeholder="제목">
    <input type="text" name="boardWriter" class="form-control" value="${sessionScope.loginEmail}" readonly>
    <textarea name="boardContents" class="form-control" id="boardContents" cols="30" rows="10"></textarea>
    <input type="file" class="form-control mb-2" id="boardFileName" name="boardFileName">
    <input type="button"  onclick="save()" class="btn btn-primary form-control mb-2" value="작성완료">
</form>
</div>
</body>
<script>
    const save = () => {
        if(document.getElementById("boardTitle").value.length == 0) {
            alert("제목을입력하세요")
            return false;
        }else if(document.getElementById("boardContents").value.length == 0){
            alert("내용을 입력하세요")
            return false;
        }else{
            document.saveForm.submit();
        }

    }
</script>
</html>
