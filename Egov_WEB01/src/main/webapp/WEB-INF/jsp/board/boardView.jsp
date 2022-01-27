<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>boardView</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/board.css'/>" />
		<script src="<c:url value='/script/board.js'/>"></script>
	</head>
	<body>
		<div id="wrap" align="center">
			<h1>게시글 상세보기</h1>
			<table>
				<tr>
					<th>작성자</th>
					<td>${board[0].USERID}</td>
					<th>이메일</th>
					<td>${board[0].EMAIL}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td><fmt:formatDate value="${board[0].WRITEDATE}"/></td>
					<th>조회수</th>
					<td>${board[0].READCOUNT}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3">${board[0].TITLE}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="2">${board[0].CONTENT}</td>
					<td width="300" align="center">
						<c:choose>
							<c:when test="${empty board[0].IMGFILENAME}">
								<img src="<c:url value='/images/noname.jpg'/>" width="250">
							</c:when>
							<c:otherwise>
								<img src="<c:url value='/images/${board[0].IMGFILENAME}'/>" width="250">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table><br><br>
			<input type="button" value="게시글 리스트" onclick="location.href='boardList.do'">
			<input type="button" value="게시글 수정" onclick="open_win('boardEditForm.do?num=${board[0].NUM}', 'update')">
			<input type="button" value="게시글 삭제" onclick="open_win('boardDeleteForm.do?num=${board[0].NUM}', 'delete')">
		</div><br><br>
		
		<c:set var="now" value="<%=new java.util.Date()%>"></c:set>
		<div id="wrap" align="center">
			<form action="addReply.do" method="post" name="frm2">
				<input type="hidden" name="boardnum" value="${board[0].NUM}">
				<table>
					<tr>
						<th>작성자</th>
						<th>작성일시</th>
						<th>내용</th>
						<th>&nbsp;</th>
					</tr>
					<tr align="center">
						<td width="100">${loginUser.ID}<input type="hidden" name="userid" value="${loginUser.ID}"></td>
						<td width="100"><fmt:formatDate value="${now}" pattern="MM/dd HH:mm"/></td>
						<td width="670"><input type="text" name="reply" size="85"></td>
						<td width="100"><input type="submit" value="답글작성" onclick="return reply_check();"></td>
					</tr>
					<c:forEach var="reply" items="${replyList}">
						<tr>
							<td align="center">${reply.USERID}</td>
							<td align="center">
								<fmt:formatDate value="${reply.WRITEDATE}" pattern="MM/dd HH:mm"/>
							</td>
							<td>${reply.CONTENT}</td>
							<td align="center">
								<c:if test="${reply.USERID==loginUser.ID}">
									<input type="button" value="삭제" onclick="location.href='deleteReply.do?num=${reply.NUM}&boardnum=${reply.BOARDNUM}'">
								</c:if>&nbsp;
							</td>
						</tr>
					</c:forEach>
				</table><br><br><br>
			</form>
		</div>
	</body>
</html>