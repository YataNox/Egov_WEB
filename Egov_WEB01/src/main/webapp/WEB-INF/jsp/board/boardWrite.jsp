<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>BoardWrite</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/board.css'/>" />
		<script src="<c:url value='/script/board.js'/>"></script>
	</head>
	<body>
		<div id="wrap" align="center">
			<h1>게시글 등록</h1>
		</div>
		<form action="boardWrite.do" name="frm" method="post" enctype="multipart/form-data">
			<table>
			<tr>
				<th>작성자</th>
				<td>${loginUser.id}<input type="hidden" name="userid" value="${loginUser.id}"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pass"> * 필수(게시문 수정 삭제시 필요합니다.)</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email" value="${loginUser.email}"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" size="70" name="title"> * 필수</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols="70" rows="15" name="content"></textarea> * 필수</td>
			</tr>
			<tr>
				<th>이미지</th>
				<td><input type="file" name="imgfilename"></td>
			</tr>
			</table><br>
			<input type="submit" value="등록" onclick="return boardCheck()">&nbsp;
			<input type="reset" value="다시작성"/>
			<input type="button" value="목록으로" onclick="location.href='boardList.do'"/>
 		</form>
	</body>
</html>