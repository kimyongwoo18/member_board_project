<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오전 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<html>
<head>
    <title>boardList</title>
    <script src="/resources/js/jquery.js"></script>
    <style>
        #list{
            margin-top: 30px;
        }
        #inputBoard{
            width:1000px;
        }
        #searchBoard{
            width : 400px;
        }
        .select{
            width :200px;
        }
    </style>
</head>
<body>

<c:if test="${sessionScope.loginEmail != null}">
    <div class="container" id="inputBoard">
        <button class="btn btn-primary" onclick="boardFn()">글쓰기</button>
        <button class="btn btn-primary" onclick="mainFn()">마이페이지</button>
        <form action="/board/search" class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
            <div class="input-group">
                <select name="type" class="select form-select">
                    <option value="boardTitle" selected>제목</option>
                    <option value="boardWriter" selected>작성자</option>
                </select>
                <input type="search" name="q" id="searchBoard" class="form-control form-control-dark text-bg-dark" placeholder="Search..." aria-label="Search">
                <button class="btn btn-success">검색</button>
            </div>
        </form>
    </div>
</c:if>
<div class="container" id="list">
    <table class="table table-striped table-hover text-center">
        <tr>
            <th>글번호</th>
            <th>작성자</th>
            <th>제목</th>
            <th>조회수</th>
            <th>작성시간</th>
        </tr>
<c:forEach items="${boardList}" var="board">
        <tr>
            <td>
            ${board.id}
            </td>
            <td>
            ${board.boardWriter}
            </td>
            <td>
            <a href="/board?id=${board.id}">${board.boardTitle}</a>
            </td>
            <td>
            ${board.boardHits}
            </td>
            <td>
            <fmt:formatDate value="${board.boardCreatedDate}" pattern="yyyy-mm-dd hh:mm:ss"></fmt:formatDate>
            </td>
        </tr>
</c:forEach>
    </table>
    <button class="brn btn-dark" onclick="index()">초기화면</button>
</div>
<div class="container">
    <ul class="pagination justify-content-center">
        <c:choose>
            <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
            <c:when test="${paging.page<=1}">
                <li class="page-item disabled">
                    <a class="page-link">[이전]</a>
                </li>
            </c:when>
            <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
            <c:otherwise>
                <li class="page-item">
                    <a class="page-link" href="/board/?page=${paging.page-1}">[이전]</a>
                </li>
            </c:otherwise>
        </c:choose>

        <%--  for(int i=startPage; i<=endPage; i++)      --%>
        <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
            <c:choose>
                <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
                <c:when test="${i eq paging.page}">
                    <li class="page-item active">
                        <a class="page-link">${i}</a>
                    </li>
                </c:when>

                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="/board/?page=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${paging.page>=paging.maxPage}">
                <li class="page-item disabled">
                    <a class="page-link">[다음]</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item">
                    <a class="page-link" href="/board/?page=${paging.page+1}">[다음]</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

</body>
<script>
    const index = () => {
        location.href="/";
    }
    const boardFn = () => {
        location.href="/boardSave";
    }
    const mainFn = () => {
      location.href="/memberMain";
    }
</script>
</html>
