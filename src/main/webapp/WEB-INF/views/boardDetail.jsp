<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-10
  Time: 오후 1:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">

<html>
<head>
    <title>boardDetail</title>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
  <script src="/resources/js/jquery.js"></script>
</head>
<body>
<div class="container">
  <table class="table-hover table table-striped">
    <tr>
      <th>제목</th>
      <td>${board.boardTitle}</td>
    </tr>
    <tr>
      <th>작성자</th>
      <td>${board.boardWriter}</td>
    </tr>
    <tr>
      <th>내용</th>
      <td>${board.boardContents}</td>
    </tr>
    <tr>
      <th>작성시간</th>
      <td>${board.boardCreatedDate}</td>
    </tr>
    <tr>
      <th>조회수</th>
      <td>${board.boardHits}</td>
    </tr>
    <c:if test="${board.storedFileName != null}">
      <tr>
        <th>첨부파일</th>
        <td>
          <img src="${pageContext.request.contextPath}/upload/${board.storedFileName}"
               alt="" width="100" height="100">
        </td>
      </tr>
    </c:if>
  </table>
  <c:if test="${board.boardWriter.equals(sessionScope.loginEmail)}">
    <button class="btn btn-success" onclick="updateFn()">수정</button>
    <button class="btn btn-danger" onclick="deleteFn()">삭제</button>
  </c:if>
  <c:if test="${sessionScope.loginName == 'admin'}">
    <button class="btn btn-danger" onclick="deleteFn()">삭제</button>
  </c:if>
</div><div class="container mt-5" id="comment-write" >
  <div class="input-group-sm mb-3">

      <input type="text" id="commentWriter" class="form-control" placeholder="작성자"
             value="${sessionScope.loginName}">

      <input type="text" id="commentContents" class="form-control" placeholder="내용">
    <button id="comment-write-btn" class="btn btn-secondary" onclick="commentWrite()">댓글작성</button>
    <%-- commentWrite 버튼을 클릭하면 작성자, 내용, 글번호 정보를  --%>
  </div>
</div>
</body>
<div class="container mt-5" id="comment-list">
  <table class="table">
    <tr>
      <th>댓글번호</th>
      <th>작성자</th>
      <th>내용</th>
      <th>작성시간</th>
    </tr>
    <c:forEach items="${commentList}" var="comment">
      <tr>
        <td>${comment.id}</td>
        <td>${comment.commentWriter}</td>
        <td>${comment.commentContents}</td>
        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${comment.commentCreatedDate}"></fmt:formatDate></td>
      </tr>
    </c:forEach>
  </table>
</div>
<script>
  const commentWrite = () => {
    const writer = document.getElementById("commentWriter").value;
    const contents = document.getElementById("commentContents").value;
    const board = '${board.id}';
    $.ajax({
      type: "post",
      url: "/comment/save",
      data: {
        commentWriter: writer,
        commentContents: contents,
        boardId: board
      },
      dataType: "json",
      success: function (commentList) {
        console.log(commentList);
        let output = "<table class='table'>";
        output += "<tr><th>댓글번호</th>";
        output += "<th>작성자</th>";
        output += "<th>내용</th>";
        output += "<th>작성시간</th></tr>";
        for(let i in commentList){
          output += "<tr>";
          output += "<td>"+commentList[i].id+"</td>";
          output += "<td>"+commentList[i].commentWriter+"</td>";
          output += "<td>"+commentList[i].commentContents+"</td>";
          output += "<td>"+moment(commentList[i].commentCreatedDate).format("YYYY-MM-DD HH:mm:ss")+"</td>";
          output += "</tr>";
        }
        output += "</table>";
        document.getElementById('comment-list').innerHTML = output;
        document.getElementById('commentWriter').value='';
        document.getElementById('commentContents').value='';
      },
      error: function () {
        console.log("실패");
      }
    });
  }
  const listFn = () => {
    const page = '${page}';
    location.href = "/board/paging?page=" + page;
  }
  const updateFn = () => {
    const id = '${board.id}';
    location.href = "/board/update?id=" + id;
  }
  const deleteFn = () => {
    const id = '${board.id}';
    location.href = "/board/delete?id=" + id;
  }
</script>
</html>
