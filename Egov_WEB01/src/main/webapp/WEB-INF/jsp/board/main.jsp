<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Main</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/board.css'/>"/>
	<script type="text/javascript" src="<c:url value='/script/board.js'/>"></script>
</head>
<body>
	<div id="wrap" align="center">
	<h1>게시글 리스트</h1>
		<table>
			<tr>
				<td colspan="5" style="border:white">
					<div style="float: left;">
						${loginUser.name}(${loginUser.userid})님 로그인 
						<input type="button"value="회원정보수정" onClick="location.href='editMemberForm'">
						<input type="button" value="로그아웃" onClick="location.href='logout'">
					</div>
					<div style="float:right">
						<a href="boardWriteForm">게시글 등록</a>
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
				<tr align="center">
					<td>${board.num}</td>
					<td align="left">
						<a href="board.do?command=boardView&num=${board.num}">${board.title}</a>&nbsp;
						<c:if test="${board.replycnt>0}">
							<span style="color:red; font-weight:bold;">[${board.replycnt}]</span>
						</c:if>
					</td>
					<td>${board.userid}</td>
					<td><fmt:formatDate value="${board.writedate}"/></td>
					<td>${board.readcount}</td>
				</tr>
			</c:forEach>
		</table>
		<br><br>
		<div id="paging">
			<!-- 링크 경로의 기본 텍스트를 변수에 저장 -->
			<c:url var="action" value="boardlist"/>
			<c:if test="${paging.prev}">
				<a href="${action}&page=${paging.beginPage-1}">◀</a>&nbsp;
				<!-- board.do?command=boardlist&page=10 -->			
			</c:if>
			
			<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
				<c:choose>
					<c:when test="${paging.page==index}">
						<!-- 링크 없이 표시 -->
						<span style="color:red">${index} &nbsp;</span>
					</c:when>
					<c:otherwise>
						<!-- 그 외에는 표시되는 숫자의 페이지를 파라미터로 해서 링크걸어 표시 -->
						<a href="${action}&page=${index}"> ${index} &nbsp;</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${paging.next}">
				<a href="${action}&page=${paging.endPage+1}">▶</a>&nbsp;		
			</c:if>
		</div>
	</div>
</body>
</html>