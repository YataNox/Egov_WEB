<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>idcheck</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/board.css'/>" />
		<script src="<c:url value='/script/board.js'/>"></script>
	</head>
	<body>
		<form action="idcheck.do" method="get" name="frm">
			아이디 : <input type="text" name="userid" value="${userid}">
			<input type="submit" value="중복 체크">
		</form> <br><br><br>
		
		<c:if test="${result==1}">
			${userid}는 이미 사용 중인 아이디입니다.
			<script>
				opener.document.useri.value=""; // 팝업창을 오픈한 주체 : opener
			</script>
		</c:if>
		<c:if test="${result==-1 }">
			${userid}는 사용 가능한 아이디입니다.
			<input type="button" value="사용" class="cancel" onClick="idok('${userid}')"/>
		</c:if>
	</body>
</html>