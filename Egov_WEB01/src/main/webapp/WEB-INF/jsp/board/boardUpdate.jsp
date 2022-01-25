<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>boardUpdate</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/board.css'/>" />
		<script src="<c:url value='/script/board.js'/>"></script>
	</head>
	<body>
		<div id="wrap" align="center">
			<h1>게시글 수정</h1>
			<form action="boardupdate.do" name="frm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="num" value="${board.num}">
				<table>
					<tr>
						<th>작성자</th>
						<td>${board.userid}<input type="hidden" name="userid" value="${loginUser.id}"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pass" size="12">* (게시물 수정 삭제시 필요합니다.)</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" name="email" value="${board.email}" size="30"></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" value="${board.title}" size="50">*</td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea cols="70" rows="15" name="content">${board.content}</textarea>*</td>
					</tr>
					<tr>
						<th>이미지</th>
						<td>
							<c:choose>
								<c:when test="${empty board.imgfilename}">
									<img src="<c:url value='/images/noname.jpg'/>" height="70"/><br>
								</c:when>
								<c:otherwise>
									<img src="<c:url value='/images/${board.imgfilename}'/>" height="70"/><br>
								</c:otherwise>
							</c:choose>
						<input type="file" name="imgfilename"><br>파일을 수정하고자 할때만 선택하세요.
						<input type="hidden" name="oldfilename" value="${board.imgfilename}">
						</td>
					</tr>
				</table>
				<br>
				<input type="submit" value="수정" onClick="return boardCheck()">
				<input type="reset" value="다시작성">
				<input type="button" value="돌아가기"
				 onClick="location.href='boardviewwithoutcount.do?num=${board.num}'">
			</form>
		</div>
	</body>
</html>