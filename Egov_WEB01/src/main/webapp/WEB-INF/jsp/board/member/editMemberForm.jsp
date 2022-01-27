<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>editMemberForm</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/board.css'/>" />
		<script src="<c:url value='/script/board.js'/>"></script>
	</head>
	<body>
		<div id="wrap" align="center">
			<h1>사용자 수정</h1>
		</div>
			<form name="frm" action="editMember.do" method="post">
				<table>
					<tr>
						<th>아이디</th>
						<td>${loginUser.ID}
							<input type="hidden" name="userid" value="${loginUser.ID}">&nbsp;
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pwd" size="20">&nbsp;*</td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" name="pwd_check" size="20">&nbsp;*</td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="name" value="${loginUser.NAME}" size="20"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" name="email" value="${loginUser.EMAIL}" size="20"></td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td><input type="text" name="phone" value="${loginUser.PHONE}" size="20"></td>
					</tr>
				</table>
				<input type="submit" value="수정" onclick="return editCheck()">&nbsp;
				<input type="reset" value="다시작성"/>
				<input type="button" value="목록으로" onclick="location.href='boardList.do'"/>
			</form>
	</body>
</html>