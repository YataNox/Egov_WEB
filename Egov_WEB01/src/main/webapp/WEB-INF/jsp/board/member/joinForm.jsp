<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JoinForm</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/board.css'/>" />
		<script src="<c:url value='/script/board.js'/>"></script>
	</head>
	<body>
		<div id="wrap" align="center">
			<h1>사용자 등록</h1>
		</div>
		<form name="frm" method="post" action="join.do">
			<table>
				<tr>
					<th>아이디</th>
						<td>
							<input type="text" name="userid" size="20"> *
						<input type="button" value="중복체크" onClick="idCheck();">
						<input type="hidden" name="reid">
						</td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" size="20" name="name">*</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" size="20" name="pw">*</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" size="20" name="pw_check">*</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" size="30" name="email"></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" size="20" name="phone"></td>
				</tr>
			</table><br><br>
			<input type="submit" value="등록" onclick="return joinCheck()">
			<input type="reset" value="다시 작성">
			<input type="button" value="로그인페이지로" onclick="location.href='loginForm.do'">
		</form>
	</body>
</html>