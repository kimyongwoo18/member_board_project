<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오후 1:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<html>
<head>
    <title>memberList</title>
</head>
<body>
<div class="container" id="list">
    <table class="table table-striped table-hover text-center">
        <tr>
            <th>회원번호</th>
            <th>이메일</th>
            <th>비밀번호</th>
            <th>이름</th>
            <th>전화번호</th>

        </tr>
        <c:forEach items="${memberList}" var="member">
            <tr>
                <td>
                        ${member.id}
                </td>
                <td>
                        ${member.memberEmail}
                </td>
                <td>
                    ${member.memberPassword}
                </td>
                <td>
                        ${member.memberName}
                </td>
                <td>
                    ${member.memberMobile}
                </td>
            </tr>
            <c:if test="${member.storedProfileName != null}">
                <tr>
                    <th>프로필사진</th>
                    <td>
                        <img src="${pageContext.request.contextPath}/upload/${member.storedProfileName}"
                             alt="" width="100" height="100">
                    </td>
                </tr>
            </c:if>
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
                    <a class="page-link" href="/member/?page=${paging.page-1}">[이전]</a>
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
                        <a class="page-link" href="/member/?page=${i}">${i}</a>
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
                    <a class="page-link" href="/member/?page=${paging.page+1}">[다음]</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
</body>
</html>
