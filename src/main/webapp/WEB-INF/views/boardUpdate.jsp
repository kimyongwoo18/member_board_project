<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-11
  Time: 오전 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<html>
<head>
    <title>boardUpdate</title>
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
    <form action="/board/update" method="post" name="saveForm" id="saveForm" enctype="multipart/form-data">
        <input type="hidden" name="Id" value="${board.id}">
        <input type="text" class="form-control mb-2" id="boardTitle" name="boardTitle" value="${board.boardTitle}">
        <input type="text" name="boardWriter" class="form-control" value="${sessionScope.loginEmail}" readonly>
        <textarea name="boardContents" class="form-control" id="boardContents" cols="30" rows="10">
            ${board.boardContents}
            --------------------------------------------------------------------------------------
        </textarea>
        <input type="file" class="form-control mb-2" id="boardFileName" name="boardFileName">
        <input type="button"  onclick="update()" class="btn btn-primary form-control mb-2" value="작성완료">
    </form>
</div>
</body>
<script>
    const update = () => {
      document.saveForm.submit();
    }
</script>
</html>
