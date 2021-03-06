<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Main</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/board.css'/>" />
		<script src="<c:url value='/script/board.js'/>"></script>
	</head>
	<body>
		<div id="wrap" align="center">
			<h1>게시글 리스트</h1>
			<table class="list">
				<tr>
					<td colspan="5" style="border: white; text-align: right">
						<div style="float: left;">
							${loginUser.NAME}(${loginUser.ID})님 로그인
							<input type="button" value="정보수정" onclick="location.href='memberEditForm.do'">
							<input type="button" value="로그아웃" onclick="location.href='logout.do'">
						</div>
						<div style="float: right;">
							<a href="boardWriteForm.do">게시글 등록</a>
						</div>
					</td>
				</tr>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회</th>
				</tr>
				<c:forEach var="board" items="${boardList}">
					<tr class="record">
						<td align="center">${board.NUM}</td>
						<td>
							<a href="boardView.do?num=${board.NUM}">${board.TITLE}</a>
							<c:if test="${board.REPLYCNT > 0}">
								<span style="color: red; font-weight: bold;">[${board.REPLYCNT}]</span>
							</c:if>
						</td>
						<td align="center">${board.USERID}</td>
						<td align="center"><fmt:formatDate value="${board.WRITEDATE}"/></td>
						<td align="center">${board.READCOUNT}</td>
					</tr>
				</c:forEach>
			</table><br>
			
			<div id="paging">
				<c:if test="${paging.prev}">
					<a href="main?page=${paging.beginPage-1}">◀</a>
				</c:if>
				<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
					<c:choose>
						<c:when test="${paging.page==index}">
							<span style="color: red; font-weight: bold;">${index}</span>
						</c:when>
						<c:otherwise>
							<a href="boardList.do?page=${index}">${index}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${paging.next}">
					<a href="main?page=${paging.endPage+1}">▶</a>
				</c:if>
			</div>
		</div>
	</body>
</html>